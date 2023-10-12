 package com.gdu.app11.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app11.dao.ContactDao;
import com.gdu.app11.dto.ContactDto;

import lombok.RequiredArgsConstructor;

@Transactional             // @EnableTransactionManagement  AppConfig에 작성 - tx.do 로 데이터 넣어도 입력 xx 원자성 지킴
@RequiredArgsConstructor   // private final ContactDao contactDao;에 @Autowired를 하기위한 코드이다.
@Service          // ContactService타입의 객체(Bean)을 Spring Container에 저장한다.
public class ContactServiceImpl implements ContactService {
  
  
  private final ContactDao contactDao;
  
  @Override
  public int addContact(ContactDto contactDto) {
    int addResult = contactDao.insert(contactDto);
    return addResult;
  }

  @Override
  public int modifyContact(ContactDto contactDto) {
    int modifyResult = contactDao.update(contactDto);
    return modifyResult;
  }

  @Override
  public int deleteContact(int contactNo) {
    int deleteResult  = contactDao.delete(contactNo);
    return deleteResult;
  }

  @Transactional(readOnly=true) // 조회용(성능 이점을 위해 붙임)
  @Override  // 트랜잭션 필요 없음
  public List<ContactDto> getContactList() {
    // TODO Auto-generated method stub
    return contactDao.selectList();
  }

  @Transactional(readOnly=true)  // db 수정이 없는 select들에게 작성
  @Override 
  public ContactDto getContactByNo(int contactNo) {
    return contactDao.selectContactByNo(contactNo);
  }
  
    
  
  @Override
  public void txTest() {
    
    // @Transactional AOP를 활용한 트랜잭션 처리 테스트 메소드
    
    
    // 성공1개 + 실패1개  DB처리를 동시에 수행 했을 떄 모두 실패로 되는지 확인
    
    
    // 성공  -> 지워지면 성공?
    contactDao.insert(new ContactDto(0, "이름", "전화번호", "이메일", "주소", null));
    
    // 실패
    contactDao.insert(new ContactDto());  // name 칼럼은 NOTNULL이므로 전달된 이름이 없으면 Exception이 발생한다.
    
  }
  
  

}
