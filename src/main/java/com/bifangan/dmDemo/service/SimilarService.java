package com.bifangan.dmDemo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bifangan.dmDemo.entity.Similar;
import com.bifangan.dmDemo.entity.TRegFaceUser;

public interface SimilarService extends IService<Similar>{

	 public List<TRegFaceUser> imageSimilar(MultipartFile file);

}
