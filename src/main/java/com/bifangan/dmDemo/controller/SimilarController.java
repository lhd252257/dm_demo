package com.bifangan.dmDemo.controller;


import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bifangan.dmDemo.common.R;
import com.bifangan.dmDemo.service.SimilarService;
import com.bifangan.dmDemo.utils.image.similarity.ImageHanmingUtil;

/**
 * @author cailin
 * 20200306
 */
@RestController
@CrossOrigin
public class SimilarController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SimilarService similarService;

    /**
     * 计算两张图片的相似度
     *
     * @param source 原图url
     * @param candi  目标图url
     * @return 整形数字，数字小于10说明二者相似度越高
     */
    @PostMapping("/similar")
    public R imageSimilar(MultipartFile file) {
    	return new R<>(similarService.imageSimilar(file));
    }


    /**
     * 计算单图片的汉明码
     *
     * @param url 待计算的图片的url
     * @return 二级制的汉明码
     */
    @GetMapping("/hanmingcode")
    public String getHanMingDis(@RequestParam(value = "url", required = true) String url) {
        ImageHanmingUtil hanmingHash = new ImageHanmingUtil();
        String hanmingCode="";
        try {
            hanmingCode = hanmingHash.hash(new URL(url));
        } catch (Exception e) {
            logger.error("汉明码计算错误", e);
        }
        return hanmingCode;
    }


}
