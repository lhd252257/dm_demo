package com.bifangan.dmDemo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static String upload(MultipartFile file) {
        if (file.isEmpty()) {
            return "null";
        }

        String fileName = file.getOriginalFilename();
        try {
			String filePath = ResourceUtils.getURL("classpath:").getPath()+"static/photo/";
			File dest = new File(filePath + fileName);
			try {
	            file.transferTo(dest);
	            return "photo/" + fileName;
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return "error";
    }

	public static void main(String[] args) {
		try {
			String filePath = ResourceUtils.getURL("classpath:").getPath()+"static/photo/";
			System.out.println(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
