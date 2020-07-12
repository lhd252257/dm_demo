package com.bifangan.dmDemo.entity;

import lombok.Data;

@Data
public class ElectricLine {

    private String id;

    private String lineId; //线路

    private Integer lineNo; //线路编号

    private Integer lineVersion; //线路版本

    private Integer lineStatus; //线路在线状态 1 在线 0 离线

    private String name; //线路名称

    private Double power; //功率

    private Double voltage; //电压

    private Integer status; //开关状态

   /* private String LineIcon; //线路图标

    private Double Max; //最大电流*/

    private Double current; //当前电流

   /* private Double Limit; //最大月用电量

    private String Model; //产品型号*/

    private Double temp; //当前温度

    /*private Integer Under; //欠压值

    private Integer Over; //过压值

    private Integer LeakValue; //漏电预警值

    private Integer Err_LeakValue; //漏电故障值*/

    private Double leakage; //当前漏电值

   /* private Integer isLeakage; //区分产品是否带漏电

    private Integer Enabled; // 锁定物理开关

    private Integer Duration; //过流持续时间*/

   private String lid;

}
