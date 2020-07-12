package com.bifangan.dmDemo.service.impl;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.mapper.TRegFaceUserMapper;
import com.bifangan.dmDemo.service.SimilarService;
import com.bifangan.dmDemo.utils.FileUploadUtil;
import com.bifangan.dmDemo.utils.image.similarity.ImageHanmingUtil;

@Service
public class SimilarServiceImpl implements SimilarService{
	
	@Autowired
	private TRegFaceUserMapper tRegFaceUserMapper;
	
	@Override
	public TRegFaceUser imageSimilar(MultipartFile file) {
		// TODU 
		TRegFaceUser result = null;
		
		String candi = FileUploadUtil.upload(file);
		if("error".equals(candi)) {
			return result;
		}
		
		QueryWrapper<TRegFaceUser> queryWrapper = new QueryWrapper<TRegFaceUser>();
		List<TRegFaceUser> userList = tRegFaceUserMapper.selectList(queryWrapper);
		if(userList != null && userList.isEmpty()) {
			ImageHanmingUtil hanmingHash = new ImageHanmingUtil();
			int maxDistence = 0;
			for(Iterator<TRegFaceUser> iterator = userList.iterator();iterator.hasNext();) {
				int distence = 0;
				TRegFaceUser user = iterator.next();
				try {
					distence = hanmingHash.distance(new URL("file://" + user.getPhoto()), new URL("file://" + candi));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(distence > maxDistence) {
					maxDistence = distence;
					result = user;
				}
			}
		}
		
		return result;
	}

}
