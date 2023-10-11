package com.gdu.app10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import com.gdu.app10.aop.AfterAop;
import com.gdu.app10.aop.AroundAop;
import com.gdu.app10.aop.BeforeAop;

@EnableAspectJAutoProxy // beboreAop에 쓰기 위해 여따 작성 아래다 작성 -> Bean으로 만들어 두고 허용,,?
@Configuration
public class AppConfig {
  
  // 위에 3개 Bean은 앞으로 계속 챙겨다닐거임!!

  // DriverManagerDataSource : CP(Connection Pool)을 처리하는 스프링 클래스
  @Bean
  public DriverManagerDataSource driverManagerDataSource() {
    DriverManagerDataSource d = new DriverManagerDataSource();
    d.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
    d.setUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:xe");
    d.setUsername("GD");
    d.setPassword("1111");
    return d;
  }
  
  // JdbcTemplate : Jdbc를 처리하는 스프링 클래스(Connection, PreparedStatement, ResultSet 처리 담당)
  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(driverManagerDataSource());
  }
  
  // TransactionManager : 트랜잭션을 처리하는 스프링 인터페이스(클래스x)
  @Bean
  public TransactionManager transactionManager() {
    return new DataSourceTransactionManager(driverManagerDataSource());
  }
  
  
  // 우리가 만든클래스 bean 으로 만들 때 component -> beforeAop에 component 붙이고 이거 삭제해도 됨
  @Bean
  public BeforeAop beforeAop() {
    return new BeforeAop();
  }
  
  @Bean
  public AfterAop afterAop() {
    return new AfterAop();
  }
  
  // 화살표 갈고리 모양이 다른것 확인
  @Bean
  public AroundAop around() {
    return new AroundAop();
  }
  
  
}