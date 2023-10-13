package com.gdu.app09;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app09.dao.ContactDao;
import com.gdu.app09.dto.ContactDto;

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


// JUnit4를 이용한다.
@RunWith(SpringJUnit4ClassRunner.class)

// ConnectDao Bean 생성 방법을 알려준다.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")


// 테스트 메소드의 이름 오름차순(알파벳순)으로 테스트를 수행한다.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactUnitTest {
  
 @Autowired // Spring Container에서 connectDao 객체(Bean)를 가져온다.
 private ContactDao contactDao;
 

  @Test  // 테스트를 수행한다.
  public void test01_삽입테스트() {
    ContactDto contactDto = new ContactDto(0, "이름", "연락처", "이메일", "주소", "");
    int insertResult = contactDao.insert(contactDto);
    assertEquals(1, insertResult); // insertResult가 1이면 test 성공
  }
  
  @Test  // 테스트 수행
  public void test02_조회테스트() {
    int contact_no = 1;
    ContactDto  contactDto = contactDao.selectContactByNo(contact_no);
    assertNotNull(contactDto);  // contactDtork not null 이면 테스트 성공이다.
  }
  
  @Test
  public void test03_0수정테스트() {
    int contact_no = 1; // 수정할 연락처 번호
    ContactDto contactDto = new ContactDto(contact_no, "수정이름", "수정연락처", "수정이메일", "수정주소", "");
    int modifyResult = contactDao.update(contactDto);
    assertEquals(1, modifyResult);
  }
  
  @Test
  public void test04_0목록조회테스트() {
      List<ContactDto> contactList = contactDao.selectList();
      assertNotNull(contactList);
      assertTrue(contactList.size() > 0); // 목록이 비어있지 않아야 함
  }

  @Test
  public void test05_0상세조회테스트() {
      int contact_no = 1; // 조회할 연락처 번호
      ContactDto contact = contactDao.selectContactByNo(contact_no);
      assertNotNull(contact);
      assertEquals(contact_no, contact.getContact_no());
  }

  
  @Test  // 테스트 수행
  public void test06_삭제테스트() {
    int contact_no = 1;
    int deleteResult  = contactDao.delete(contact_no);
    assertEquals(1, deleteResult);  // deleteResult가 1이면 test 성공
    // assertNull(contactDao.selectContactByNo(contact_no)); select 결과가 null이면 테스트 성공이다. 
  }
  
  


}
