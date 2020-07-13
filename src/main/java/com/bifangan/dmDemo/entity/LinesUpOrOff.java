package com.bifangan.dmDemo.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class LinesUpOrOff {
	
	private String ControllerID;
	
	private List<LineStatus> Lines;

	@JSONField(name = "ControllerID")
	public String getControllerID() {
		return ControllerID;
	}

	public void setControllerID(String controllerID) {
		ControllerID = controllerID;
	}

	public List<LineStatus> getLines() {
		return Lines;
	}

	public void setLines(List<LineStatus> lines) {
		Lines = lines;
	}
	
	

}
