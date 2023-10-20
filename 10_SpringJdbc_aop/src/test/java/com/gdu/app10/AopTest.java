package com.gdu.app10;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app10.dao.ContactDao;
import com.gdu.app10.dto.ContactDto;

// JUnit4 이용
@RunWith(SpringJUnit4ClassRunner.class)

// Dao의 Bean 생성 방법을 알려준다.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")


// 테스트 메소드의 이름 오름차순(알파벳순)으로 테스트를 수행한다.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AopTest {
  
  /*
   * JUnit  처리 방법
   * 1. spring-test dependency를 추가한다.
   * 2. @RunWith를 추가한다
   * 3. @ContextConfiguration을 추가한다.
   *    ContactDao 객체(bean)을 생성한 방법에 따라서 아래 3가지 방식 중 선택한다.
   *  1) <bean> 태그 : @ContextConfiguration(locations = "src/main/webapp/WEB-INF/spring/root-context.xml")
   *  2) @Bean       : @ContextConfiguration(classes=AppConfig.class)
   *  3) @Conponent  : @ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
   */
  
  @Autowired
  private ContactDao contactDao;
  
  @Test
  public void test01_삽입테스트() {
    ContactDto contactDto = new ContactDto(0, "이름", "연락처", "이메일", "주소", "");
    int insertResult = contactDao.insert(contactDto);
    assertEquals(1, insertResult);
  }
  
  
  

}
