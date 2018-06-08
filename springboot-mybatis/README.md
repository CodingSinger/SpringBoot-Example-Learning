```java

@Repository
public class PersonDaoDao
{

    @Autowired
    public SqlSession sqlSession;



    public int create(Person person){
        return sqlSession.insert("com.zzc.test.springboot_mybatis.springbootmybatis.Dao.PersonDaoDao.add",person);
    }

    public Person findById(Integer id){

//        return sqlSession.selectOne("PersonDao.queryById", ImmutableMap.of("id",id));
        //statement必须是对应的mapper.xml文件的namespace.sqlId语句的格式 并且好像namespace可以随便取，但不推荐随便取

        //如果格式不符合则会报错Mapped Statements collection does not contain ...
        return sqlSession.selectOne("PersonDaoDaohh.queryById", ImmutableMap.of("id",id));
        //一个参数的时候传map或者传id都行，parameterType=map或者int都行，因为mybatis会自己为我们解析出来，但sql有多个参数时则需包装到map里，并且mapperxml文件中parameterType=map
    }
}

```


mysql预编译功能 默认不开启
http://cs-css.iteye.com/blog/1847772
https://segmentfault.com/a/1190000004617028