package com.gdu.myhome.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.myhome.dao.UploadMapper;
import com.gdu.myhome.dto.AttachDto;
import com.gdu.myhome.dto.UploadDto;
import com.gdu.myhome.dto.UserDto;
import com.gdu.myhome.util.MyFileUtils;
import com.gdu.myhome.util.MyPageUtils;

import lombok.RequiredArgsConstructor;
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
  
  @Transactional(readOnly = true)
  @Override
  public Map<String, Object> getUploadList(HttpServletRequest request) {
    //페에지 번호 안올 것을 대비 
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    int total = uploadMapper.getUploadCount();
    int display = 9; // 3 * 3
    
    myPageUtils.setPaging(page, total, display);
    
    Map<String, Object> map = Map.of("begin", myPageUtils.getBegin()
                                    , "end", myPageUtils.getEnd());
    
    List<UploadDto> uploadList = uploadMapper.getUploadList(map);
    
    // 나중에 확장하고 싶으면 map에 추가해서 실어서 등록하여 확장 할 수 있따...
    return Map.of("uploadList", uploadList
                , "totalPage", myPageUtils.getTotalPage());
  }
  
  @Transactional(readOnly=true)
  @Override
  public void loadUpload(HttpServletRequest request, Model model) {
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("uploadNo"));
    int uploadNo = Integer.parseInt(opt.orElse("0"));
    
    model.addAttribute("upload", uploadMapper.getUpload(uploadNo));
    model.addAttribute("attachList", uploadMapper.getAttachList(uploadNo));
    
  }
  
  @Override
  public ResponseEntity<Resource> download(HttpServletRequest request)  {

    // 첨부 파일의 정보 가져오기
    int attachNo = Integer.parseInt(request.getParameter("attachNo"));
    AttachDto attach = uploadMapper.getAttach(attachNo);
    
    // 첨부 파일 File 객체 -> Resource 객체
    File file = new File(attach.getPath(), attach.getFilesystemName());
    Resource resource = new FileSystemResource(file);
    
    // 첨부 파일이 없으면 다운로드 취소
    if(!resource.exists()) {
      return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
    }
    
    // 다운로드 횟수 증가하기
    uploadMapper.updateDownloadCount(attachNo);
    
    
    // 사용자가 다운로드 받을 파일의 이름 결정(User-Agent 값에 따른 인코딩 처리)
    String originalFilename = attach.getOriginalFilename();
    String userAgent = request.getHeader("User-Agent");
    
    
    try {
      
      // IE
      if(userAgent.contains("Trident")) {
        originalFilename = URLEncoder.encode(originalFilename, "UTF-8").replace("+", "");
      }
      
      // Edge
      else if(userAgent.contains("edg")) {
        originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
      }
      
      // Other
      else {
        originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
     
    // 다운로드 응답 헤더 만들기
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", "application/octet-stream");
    header.add("Content-Disposition", "attachment; filename=" + originalFilename);
    header.add("Content-Length", file.length() + "");
    
    
    // 응답
    return new ResponseEntity<Resource>(resource , header, HttpStatus.OK);
  
  }
  
  @Override
  public ResponseEntity<Resource> downloadAll(HttpServletRequest request) {
    
    
    // 다운로드 할 모든 첨부 파일 정보 가져오기 
    int uploadNo = Integer.parseInt(request.getParameter("uploadNo"));
    List<AttachDto> attachList = uploadMapper.getAttachList(uploadNo);
    
    // 첨부 파일이 없으면 종료
    if(attachList.isEmpty()) {
      
      return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
    }
    
    
    // zip 파일을 생성할 경로
    File tempDir = new File(myFileUtils.getTempPath());
    if(!tempDir.exists()) {
      tempDir.mkdirs();
    }
    
    // zip 파일의 이름
    String zipName = myFileUtils.getTempFilename() + ".zip";
    
    // zip 파일의 File 객체 
    File zipFile = new File(tempDir, zipName);
    
    // zip 파일을 생성하는 출력 스트림
    ZipOutputStream zout = null;
    
    // 첨부 파일들을 순회하면서 zip 파일에 등록하기 
    try {
      
      // zip 파일의 파일 객체가 여기로 들어오는 거임..
     zout = new ZipOutputStream(new FileOutputStream(zipFile));
     
     for(AttachDto attach : attachList) {
       
       // 각 첨부 파일들의 원래 이름으로  zip 파일에 등록하기 (이름만 등록)
       ZipEntry zipEntry = new ZipEntry(attach.getOriginalFilename());
       zout.putNextEntry(zipEntry);
       
       // 각 첨부 파일들의 내용을 zip 파일에 등록하기(실제 파일 등록)
       BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File(attach.getPath(), attach.getFilesystemName())));
       zout.write(bin.readAllBytes());
       
       // 자원 반납
       bin.close();
       zout.closeEntry();
       
       // 다운로드 횟수 증가 
       uploadMapper.updateDownloadCount(attach.getAttachNo());
       
     }
     
     // zout 자원 반납
     zout.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 다운로드할 zip 파일의 File 객체 -> Resource 객체
    Resource resource = new FileSystemResource(zipFile);
    
    // 다운로드 응답 헤더 만들기
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", "application/octet-stream");
    header.add("Content-Disposition", "attachment; filename=" + zipName);
    header.add("Content-Length", zipFile.length() + "");
    
    
    // 응답
    return new ResponseEntity<Resource>(resource , header, HttpStatus.OK);
    
    
  }
  
  
  
  
  
  
}
