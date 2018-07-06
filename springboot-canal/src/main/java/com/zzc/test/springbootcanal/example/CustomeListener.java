package com.zzc.test.springbootcanal.example;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zzc.test.springbootcanal.annotation.CanalCatcher;
import com.zzc.test.springbootcanal.listener.CanalListener;
import com.zzc.test.springbootcanal.utils.DMLSqlAssembler;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/7/6
 * Email ooczzoo@gmail.com
 */
@CanalCatcher(pattern = "test\\..*")
public class CustomeListener implements CanalListener {

    private static String url;
    private static String username;
    private static String password;
    static {
        try {
            url = "jdbc:mysql://localhost:3306/secondary?useUnicode=true&characterEncoding=utf8&useSSL=true&useServerPrepStmts=true&cachePrepStmts=true";
            username = "root";
            password = "it&1997";
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDelete(CanalEntry.Entry entry, CanalEntry.RowChange rowChange) {

    }

    @Override
    public void onUpdate(CanalEntry.Entry entry, CanalEntry.RowChange rowChange) {

    }

    @Override
    public void onInsert(CanalEntry.Entry entry, CanalEntry.RowChange rowChange) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            DMLSqlAssembler assembler = new DMLSqlAssembler();
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatasList) {
                assembler.handleInsertRowData(entry, rowData, conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDDL(CanalEntry.Entry entry, CanalEntry.RowChange rowChange) {

    }

    @Override
    public void onException(CanalEntry.Entry entry, CanalEntry.RowChange rowChange) {

    }
}

