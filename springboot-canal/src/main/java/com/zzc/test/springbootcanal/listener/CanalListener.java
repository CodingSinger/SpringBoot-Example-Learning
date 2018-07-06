package com.zzc.test.springbootcanal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */
public interface  CanalListener {

    void onDelete(CanalEntry.Entry entry, CanalEntry.RowChange rowChange);

    void onUpdate(CanalEntry.Entry entry, CanalEntry.RowChange rowChange);

    void onInsert(CanalEntry.Entry entry, CanalEntry.RowChange rowChange);

    void onDDL(CanalEntry.Entry entry, CanalEntry.RowChange rowChange);

    void onException(CanalEntry.Entry entry, CanalEntry.RowChange rowChange);

}
