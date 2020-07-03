package com.bifangan.dmDemo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bifangan.dmDemo.common.R;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.service.TRegFaceUserService;


/**
 * 注册人脸用户
 *
 * @author pigx code generator
 * @date 2020-07-02 16:42:43
 */
@RestController
@RequestMapping("/tregfaceuser" )
public class TRegFaceUserController {

    private TRegFaceUserService tRegFaceUserService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param tRegFaceUser 注册人脸用户
     * @return
     */
    @GetMapping("/page" )
    public R getTRegFaceUserPage(Page page, TRegFaceUser tRegFaceUser) {
        return new R<>(tRegFaceUserService.page(page, Wrappers.query(tRegFaceUser)));
    }


    /**
     * 通过id查询注册人脸用户
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(tRegFaceUserService.getById(id));
    }

    /**
     * 新增注册人脸用户
     * @param tRegFaceUser 注册人脸用户
     * @return R
     */
    @PostMapping
    public R save(@RequestBody TRegFaceUser tRegFaceUser) {
        return new R<>(tRegFaceUserService.save(tRegFaceUser));
    }

    /**
     * 修改注册人脸用户
     * @param tRegFaceUser 注册人脸用户
     * @return R
     */
    @PutMapping
    public R updateById(@RequestBody TRegFaceUser tRegFaceUser) {
        return new R<>(tRegFaceUserService.updateById(tRegFaceUser));
    }

    /**
     * 通过id删除注册人脸用户
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable String id) {
        return new R<>(tRegFaceUserService.removeById(id));
    }

}
