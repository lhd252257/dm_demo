package com.bifangan.dmDemo.entity;



import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 就寝日记表
 *
 * @author lihongda
 * @date 2020-07-02 16:39:00
 */
@Data
@TableName("t_bed_logs")
@EqualsAndHashCode(callSuper = true)
public class TBedLogs extends Model<TBedLogs> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private String id;
    /**
   * 用户id
   */
    private String userId;
    /**
   * 用户名
   */
    private String userName;
    /**
   * 部门(寝室)id
   */
    private String deptId;
    /**
   * 部门(寝室)
   */
    private String dept;
    /**
   * faceid
   */
    private String faceid;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
   * 进出时间
   */
    private Date ioTime;
    /**
   * 进出标识(1:进，2:出)
   */
    private Integer ioFlag;
    /**
   * 就寝状态(1:在寝，2:晚归，3:滞留，4:未归)
   */
    private Integer bedState;
  
}
