package com.bifangan.dmDemo.vo;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class FaceDeviceAuthVO {

	/**
	 * 设备码如：“001234567”
	 */
	@JSONField(name = "ID")
	private String ID;
	
	/**
	 * 指令 70/71/32
	 */
	private Integer drivers;
	
	/**
	 * 保留
	 */
	private int no;
	
	/**
	 * 数据包JSON
	 */
	private SrcVO src;
}
