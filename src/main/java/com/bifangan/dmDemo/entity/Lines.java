package com.bifangan.dmDemo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

public class Lines {
	
	private String $id;
	
	private String LineID; //线路
	 
	private Integer LineNo; //线路编号
	
	private Integer Line_Version; //线路版本
	
	private Integer Line_Status; //线路在线状态 1 在线 0 离线
	
	private String Name; //线路名称
	
	private Double Power; //功率
	
	private Double Voltage; //电压
	
	private Integer Status; //开关状态
	
	private String LineIcon; //线路图标
	
	private Double Max; //最大电流
	
	private Double Current; //当前电流
	
	private Double Limit; //最大月用电量
	
	private String Model; //产品型号
	
	private Double Temp; //当前温度
	
	private Integer Under; //欠压值
	
	private Integer Over; //过压值
	
	private Integer LeakValue; //漏电预警值
	
	private Integer Err_LeakValue; //漏电故障值
	
	private Double Leakage; //当前漏电值
	
	private Integer isLeakage; //区分产品是否带漏电
	
	private Integer Enabled; // 锁定物理开关
	
	private Integer Duration; //过流持续时间

	private BigDecimal Energy; //电能

	@JSONField(name = "Energy")
	public BigDecimal getEnergy() {
		return Energy;
	}
	public void setEnergy(BigDecimal energy) {
		Energy = energy;
	}
	@JSONField(name = "$id")
	public String get$id() {
		return $id;
	}

	public void set$id(String $id) {
		this.$id = $id;
	}

	@JSONField(name = "LineID")
	public String getLineID() {
		return LineID;
	}

	public void setLineID(String lineID) {
		LineID = lineID;
	}

	@JSONField(name = "LineNo")
	public Integer getLineNo() {
		return LineNo;
	}

	public void setLineNo(Integer lineNo) {
		LineNo = lineNo;
	}

	@JSONField(name = "Line_Version")
	public Integer getLine_Version() {
		return Line_Version;
	}

	public void setLine_Version(Integer line_Version) {
		Line_Version = line_Version;
	}

	@JSONField(name = "Line_Status")
	public Integer getLine_Status() {
		return Line_Status;
	}

	public void setLine_Status(Integer line_Status) {
		Line_Status = line_Status;
	}

	@JSONField(name = "Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@JSONField(name = "Power")
	public Double getPower() {
		return Power;
	}

	public void setPower(Double power) {
		Power = power;
	}

	@JSONField(name = "Voltage")
	public Double getVoltage() {
		return Voltage;
	}

	public void setVoltage(Double voltage) {
		Voltage = voltage;
	}

	@JSONField(name = "Status")
	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	@JSONField(name = "LineIcon")
	public String getLineIcon() {
		return LineIcon;
	}

	public void setLineIcon(String lineIcon) {
		LineIcon = lineIcon;
	}

	@JSONField(name = "Max")
	public Double getMax() {
		return Max;
	}

	public void setMax(Double max) {
		Max = max;
	}

	@JSONField(name = "Current")
	public Double getCurrent() {
		return Current;
	}

	public void setCurrent(Double current) {
		Current = current;
	}

	@JSONField(name = "Limit")
	public Double getLimit() {
		return Limit;
	}

	public void setLimit(Double limit) {
		Limit = limit;
	}

	@JSONField(name = "Model")
	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	@JSONField(name = "Temp")
	public Double getTemp() {
		return Temp;
	}

	public void setTemp(Double temp) {
		Temp = temp;
	}

	@JSONField(name = "Under")
	public Integer getUnder() {
		return Under;
	}

	public void setUnder(Integer under) {
		Under = under;
	}

	@JSONField(name = "Over")
	public Integer getOver() {
		return Over;
	}

	public void setOver(Integer over) {
		Over = over;
	}

	@JSONField(name = "LeakValue")
	public Integer getLeakValue() {
		return LeakValue;
	}

	public void setLeakValue(Integer leakValue) {
		LeakValue = leakValue;
	}

	@JSONField(name = "Err_LeakValue")
	public Integer getErr_LeakValue() {
		return Err_LeakValue;
	}

	public void setErr_LeakValue(Integer err_LeakValue) {
		Err_LeakValue = err_LeakValue;
	}

	@JSONField(name = "Leakage")
	public Double getLeakage() {
		return Leakage;
	}

	public void setLeakage(Double leakage) {
		Leakage = leakage;
	}

	@JSONField(name = "isLeakage")
	public Integer getIsLeakage() {
		return isLeakage;
	}

	public void setIsLeakage(Integer isLeakage) {
		this.isLeakage = isLeakage;
	}

	@JSONField(name = "Enabled")
	public Integer getEnabled() {
		return Enabled;
	}

	public void setEnabled(Integer enabled) {
		Enabled = enabled;
	}

	@JSONField(name = "Duration")
	public Integer getDuration() {
		return Duration;
	}

	public void setDuration(Integer duration) {
		Duration = duration;
	}

	@Override
	public String toString() {
		return "Lines [$id=" + $id + ", LineID=" + LineID + ", LineNo="
				+ LineNo + ", Line_Version=" + Line_Version + ", Line_Status="
				+ Line_Status + ", Name=" + Name + ", Power=" + Power
				+ ", Voltage=" + Voltage + ", Status=" + Status + ", LineIcon="
				+ LineIcon + ", Max=" + Max + ", Current=" + Current
				+ ", Limit=" + Limit + ", Model=" + Model + ", Temp=" + Temp
				+ ", Under=" + Under + ", Over=" + Over + ", LeakValue="
				+ LeakValue + ", Err_LeakValue=" + Err_LeakValue + ", Leakage="
				+ Leakage + ", isLeakage=" + isLeakage + ", Enabled=" + Enabled
				+ ", Duration=" + Duration + "]";
	}
	

}
