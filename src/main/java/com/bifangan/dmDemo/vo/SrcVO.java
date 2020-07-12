package com.bifangan.dmDemo.vo;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class SrcVO {

	/**
	 * 抓拍照片base64
	 */
	private String image;
	/**
	 * 人脸照片ID
	 */
	private String faceid;
	/**
	 * 记录时间
	 */
	private String record_time;
	/**
	 * 温度
	 */
	@JSONField(name = "TEMP")
	private String TEMP;
	
}
