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
import com.bifangan.dmDemo.entity.TBedLogs;
import com.bifangan.dmDemo.service.TBedLogsService;


/**
 * 就寝日记表
 *
 * @author lihongda
 * @date 2020-7-3 14:46:01
 */
@RestController
@RequestMapping("/tbedlogs" )
public class TBedLogsController {

	@Autowired
    private TBedLogsService tBedLogsService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param tBedLogs 就寝日记表
     * @return
     */
    @GetMapping("/page" )
    public R getTBedLogsPage(Page page, TBedLogs tBedLogs) {
        return new R<>(tBedLogsService.page(page, Wrappers.query(tBedLogs)));
    }


    /**
     * 通过id查询就寝日记表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(tBedLogsService.getById(id));
    }

    /**
     * 新增就寝日记表
     * @param tBedLogs 就寝日记表
     * @return R
     */
    @PostMapping
    public R save(@RequestBody TBedLogs tBedLogs) {
        return new R<>(tBedLogsService.save(tBedLogs));
    }

    /**
     * 修改就寝日记表
     * @param tBedLogs 就寝日记表
     * @return R
     */
    @PutMapping
    public R updateById(@RequestBody TBedLogs tBedLogs) {
        return new R<>(tBedLogsService.updateById(tBedLogs));
    }

    /**
     * 通过id删除就寝日记表
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable String id) {
        return new R<>(tBedLogsService.removeById(id));
    }

    @GetMapping("/bedSituation")
    public R queryBedTime() {
    	return new R<>(tBedLogsService.list());
    }
}
