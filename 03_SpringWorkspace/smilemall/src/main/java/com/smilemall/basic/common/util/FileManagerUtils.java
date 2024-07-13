package com.smilemall.basic.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;



public class FileManagerUtils {

	// 기능1) 현재 폴더를 운영체제에 맞게 문자열로 변환
	public static String getDateFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 원하는 날짜 패턴
		Date date = new Date(); //오늘 날짜 정보
		
		String str = sdf.format(date); // 오늘 날짜 정보를 원하는 날짜 패턴으로 변환
		
		return str.replace("-", File.separator); //출력한 오늘 날짜 정보 구분자를 운영체제에 맞는 구분자로 변환
	}
	
	
	// 기능2) 업로드 기능
	public static String uploadFile(String uploadFolder, String dateFolder, MultipartFile uploadFile) {
		
		//실제 업로드 파일명
		String realUploadFileName = "";
		
		//uploadFolder경로에 dateFolder 생성
		File file = new File(uploadFolder, dateFolder);
		
		//폴더가 존재 하지 않으면
		if(file.exists() == false) {
			file.mkdirs(); // 상위 폴더 까지 생성
		}
		
		// 클라이언트에서 보낸 파일명
		String clientFileName = uploadFile.getOriginalFilename();
		UUID uuid = UUID.randomUUID(); // 랜덤값 생성
		
		//사용자가 올린 파일의 앞에 랜덥 값 붙여서 실제업로드할 파일로 사용
		realUploadFileName = uuid.toString() + "-" + clientFileName;
		
		//예외 처리
		try {
			// dateFolder경로에 실제 업로드한 파일 생성
			File saveFile = new File(file, realUploadFileName);
			uploadFile.transferTo(saveFile);  //업로드한 파일데이터를 지정한 파일에 저장한다.
			
			if(checkImageType(saveFile)) {
				
				//썸네일 파일은 dateFolder경로에 실제 업로드한 파일 이름 앞에 "s_"를 붙여서 생성
				File thumbnailFile = new File(file, "s_" + realUploadFileName);
				
				//save파일을 읽어와서 bo_img에 대입, BufferedImage :이미지데이터를 메모리에 저장하고 조작 할 수 있다.
				BufferedImage bo_img = ImageIO.read(saveFile);
				
				BufferedImage bt_img = new BufferedImage(300, 300, BufferedImage.TYPE_3BYTE_BGR);
			
				double ratio = 3;
				int width = (int) (bo_img.getWidth() / ratio);
				int height = (int) (bo_img.getHeight() / ratio);
				
				Thumbnails.of(saveFile) 
						  .size(width, height)
						  .toFile(thumbnailFile);  
				
				}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return realUploadFileName;
	}
	
	
	// 기능3) 업로드한 파일의 MIME타입 확인. 이미지, 일반파일 여부 체크
	public static boolean checkImageType(File saveFile) {
		
		boolean isImageType = false;
		
		try {
			//saveFile의 경로를 추출하고, Mime 타입 확인 후 contentType에 대입
			String contentType = Files.probeContentType(saveFile.toPath());
			
			//contentType변수 내용이 "image"문자열로 시작하는지 여부 boolean값 반환
			isImageType = contentType.startsWith("image");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return isImageType;
	}
	
	
	// 기능4) 이미지 파일 웹브라우저 화면에 표시
	public static ResponseEntity<byte[]> getFile(String uploadPath, String fileName) throws Exception {
		
		ResponseEntity<byte[]> entity = null;
		
		//uploadPath에 fileName생성
		File file = new File(uploadPath, fileName);
		
		//fileName이 존재하지 않으면 null값 반환
		if(!file.exists()) {
			return entity;
		}
		
		HttpHeaders headers = new HttpHeaders();
		// 헤더에 Content-Type 이름으로 file의 mime타입 추가
		headers.add("Content-Type", Files.probeContentType(file.toPath()));
		
		//경로, 헤더값, 상태값 반환
		entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		
		return entity;
	}
	
	
	// 기능5) 이미지 파일 삭제
	public static void delete(String uploadPath, String dateFolderName, String fileName, String type) {
		
		File realUploadFile = new File((uploadPath + "\\" + dateFolderName + "\\" + fileName.substring(2)).replace('\\', File.separatorChar));
		if(realUploadFile.exists()) realUploadFile.delete();
		
		if(type.equals("image")) {
			File thumbnailFile = new File((uploadPath + "\\" + dateFolderName + "\\" + fileName).replace('\\', File.separatorChar));
			if(thumbnailFile.exists()) thumbnailFile.delete();
					
		}
	}
	
}
