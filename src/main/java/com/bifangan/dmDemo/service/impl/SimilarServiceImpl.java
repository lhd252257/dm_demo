package com.bifangan.dmDemo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bifangan.dmDemo.entity.Similar;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.mapper.SimilarMapper;
import com.bifangan.dmDemo.mapper.TRegFaceUserMapper;
import com.bifangan.dmDemo.service.SimilarService;
import com.bifangan.dmDemo.utils.FileUploadUtil;
import com.bifangan.dmDemo.utils.image.similarity.ImageHanmingUtil;

@Service
public class SimilarServiceImpl extends ServiceImpl<SimilarMapper, Similar> implements SimilarService{
	
	@Autowired
	private TRegFaceUserMapper tRegFaceUserMapper;
	
	@Override
	public TRegFaceUser imageSimilar(MultipartFile file) {
		// TODU 
		TRegFaceUser result = null;
		
		QueryWrapper<TRegFaceUser> queryWrapper = new QueryWrapper<TRegFaceUser>();
		List<TRegFaceUser> userList = tRegFaceUserMapper.selectList(queryWrapper);
		if(userList != null && !userList.isEmpty()) {
			ImageHanmingUtil hanmingHash = new ImageHanmingUtil();
			int minDistence = 0;
			for(Iterator<TRegFaceUser> iterator = userList.iterator();iterator.hasNext();) {
				int distence = 0;
				TRegFaceUser user = iterator.next();
				if(user.getPhoto() == null) {
					continue;
				}
				File photo = new File(user.getPhoto());
				FileInputStream fileis = null;
				try {
					fileis = new FileInputStream(photo);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					distence = hanmingHash.distance(fileis, file.getInputStream());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(minDistence == 0) {
					minDistence = distence;
					result = user;
				}
				if(distence < minDistence) {
					minDistence = distence;
					result = user;
				}
			}
		}
		
		return result;
	}

}
