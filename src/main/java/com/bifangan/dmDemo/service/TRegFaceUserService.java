package com.bifangan.dmDemo.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bifangan.dmDemo.common.R;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.vo.RegFaceUserVO;

/**
 * 注册人脸用户
 *
 * @author lihongda
 * @date 2020-07-02 16:42:43
 */
public interface TRegFaceUserService extends IService<TRegFaceUser> {

	public R blacklist(Map param);
	
	public boolean regUser(RegFaceUserVO user);

	boolean save(RegFaceUserVO user);
	
	public int importUser(MultipartFile file) throws IOException;
	
	public int refreshState();
}
