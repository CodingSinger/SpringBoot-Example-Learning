
[文档](https://github.com/spring-projects/spring-data-elasticsearch)
 报错 
 org.elasticsearch.client.transport.NoNodeAvailableException: None of the configured nodes are available: [{#transport#-1}{Os_FBvGPRmSeCof-OSxLKg}{127.0.0.1}{127.0.0.1:9300}]
 
 在SpringBoot配置文件中加上 spring.data.elasticsearch.cluster-name=
 关于cluster-name值可在localhost:9200上进行查看
 
 
 关于model中的ID和Spring Data JPA的中的相似
 https://stackoverflow.com/questions/29855280/spring-data-elasticsearch-requires-property-named-id?rq=1&utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa