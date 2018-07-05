package com.zzc.test.springboot_mybatis.springbootmybatis.domain;

/**
 * @author zhengzechao
 * @date 2018/3/14
 */
public class Person extends Human{
    private Integer id;
    private String name;




    private Dog dog;//注入dog.price 需要这样dog.price 直接用price注入不了 并且需要提供dog的get方法 因为注入dog元素的时候 需要获取dog对象

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog)
    {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dog=" + dog +
                '}';
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }
}
