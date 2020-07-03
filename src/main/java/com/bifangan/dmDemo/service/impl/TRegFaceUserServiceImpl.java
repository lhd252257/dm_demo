package com.bifangan.dmDemo.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.mapper.TRegFaceUserMapper;
import com.bifangan.dmDemo.service.TRegFaceUserService;

/**
 * 注册人脸用户
 *
 * @author lihongda
 * @date 2020-07-02 16:42:43
 */
@Service
public class TRegFaceUserServiceImpl extends ServiceImpl<TRegFaceUserMapper, TRegFaceUser> implements TRegFaceUserService {
}
