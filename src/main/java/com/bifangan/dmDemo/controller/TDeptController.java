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
import com.bifangan.dmDemo.entity.TDept;
import com.bifangan.dmDemo.service.TDeptService;


/**
 * 部门(寝室)
 *
 * @author lihongda
 * @date 2020-7-3 15:02:46
 */
@RestController
@RequestMapping("/tdept" )
public class TDeptController {
	@Autowired(required=true)
    private TDeptService tDeptService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param tDept 部门(寝室)
     * @return
     */
    @GetMapping("/page" )
    public R getTDeptPage(Page page, TDept tDept) {
        return new R<>(tDeptService.page(page, Wrappers.query(tDept)));
    }


    /**
     * 通过id查询部门(寝室)
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(tDeptService.getById(id));
    }

    /**
     * 新增部门(寝室)
     * @param tDept 部门(寝室)
     * @return R
     */
    @PostMapping
    public R save(@RequestBody TDept tDept) {
        return new R<>(tDeptService.save(tDept));
    }

    /**
     * 修改部门(寝室)
     * @param tDept 部门(寝室)
     * @return R
     */
    @PutMapping
    public R updateById(@RequestBody TDept tDept) {
        return new R<>(tDeptService.updateById(tDept));
    }

    /**
     * 通过id删除部门(寝室)
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable String id) {
        return new R<>(tDeptService.removeById(id));
    }

//    @RequestMapping("/relationFaceDevice")
//    public R relationFaceDevice(@PathVariable) {
//    	
//    }
    
}
