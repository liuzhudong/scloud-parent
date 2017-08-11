package com.jellyliu.spring.cloud.server.city.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis 配置
 *
 * @Creater liuzhudong
 * @Date 17/6/2 下午4:12
 * @Version 1.0
 * @Copyright
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfiguration implements EnvironmentAware {

  private final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

  private RelaxedPropertyResolver dataSourcePropertyResolver;
  private RelaxedPropertyResolver mysqlPropertyResolver;

  private RelaxedPropertyResolver propertyResolver;

  @Override
  public void setEnvironment(Environment environment) {
    this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
    this.mysqlPropertyResolver = new RelaxedPropertyResolver(environment, "mysql.jdbc.");
    this.dataSourcePropertyResolver = new RelaxedPropertyResolver(environment, "datasource.druid.");

  }

  /**
   * 数据源
   */
  @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
  public DruidDataSource dataSource() {
    logger.debug("初始化数据源");
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(mysqlPropertyResolver.getProperty("driver"));
    dataSource.setUrl(mysqlPropertyResolver.getProperty("url"));
    dataSource.setUsername(mysqlPropertyResolver.getProperty("user"));
    dataSource.setPassword(mysqlPropertyResolver.getProperty("password"));
    // 连接池配置
    dataSource.setInitialSize(
        dataSourcePropertyResolver.getProperty("initialSize", Integer.class, 1));
    dataSource.setMinIdle(
        dataSourcePropertyResolver.getProperty("minIdle", Integer.class, 1));
    dataSource.setMaxActive(
        dataSourcePropertyResolver.getProperty("maxActive", Integer.class, 20));
    dataSource.setMaxWait(
        dataSourcePropertyResolver.getProperty("maxWait", Long.class, 60000L));
    dataSource.setTimeBetweenEvictionRunsMillis(
        dataSourcePropertyResolver.getProperty("timeBetweenEvictionRunsMillis",
            Long.class, 60000L));
    dataSource.setMinEvictableIdleTimeMillis(
        dataSourcePropertyResolver.getProperty("minEvictableIdleTimeMillis",
            Long.class, 30000L));
    dataSource.setRemoveAbandoned(true);
    dataSource.setRemoveAbandonedTimeout(60);
    dataSource.setLogAbandoned(true);
    return dataSource;
  }

  @Bean(name = "sqlSessionFactory")
  @ConditionalOnMissingBean
  public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) throws Exception {
    try {
      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
      sessionFactory.setDataSource(dataSource);
      sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
      sessionFactory.setConfigLocation(
          new DefaultResourceLoader().getResource(propertyResolver.getProperty("configLocation")));
      sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
          .getResources(propertyResolver.getProperty("mapperLocations")));
      return sessionFactory.getObject();
    } catch (Exception e) {
      logger.error("mybatis session factory 初始化异常", e);
      throw e;
    }
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setBasePackage(propertyResolver.getProperty("daoBasePackage"));
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    return mapperScannerConfigurer;
  }

  @Bean
  @ConditionalOnMissingBean
  public DataSourceTransactionManager transactionManager(DruidDataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
