package com.bifangan.dmDemo.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static boolean upload(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();
        String filePath = "F:\\Temp\\upload";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return false;
    }

	
}
