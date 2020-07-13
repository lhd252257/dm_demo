package com.bifangan.dmDemo.service;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bifangan.dmDemo.entity.Similar;
import com.bifangan.dmDemo.entity.TRegFaceUser;

public interface SimilarService extends IService<Similar>{

	 public TRegFaceUser imageSimilar(MultipartFile file);

}
