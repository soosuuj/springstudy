package com.gdu.app13.util;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component  // MyFileUtil 객체로 만들어  불러다 쓰기 @autowired
public class MyFileUtil {
    
  // 파일이 저장될 경로 반환하기 - 두고두고 쓸 수 있음!!
  public String getPath() {   // 실프로젝트에서는 path 여러개가 만들어질 수 있음
    /*  /storage/yyyy/MM/dd - 날짜 별로 저장(저장되는 위치 하루에 안번씩 바뀜) */
    
    // 윈도우 경로 \  -> 자바에서는 \\ 로 인식시킴 리눅스에서 경로구분자는 / 
    // 다행이 윈도우 개발도 / 인식됨!! -> / 이걸로 작업하자!!
    
    LocalDate today = LocalDate.now();  // 오늘
    return "/storage/" + today.getYear() + "/" + String.format("%02d", today.getMonthValue()) + "/" + String.format("%02d", today.getDayOfMonth());
   
    // return "/storage/" + DateTimeFormatter.ofPattern("yyyy/MM/dd").format(today);
  }
  
  // 파일이 저장될 이름 반환하기 
  //올릴때 이름은 같아도 (a.txt 파일 3개 업로드) 실제 저장될 때 이름은 달라야함
  //db에 저장시키기 떄문에 찾는데 문제 없음
  public String getFilesystemName(String originalName) {
    
    // 확장자 꺼내기 UUID universial unique id , 중복 없는 이름 만들기
   
    /* UUID.확장자 */
    
    
    String extName = null;
    if(originalName.endsWith("tar.gz")) {  // 확장자의 마침표가 포함되는 예외 경우를 처리한다. gz는 리눅스 압축파일 확장자 - 구글링 하면 더나옴 else if 로 연결
      extName = "tar.gz";
    } else {                                      // [.] 또는 \. (자바에서는 \\. ) 마침표 하나 덜렁 쓰면 안됨
      String[] arr = originalName.split("\\.");   // regex 정규식 마침표는 모든 문자라는 메타문자 성격, 우리는 진짜 마침표라고 지정해줘야함
      extName = arr[arr.length - 1];              // 배열 시작은 0, 마지막은 길이-1
    }
    // 하이픈 계속 들어가니까 찾아서 빈 문자열로 바꿔주기
    return UUID.randomUUID().toString().replace("-", "") + "." + extName;
    
  }
   
  
}
