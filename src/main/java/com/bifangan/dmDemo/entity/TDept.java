package com.bifangan.dmDemo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门(寝室)
 *
 * @author lihongda
 * @date 2020-07-02 16:42:26
 */
@Data
@TableName("t_dept")
@EqualsAndHashCode(callSuper = true)
public class TDept extends Model<TDept> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private String id;
    /**
   * 名称
   */
    private String name;
    /**
   * 备注
   */
    private String remarks;
    /**
   * 父节点
   */
    private String parentId;
   
    /**
     * 进口人脸机ip
     */
    private String inFaceMachineIp;
    
    /**
     * 出口人脸机ip
     */
      private String outFaceMachineIp;
    /**
   * 库存计数器
   */
    private Integer counter;
    /**
     * 剩余电量
     */
    private Integer electricCount;
  
}
