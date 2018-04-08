# SpringBoot-Example-Learning #


***


# springboot-helloworld
第一个SpringBoot学习案例

# springboot-mybatis
spriingboot结合mybatis进行开发

**注意需要在启动类上加上dao接口类扫描的完整包名**
@MapperScan("com.zzc.test.springboot_mybatis.springbootmybatis.dao")



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