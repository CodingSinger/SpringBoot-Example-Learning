package com.zzc.test.springbootmultidatasource.dao.secondary;

import com.zzc.test.springbootmultidatasource.domain.Person;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhengzechao
 * @date 2018/6/23
 * Email ooczzoo@gmail.com
 */
public interface SecondaryMapper {
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
    public int add(Person person);


    /**
     * 查找
     * @param id
     * @return
     */
    Person queryById(@Param(value = "id") Integer id);
}
