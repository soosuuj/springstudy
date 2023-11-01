package com.gdu.myhome.service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.myhome.dao.UploadMapper;
import com.gdu.myhome.dto.AttachDto;
import com.gdu.myhome.dto.UploadDto;
import com.gdu.myhome.dto.UserDto;
import com.gdu.myhome.util.MyFileUtils;
import com.gdu.myhome.util.MyPageUtils;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

@Transactional
@RequiredArgsConstructor
@Service
public class UploadServiceImpl implements UploadService {
  
  private final UploadMapper uploadMapper;
  private final MyFileUtils myFileUtils;
  private final MyPageUtils myPageUtils;
  
              //insert 2개 돌릴 때 트랜잭션 처리
  @Override
  public boolean addUpload(MultipartHttpServletRequest multipartRequest) throws Exception {

    // 3개 jsp에서 가져옴
    String title = multipartRequest.getParameter("title");
    String contents = multipartRequest.getParameter("contents");
    int userNo = Integer.parseInt(multipartRequest.getParameter("userNo"));
    
    UploadDto upload = UploadDto.builder()
                            .title(title)
                            .contents(contents)
                            .userDto(UserDto.builder()
                                         .userNo(userNo)
                                         .build())
                            .build();
    
    int uploadCount = uploadMapper.insertUpload(upload);
    
    // 다중첨부 multiple jsp에서 보내주는 files
    List<MultipartFile> files = multipartRequest.getFiles("files");
    
    // 주석 추가하기 
    // 추가 추가 
    
    int attachCount;
    // 첫번째 요소의 여러가지를 가져다가 확인을 할 수 있음 사이즈 .getSize() == 0 // .getOriginalFilename().isEmpty()
    if(files.get(0).getSize() == 0) {
      attachCount = 1; 
    } else {
      attachCount = 0;
    }
    
    // 한개씩 꺼내는 향상for
    for(MultipartFile multipartFile : files) {
      if(multipartFile != null && !multipartFile.isEmpty()) {
       
        String path = myFileUtils.getUploadPath();
        File dir = new File(path);
        if(!dir.exists()) {
          dir.mkdirs();
        }
        
        String originalFilename = multipartFile.getOriginalFilename();
        String filesystemName = myFileUtils.getFilesystemName(originalFilename);
        File file = new File(dir, filesystemName);
        
        // 실제 저장 코드 - 예외... 던짐 - 서버에 파일 저장ㅇㅇ 업로드 폴더 밑에 년월일
        multipartFile.transferTo(file);
        
        // 썸네일 여부 확인. 썸네일 만드는 코드
        String contentType = Files.probeContentType(file.toPath()) ; // 이미지의 Content-Type : image.jpeg, image.png 등 image로 시작한다. 
        int hasTumbnail = (contentType != null && contentType.startsWith("image")) ? 1 : 0;
        
        if(hasTumbnail == 1) {
          // 썸네일 제작 - 디펜던시 활용 - 
          File thumbnail = new File(dir,"s_" + filesystemName);  // small 이미지를 의미하는 s_을 덧붙임
          Thumbnails.of(file)             // 원본
                      .size(100, 100)     // 가로 100px, 세로 100px
                      .toFile(thumbnail);
      
        }
        
        AttachDto attach = AttachDto.builder()
                               .path(path)
                               .originalFilename(originalFilename)
                               .filesystemName(filesystemName)
                               .hasThumbnail(hasTumbnail)
                               .uploadNo(upload.getUploadNo())
                               .build();
        
        // 첨부 될 때만 돌아야함.. 행여나 if 밖에 나갔다?  - 첨부가 없어서 첨부추가 안됨
       // 개수를 구해서 되는지 안되는지 확인 할 수 있음..
        attachCount += uploadMapper.insertAttach(attach); // 삽입 / 반복문 안에서 여러번 반복 중... 덮어써지고 있다!!
        
      }  // if
      
    }    // for
    
    return ( uploadCount == 1 ) && (files.size() == attachCount);
  }
  
  
  
  // 첨부가 없으면 attachCount = 1로 잡음, 
  // 첨부가 한개일 떄 위에가 0으로 시작, 실제로 첨부하면서 1이 됨... 
  // 파일의 사이즈 카운드 1???  
  // 2개부터는 개수가 맞아서 고려할 필요 없음... 
  
  

}
