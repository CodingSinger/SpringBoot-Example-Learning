package com.zzc.test.springbootcanal.utils;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.zzc.test.springbootcanal.exception.ColumnsErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/7/6
 * Email ooczzoo@gmail.com
 */
public class DMLSqlAssembler {
    private static final Logger log = LoggerFactory.getLogger(DMLSqlAssembler.class);

    //TODO 批量插入
    /**
     * 对每一行数据进行改变
     *
     * @param entry
     * @param rowData
     * @param connection
     * @throws SQLException
     */
    public void handleInsertRowData(CanalEntry.Entry entry, CanalEntry.RowData rowData, Connection connection) throws SQLException {
        final List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        if (afterColumnsList.isEmpty()) {
            throw new ColumnsErrorException(entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), 0);
        }

        StringBuilder sqlBuilder = new StringBuilder("insert into ")
                .append(entry.getHeader().getTableName())
                .append(" (");

        for (Iterator<CanalEntry.Column> iterator = afterColumnsList.iterator(); iterator.hasNext(); ) {
            CanalEntry.Column column = iterator.next();
            sqlBuilder.append(column.getName());

            if (iterator.hasNext()) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(") values (");
        for (Iterator<CanalEntry.Column> iterator = afterColumnsList.iterator(); iterator.hasNext(); ) {
            iterator.next();
            sqlBuilder.append("?");

            if (iterator.hasNext()) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();
        if (log.isDebugEnabled()) {
            log.debug("Insert sql ==> {}", sql);
        }
        PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());
        int i = 1;
        for (CanalEntry.Column column : afterColumnsList) {
            ps.setString(i, column.hasValue() ? column.getValue() : null);
            i++;
        }
        int inserted = ps.executeUpdate();

        if (inserted != 1) {
            log.warn("Insert SQL should update 1 row exactly,sql:{}", sql);
        }


    }


    public void handleDeleteRowData(CanalEntry.Entry entry, CanalEntry.RowData rowData, Connection connection) throws SQLException {
        final List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        String schemaName = entry.getHeader().getSchemaName();
        String tableName = entry.getHeader().getTableName();
        if (beforeColumnsList.isEmpty()) {
            throw new ColumnsErrorException(schemaName, tableName, 0);
        }

        StringBuilder sqlBuilder = new StringBuilder("delete from ")
                .append(entry.getHeader().getTableName())
                .append(" where ");

        String sql = sqlBuilder.toString();
        if (log.isDebugEnabled()) {
            log.debug("Delete sql ==> {}", sql);
        }


        PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());
        int i = 1;
        for (final CanalEntry.Column column : beforeColumnsList) {

        }
        int deleted = ps.executeUpdate();
        if (deleted != 1) {
            log.warn("Update SQL should update 1 row exactly,sql:{}", sql);
        }
    }

    public void handleUpdateRowData(CanalEntry.Entry entry, CanalEntry.RowData rowData, Connection connection) throws SQLException {
        final List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        final List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        String schemaName = entry.getHeader().getSchemaName();
        String tableName = entry.getHeader().getTableName();
        if (afterColumnsList.size() < 2) {
            throw new ColumnsErrorException(schemaName, tableName,
                    afterColumnsList.size());
        }

        StringBuilder sqlBuilder = new StringBuilder("update ")
                .append(tableName)
                .append(" set");

        //修改过的column getUpdated返回true
        for (CanalEntry.Column column : afterColumnsList) {
            if (column.getUpdated()) {
                sqlBuilder.append(" ").append(column.getName()).append(" = ?,");
            }
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" where 1 = 1 ");

        //获取主键 根据主键作为where条件来修改 为了防止update修改主键的值 通过beforeColumn来修改

        List<CanalEntry.Column> primaryColumn = new ArrayList<>();
        for (CanalEntry.Column column : beforeColumnsList) {
            if (column.getIsKey()){
                sqlBuilder.append(" and ")
                        .append(column.getName())
                        .append(" = ? ");
                primaryColumn.add(column);
            }
        }
        String sql = sqlBuilder.toString();
        if (log.isDebugEnabled()) {
            log.debug("Update sql ==> {}", sql);
        }

        PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());
        int i = 1;
        for (CanalEntry.Column column : afterColumnsList) {
            if (column.getUpdated()) {
                ps.setString(i, column.hasValue() ? column.getValue() : null);
                i++;
            }
        }
        for (CanalEntry.Column column : primaryColumn) {
            ps.setString(i,column.getValue());
            i++;
        }

        int updated = ps.executeUpdate();
        if (updated != 1) {
            log.warn("Update SQL should update 1 row exactly,sql:{}", sql);
        }
    }
}
