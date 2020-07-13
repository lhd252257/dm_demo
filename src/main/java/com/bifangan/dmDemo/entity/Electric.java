package com.bifangan.dmDemo.entity;

import lombok.Data;

@Data
public class Electric {

    private String id;

    private String deviceId; //设备 ID

    private String controllerId; //控制器 ID

    private String name; // 设备名称

    private int connect; //在线状态

    private String sn; //设备编码
}
