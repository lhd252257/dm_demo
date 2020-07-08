package com.bifangan.dmDemo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bifangan.dmDemo.entity.TDept;
import com.bifangan.dmDemo.global.CounterGlobal;
import com.bifangan.dmDemo.mapper.TDeptMapper;
import com.bifangan.dmDemo.service.TDeptService;

/**
 * 部门(寝室)
 *
 * @author lihongda
 * @date 2020-07-02 16:42:26
 */
@Service
@Primary
public class TDeptServiceImpl extends ServiceImpl<TDeptMapper, TDept> implements TDeptService {

	@Override
	public boolean save(TDept entity) {
		return super.save(entity);
	}
	
}
