package com.bifangan.dmDemo.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bifangan.dmDemo.entity.TDept;

/**
 * 部门(寝室)
 *
 * @author lihongda
 * @date 2020-07-02 16:42:26
 */
@Repository
public interface TDeptMapper extends BaseMapper<TDept> {
	
	public String getInFaceMachineIPByUserDeptId(String userId);
	
}
