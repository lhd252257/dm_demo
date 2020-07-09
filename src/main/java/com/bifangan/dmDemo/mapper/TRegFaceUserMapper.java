package com.bifangan.dmDemo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bifangan.dmDemo.entity.TRegFaceUser;

/**
 * 注册人脸用户
 *
 * @author lihongda
 * @date 2020-07-02 16:42:43
 */
@Repository
public interface TRegFaceUserMapper extends BaseMapper<TRegFaceUser> {

	public List<TRegFaceUser> textFullSearchUser(String content);
	
}
