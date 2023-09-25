package com.gdu.app02.xml02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyJdbcService {
  
  // 서비스는 다오를 필요로 하니까 여따 선언해줘야함..?
  // 세터로 값을 전달 할 수 있게 롬복!
  
  private MyJdbcDao myJdbcDao;
  
  public void add() {
    myJdbcDao.add();
  }
  
  public void remove() {
    myJdbcDao.remove();
  }
  
  public void modify() {
    myJdbcDao.modify();
  }

  public void select() {
    myJdbcDao.select();
  }
  
  
}
