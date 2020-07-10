package com.bifangan.dmDemo.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static String upload(MultipartFile file) {
        if (file.isEmpty()) {
            return "null";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "F:\\Temp\\upload\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return filePath + fileName;
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return "error";
    }

	
}
