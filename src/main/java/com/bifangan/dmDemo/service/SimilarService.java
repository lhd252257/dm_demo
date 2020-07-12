package com.bifangan.dmDemo.service;

import org.springframework.web.multipart.MultipartFile;

import com.bifangan.dmDemo.entity.TRegFaceUser;

public interface SimilarService {

	 public TRegFaceUser imageSimilar(MultipartFile file);

}
