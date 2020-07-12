package com.bifangan.dmDemo.vo;

import org.springframework.web.multipart.MultipartFile;

import com.bifangan.dmDemo.entity.TRegFaceUser;

import lombok.Data;

@Data
public class RegFaceUserVO extends TRegFaceUser {

	    
		/**
	     * 照片文件
	     */
	    private MultipartFile photoFile;
	    
	    private String inFaceDevice;
	    
	    private String outFaceDevice;
	    
}
