package com.bifangan.dmDemo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注册人脸用户
 *
 * @author lihongda
 * @date 2020-07-02 16:42:43
 */
@Data
@TableName("t_reg_face_user")
@EqualsAndHashCode(callSuper = true)
public class TRegFaceUser extends Model<TRegFaceUser> implements Cloneable {
	private static final long serialVersionUID = 1L;

    /**
   * id
   */
    @TableId
    private String id;
    /**
   * 用户名
   */
    private String name;
    /**
   * 部门(寝室)id
   */
    private String deptId;
    /**
   * 部门(寝室)
   */
    private String dept;
    /**
   * 工号
   */
    private String jobNumber;
    /**
   * 电话
   */
    private String phone;
    /**
   * 门卡号码
   */
    private String gateCardNum;
    /**
   * 邮箱
   */
    private String email;
    /**
   * 地址
   */
    private String address;
    /**
   * 车牌
   */
    private String licensePlate;
    /**
   * 性别(1:男，0:女)
   */
    private Integer gender;
    /**
   * 是否考勤(1:是，0:否)
   */
    private Integer isTime;
    /**
   * 生日
   */
    private Date birthday;
    /**
   * 职位
   */
    private String position;
    /**
   * 证件号
   */
    private String idCard;
    /**
   * 通行时间
   */
    private String passingTime;
    /**
   * 有效次数
   */
    private Integer effectiveNum;
    /**
   * 照片地址
   */
    private String photo;
    /**
   * faceid
   */
    private String faceId;
    /**
   * 人脸机ip
   */
    private String faceMachineIp;
    /**
   * 是否是黑名单 1:是黑名单  0:不是黑名单
   */
    private Integer isBlacklist;
    /**
     * 用户微信的openId
     */
    private String openId;
  
    @Override
	public Object clone() throws CloneNotSupportedException {
    	// TODO Auto-generated method stub
    	return super.clone();
    }
    
}
