package com.demo.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dto.AttachmentDto;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UploadService {
	
	private final static String rootDirectory="c:/upload/image";
	
	public List<AttachmentDto> store(MultipartFile[] uploadFiles) {
		List<AttachmentDto> listOfAttachments = new ArrayList<>();

        String directoryPathByDate = getDirectoryPathByDate();
        File uploadPath = new File(rootDirectory, directoryPathByDate);

        createDirectory(uploadPath);

        for (MultipartFile multipartFile : uploadFiles) {
            String fileName = multipartFile.getOriginalFilename();

            AttachmentDto attachmentDto = AttachmentDto.builder()
                    .fileName(fileName)
                    .uploadPath(directoryPathByDate)
                    .build();
            
            String uuid = UUIDGenerator();
            fileName = uuid + "_" + fileName;
            attachmentDto.setUuid(uuid);

            File saveFile = new File(uploadPath, fileName);

            try {
                multipartFile.transferTo(saveFile);
                listOfAttachments.add(attachmentDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listOfAttachments;
    }
	
	private void createDirectory(File path) {
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    private String UUIDGenerator() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private String getDirectoryPathByDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateFormat = sdf.format(date);
        return dateFormat.replace("-", File.separator);
    }
}
