package com.gdu.app10.service;

import java.util.List;

import com.gdu.app10.dto.ContactDto;

public interface ContactService {
  
  public int addContact(ContactDto contactDto);
  public int modifyContact(ContactDto contactDto);
  public int deleteContact(int contact_no);
  public List<ContactDto> getContactList();  // 원래는 페이지값 전달해줘야함
  public ContactDto getContactByNo(int contact_no);

}
