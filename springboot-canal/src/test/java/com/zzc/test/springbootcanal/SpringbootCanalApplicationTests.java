package com.zzc.test.springbootcanal;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.zzc.test.springbootcanal.annotation.CanalCatcher;
import com.zzc.test.springbootcanal.listener.CanalListener;
import com.zzc.test.springbootcanal.utils.DMLSqlAssembler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCanalApplicationTests {
    private static String url;
    private static String username;
    private static String password;
    @Test
    public void contextLoads() {


    }

    @CanalCatcher
    @Component
    private static class CustomeListener implements CanalListener {


        static {
            try {
                String url = "jdbc:mysql://localhost:3306/secondary?useUnicode=true&characterEncoding=utf8&useSSL=true&useServerPrepStmts=true&cachePrepStmts=true";
                String username = "root";
                String password = "password";
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
                    assembler.handleInsertRowData(entry,rowData,conn);
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


}
