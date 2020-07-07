package com.bifangan.dmDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bifangan.dmDemo.entity.TFaceDevice;
import com.bifangan.dmDemo.service.TFaceDeviceService;


/**
 * 人脸机设备表
 *
 * @author pigx code generator
 * @date 2020-07-02 16:42:33
 */
@RestController
@RequestMapping("/tfacedevice" )
public class TFaceDeviceController {
	@Autowired(required=true)
    private TFaceDeviceService tFaceDeviceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param tFaceDevice 人脸机设备表
     * @return
     */
    @GetMapping("/page" )
    public R getTFaceDevicePage(Page page, TFaceDevice tFaceDevice) {
        return new R<>(tFaceDeviceService.page(page, Wrappers.query(tFaceDevice)));
    }


    /**
     * 通过id查询人脸机设备表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(tFaceDeviceService.getById(id));
    }

    /**
     * 新增人脸机设备表
     * @param tFaceDevice 人脸机设备表
     * @return R
     */
    @PostMapping
    public R save(@RequestBody TFaceDevice tFaceDevice) {
        return new R<>(tFaceDeviceService.save(tFaceDevice));
    }

    /**
     * 修改人脸机设备表
     * @param tFaceDevice 人脸机设备表
     * @return R
     */
    @PutMapping
    public R updateById(@RequestBody TFaceDevice tFaceDevice) {
        return new R<>(tFaceDeviceService.updateById(tFaceDevice));
    }

    /**
     * 通过id删除人脸机设备表
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable String id) {
        return new R<>(tFaceDeviceService.removeById(id));
    }

}
