package com.bifangan.dmDemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 注册人脸用户
 *
 * @author lihongda
 * @date 2020-07-02 16:42:43
 */
@Repository
public interface TRegFaceUserMapper extends BaseMapper<com.bifangan.dmDemo.entity.TRegFaceUser> {

	public List<com.bifangan.dmDemo.entity.TRegFaceUser> textFullSearchUser(@Param("content") String content);
	
}
