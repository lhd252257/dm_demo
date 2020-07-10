package com.bifangan.dmDemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 人脸机设备表
 *
 * @author lihongda
 * @date 2020-07-02 16:42:33
 */
@Data
@TableName("t_face_device")
@EqualsAndHashCode(callSuper = true)
public class TFaceDevice extends Model<TFaceDevice> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private String id;
    /**
   * ip
   */
    private String ip;
    /**
   * 子掩码
   */
    private String subMask;
    /**
   * 网关地址
   */
    private String gatewayaddress;
    /**
   * 服务器地址
   */
    private String serverIp;
    /**
   * 服务器端口
   */
    private String serverPort;
    /**
   * 客户编号
   */
    private String custNum;
    /**
   * 设备区域
   */
    private String deviceArea;
    /**
   * NTP服务器IP
   */
    private String ntpServerIp;
    /**
   * 设备别名
   */
    private String deviceAlias;
    /**
   * 是否启动DHCP服务
   */
    private String isDhcpServer;
    /**
   * 通行时段
   */
    private LocalDateTime passageTime;
    /**
     * 判断进口人脸机还是出口人脸机(1:进，2:出)
     */
    private int isIo;
  
}
