package com.bifangan.dmDemo.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bifangan.dmDemo.common.R;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.service.TRegFaceUserService;
import com.bifangan.dmDemo.vo.RegFaceUserVO;


/**
 * 注册人脸用户
 *
 * @author pigx code generator
 * @date 2020-07-02 16:42:43
 */
@RestController
@RequestMapping("/tregfaceuser" )
public class TRegFaceUserController {
	@Autowired(required=true)
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
    public R save(@RequestBody RegFaceUserVO tRegFaceUser) {
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

    
    
    @PostMapping("/blacklist")
    public R blacklist(@RequestBody Map param) {
    	return tRegFaceUserService.blacklist(param);
    }
    
    
    @RequestMapping("/updateExcel")
    public R updateExcel(@RequestParam(value="filename")MultipartFile file,HttpSession session) {
    	int result = 0;
		
		try {
			result = tRegFaceUserService.importUser(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result > 0){
			return new R<>("excel数据导入成功！");
		}else{
			return new R<>("excel数据导入失败！");
		}

    }
    
    @RequestMapping("/downloadExcel")
	@ResponseBody
	public R downloadExcel(HttpServletResponse response,HttpServletRequest request) {
		//方法一：直接下载路径下的文件模板
		try {
            //获取要下载的模板名称
            String fileName = "regFaceUserTemplate.xls";
            //设置要下载的文件的名称
            response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
            //通知客服文件的MIME类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //获取文件的路径
            String filePath = getClass().getResource("/templates/" + fileName).getPath();
            FileInputStream input = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            //修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
            response.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
            return new R<>("应用导入模板下载完成！");
        } catch (Exception ex) {
            return new R<>("应用导入模板下载失败！");
        }
    }
    
    /**
     * 刷新滞留和未归日志
     * @return
     */
    @RequestMapping("/refreshState")
    public R refreshState() {
    	return new R<>(tRegFaceUserService.refreshState());
    }
}
