package com.zzc.test.springbootcanal.listener;

/**
 * @author zhengzechao
 * @date 2018/7/5
 * Email ooczzoo@gmail.com
 */
public interface  CanalListener {

    void onDelete();

    void onUpdate();

    void onInsert();

    void onDDL();

}
