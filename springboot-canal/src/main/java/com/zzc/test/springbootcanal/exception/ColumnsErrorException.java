package com.zzc.test.springbootcanal.exception;

import java.sql.SQLException;

/**
 * @author zhengzechao
 * @date 2018/7/6
 * Email ooczzoo@gmail.com
 */
public class ColumnsErrorException extends SQLException {
    public ColumnsErrorException(String schemaName, String tableName, int i) {
    }
}
