package com.gdu.app11.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@PropertySource(value="classpath:application.properties")
@EnableTransactionManagement  // @Transactional 허용 -> 이제 tx.do 주소 창에 입력해도 data 들어가지x
@EnableAspectJAutoProxy       // 이제 aop 없어서 안돌아감
@Configuration
public class AppConfig {

  @Autowired
  private Environment env;
  // @PropertySource(value="classpath:application.properties") 이걸보고 스프링이 환경을 만들고  
  //Environment 여기서 가져다 쓰면 됨 

  
  // HikariConfig : HikariCP를 이용해 DB에 접속할 때 필요한 정보를 처리하는 Hikari 클래스
  //위에서 다하고 HikariDataSource 여기서는 불러들이기만 하면 됨
  
  @Bean
  public HikariConfig hikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));
    hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
    hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
    hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
    return hikariConfig;
  }
  
  // HikariDataSource : CP(Connection Pool)을 처리하는 javax.sql.DataSource 인터페이스
  @Bean
  public HikariDataSource hikariDataSource() {
    return new HikariDataSource(hikariConfig());
  }
  
  // SqlSessionFactory : SqlSessionTemplate을 만들기 위한 mybatis 인터페이스
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(hikariDataSource());
    sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location")));
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
    return sqlSessionFactoryBean.getObject();
  }
  
  // SqlSessionTemplate : 쿼리 실행을 담당하는 mybatis 클래스
  @Bean
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory());
  }
  

  // TransactionManager : 트랜잭션을 처리하는 스프링 인터페이스
  @Bean
  public TransactionManager transactionManager() {
    return new DataSourceTransactionManager(hikariDataSource());
  }
  
}