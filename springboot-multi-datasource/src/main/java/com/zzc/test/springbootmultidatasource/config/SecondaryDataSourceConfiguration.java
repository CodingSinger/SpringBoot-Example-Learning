package com.zzc.test.springbootmultidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhengzechao
 * @date 2018/6/23
 * Email ooczzoo@gmail.com
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.datasource.secondary",name = "jdbc-url")
@MapperScan(basePackages = "com.zzc.test.springbootmultidatasource.dao.secondary",sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class SecondaryDataSourceConfiguration {


    @Value("${spring.datasource.secondary.mybatis.mapperLocation}")
    private String mapperLocation;
    @Value("${spring.datasource.secondary.mybatis.typeAliasPackage}")
    private String typeAliasPackage;

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")

    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource")/*多个相同类型的时候，指定注入该Bean*/
                                                                              DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }


}
