package com.gdu.app11;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

// JUnit4 사용한다.
@RunWith(SpringJUnit4ClassRunner.class)

// 테스트에서 사용할 Bean이 @Component로 생성된다.
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" )

// 메소드 이름 순으로 테스트를 수행한다.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // 메소드의 알파벳 순으로 실행

// WebApplicationContext를 사용할 수 있다. (Spring Container에 Bean을 생성해 둔다.)
@WebAppConfiguration  // 톰캣필요 - 웹기능 사용

// 로그 찍기
@Slf4j

public class ContactControllerTest {

  // mock이 webApplicationContext얘를 필요로 한다 그래서 만듬
  
  // MockMvc 객체가 생성될 때 필요한 WepApplicationContext
  @Autowired
  private WebApplicationContext webApplicationContext;
  
  // MocMvc 객체 : 테스트를 수행하는 객체
  private MockMvc mockMvc;
  
  // 테스트 수행 이전에 MockMvc mockMvc 객체를 만든다.
  @Before  
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }
  
  // 삽입 테스트
  // 요청 방식 : POST
  // 요청 주소 : /contact/add.do
  // 요청 파라미터 : name, tel, email, address
  @Test
  public void test01_삽입() throws Exception {
    log.info(mockMvc
              .perform(MockMvcRequestBuilders
                  .post("/contact/add.do")              // 요청 수행
                  .param("name","뽀로로")               // 요청 방식, 주소
                  .param("tel", "02-1111-1111")         // 요청 파라미터
                  .param("email", "1@naver.com")        
                  .param("address", "proro villiage"))
              .andReturn()                              // 요청 결과
              .getFlashMap()                            // 요청 결과가 저장된 flash - 설명 추가 
              .toString());
  }
  
  // 상세조회테스트
  // 요청 방식 : GET
  // 요청 주소 : /contact/detail.do
  // 요청 파라미터 : contactNo
  @Test
  public void test02_상세조회() throws Exception {  // 스프링 초창기에 쓰던 getModelAndView() 반환까지!
    log.info(mockMvc
               .perform(MockMvcRequestBuilders
                   .get("/contact/detail.do")
                   .param("contactNo", "1"))
               .andReturn()
               .getModelAndView()
               .getModelMap()
               .toString());
  }
  
}
