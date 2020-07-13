package com.bifangan.dmDemo.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class LineStatus {

	private Integer LineNo;
	
	private Integer Status;

	@JSONField(name = "LineNo")
	public Integer getLineNo() {
		return LineNo;
	}

	public void setLineNo(Integer lineNo) {
		LineNo = lineNo;
	}
	@JSONField(name = "Status")
	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}
	
}
