package com.zzc.test.springboot_mybatis.springbootmybatis.Dao;

import com.zzc.test.springboot_mybatis.springbootmybatis.domain.Person;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhengzechao
 * @date 2018/3/14
 */

public interface PersonDao {
    /**
     * 删除
     *
     * @param id
     */
    public void delete(@Param(value = "id") Integer id);

    /**
     * 增加
     *
     * @param id
     */
    public int add(@Param(value = "p") Person person);


    /**
     * 查找
     * @param id
     * @return
     */
    Person queryById(@Param(value = "id") Integer id);
}
