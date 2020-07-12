package com.bifangan.dmDemo.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceInfo {
	
	private String $id;
	
	private String DeviceID;
	
	private String ControllerID;
	
	private String Name;
	
	private String CategoryID;
	
	private String Lan;
	
	private String Connect;
	
	private List<Lines> Lines;
	
	
	@JSONField(name = "$id")
	public String get$id() {
		return $id;
	}

	public void set$id(String $id) {
		this.$id = $id;
	}
	
	@JSONField(name = "DeviceID")
	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	@JSONField(name = "ControllerID")
	public String getControllerID() {
		return ControllerID;
	}

	public void setControllerID(String controllerID) {
		ControllerID = controllerID;
	}

	@JSONField(name = "Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@JSONField(name = "CategoryID")
	public String getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}

	@JSONField(name = "Lan")
	public String getLan() {
		return Lan;
	}

	public void setLan(String lan) {
		Lan = lan;
	}

	@JSONField(name = "Connect")
	public String getConnect() {
		return Connect;
	}

	public void setConnect(String connect) {
		Connect = connect;
	}

	/*@JSONField(name = "Lines")*/
	public List<Lines> getLines() {
		return Lines;
	}

	public void setLines(List<Lines> lines) {
		Lines = lines;
	}

	@Override
	public String toString() {
		return "Device [$id=" + $id + ", DeviceID=" + DeviceID
				+ ", ControllerID=" + ControllerID + ", Name=" + Name
				+ ", CategoryID=" + CategoryID + ", Lan=" + Lan + ", Connect="
				+ Connect + ", Lines=" + Lines + "]";
	}
	
	
	
}
