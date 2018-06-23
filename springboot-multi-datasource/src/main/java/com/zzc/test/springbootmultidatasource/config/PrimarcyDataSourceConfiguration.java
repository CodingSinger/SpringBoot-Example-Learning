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
@ConditionalOnProperty(prefix = "spring.datasource.primary",name = "jdbc-url")
@MapperScan(basePackages = "com.zzc.test.springbootmultidatasource.dao.primary",sqlSessionFactoryRef = "primarySqlSessionFactory")//扫描mapper接口
public class PrimarcyDataSourceConfiguration {



    @Value("${spring.datasource.primary.mybatis.mapperLocation}")
    private String mapperLocation;
    @Value("${spring.datasource.primary.mybatis.typeAliasPackage}")
    private String typeAliasPackage;

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }



    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name="primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource")/*多个相同类型的时候，指定注入该Bean*/
                                                                          DataSource prodDataSource) {
        return new DataSourceTransactionManager(prodDataSource);
    }

}
