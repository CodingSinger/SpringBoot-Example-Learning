# SpringBoot-Example-Learning #


***


# springboot-helloworld
第一个SpringBoot学习案例

- @Configuration注解的使用
- @ConfigurationProperties注解使用
    - 该注解使用在注入properties对象的时候需要配合@EnableConfigurationProperties使用，并且需要properties类
    必须有对应的set方法才能注入
    - @ConditionalOnProperty注解的使用 只有配置文件中有某个properties属性或者有某个值时才会像Spring容器注册该bean，通常配合@Configuration使用
    

@Autowired、@Value等属性都不能注入静态属性，否则会报错`Autowired annotation is not supported on static fields:`
注入的时候是在Spring初始化所有对象的时候,doCreateBean部分代码如下，

```java
	// Initialize the bean instance.
		Object exposedObject = bean;
		try {
			populateBean(beanName, mbd, instanceWrapper); //对象字段的注入
			exposedObject = initializeBean(beanName, exposedObject, mbd); //BeanPostProcessor的前后置处理方法执行
		}

```
如上，这两个方法都是很有用的，调用populateBean方法进行填充依赖，需要注意的是因为此时对象已经创建成功了，所以在构造函数里对@Autowired、@Value等字段的使用都是不成功的。
其中会调用AutowiredAnnotationBeanPostProcessor的方法进行对带此类注入注解的判断和注入。但是字段注入的执行方法在`AutowiredFieldElement#inject`，可以看到该方法是直接通过Field.set方法注入的，所以不需要@Autowired、
@Value等属性提供对应的set方法即可注入。



# springboot-mybatis
spriingboot结合mybatis进行开发

如果使用interface+Mapper的方式，注意Mapper的namespace需要和interface的全限定名一致，否则会找不到对应的sql

**注意需要在启动类上加上dao接口类扫描的完整包名**
@MapperScan("com.zzc.test.springboot_mybatis.springbootmybatis.dao")



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



## SpringBoot添加Interceptor

```java

@Configuration
public class InterceptorRegistration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor());
    }
}

```


## SpringBoot 添加Filter

### 方法1
```java

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

  
}

```


```java

@SpringBootApplication

@ServletComponentScan  //启动类加这个注解
primaryMapper
@MapperScan("com.zzc.test.springboot_mybatis.springbootmybatis.dao")
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisApplication.class, args);
	}
}

```


### 方法二

```java

public  class  TestFilter  implements  Filter  {
{ 
    
}


```


```java


@Bean
public FilterRegistrationBean testFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new TestFilter());
    registration.addUrlPatterns("/*");
    registration.addInitParameter("paramName", "paramValue");
    registration.setName("testFilter");
    registration.setOrder(1);
    return registration;
}

```




# springboot-mybatis-redis


* @Transactional//默认只回滚运行时异常 除非用rollbackFor指定 (rollbackFor = Exception.class) 
     捕捉了的异常不会回滚，事务回滚只会是抛出的异常
 
* RedisTemplate默认使用的是JdkSerializationRedisSerializer，所以需要对象实现Serializable接口
  
* Http的PUT方法传参数不能用Content-Type：multipart/form-data传参

* @RequestBody 接受对象的json字符串，需要请求头dataType: "json",contentType:"application/json"

redis做缓存不能用在强一致的情况下，redis做为mysql的缓存
其基本的策略一般是读先读redis，redis没有才去读mysql,并将读到的内容插入redis
更新操作一般先删除redis的缓存数据，再对mysql进行更新。
新增操作直接操作mysql。
此时分为三种情况：
1. redis删除出错，则全部不执行，返回错误。不会破坏一致性
2. redis删除正确，但是mysql更新出错，则返回错误。也不会破坏一致性。
3. redis删除正确，mysql更新正确，返回正确。也不会破坏一致性。

[知乎：分布式的环境下， MySQL和Redis如何保持数据的一致性？](https://www.zhihu.com/question/36413559)



# springboot+elasticsearch


[文档](https://github.com/spring-projects/spring-data-elasticsearch)
 报错 
 org.elasticsearch.client.transport.NoNodeAvailableException: None of the configured nodes are available: [{#transport#-1}{Os_FBvGPRmSeCof-OSxLKg}{127.0.0.1}{127.0.0.1:9300}]
 
 在SpringBoot配置文件中加上 spring.data.elasticsearch.cluster-name=
 关于cluster-name值可在localhost:9200上进行查看
 
 
 关于model中的ID和Spring Data JPA的中的相似
 https://stackoverflow.com/questions/29855280/spring-data-elasticsearch-requires-property-named-id?rq=1&utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
# springboot+quartz



```java

  trigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduleJob.getTriggerName(),scheduleJob.getTriggerGroup())
                    .withDescription(scheduleJob.getDescription())
                    .startAt(date)
                    .build();
```
持久化在mysql的任务date如果已经超过指定时间(startAt())在`        scheduler.scheduleJob(jobDetail, trigger);`
之后并不会马上运行，而是在下次启动服务器的时候再从数据库中加载并运行，运行之后删除任务。(前提没有超过endAt限制)



```分布式环境下
一个间隔执行的事件并不是由一个机器获取执行到这台机器宕机为止，中途有可能由其他机器执行，(其实就应该这样)不然的话可能会导致某台
机器全是间断时间短执行的任务，造成负载很高。

```

[springboot-quartz](https://github.com/helloworlde/SpringBootCollection/tree/master/SpringBoot-ScheduledJob)
[springboot项目集合](https://gitee.com/hengboy/spring-boot-chapter/tree/master)

# springboot-多数据源

@Primary 是用于当有多个相同类型的对象时，设置优先考虑的注入对象

只有标注了@Primary的事务生效？

因为所有标注了@Transactional的方法都会用@Primary的TransactionManager作为默认的事务管理器，所以如果sql的
执行的数据源和事务管理器的数据源不在同一个数据源将会导致事务无法回滚，针对该情况只有在`@Transactional(rollbackFor = Exception.class,transactionManager = "")`来
指定事务管理器。
因此同一个方法里如果有两个数据源将无法用事务管理器回滚。这就类似于分布式事务了。
`

