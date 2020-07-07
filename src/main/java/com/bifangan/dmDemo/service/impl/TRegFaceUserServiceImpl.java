package com.bifangan.dmDemo.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bifangan.dmDemo.common.R;
import com.bifangan.dmDemo.constant.CommonConstants;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.mapper.TRegFaceUserMapper;
import com.bifangan.dmDemo.service.TRegFaceUserService;
import com.bifangan.dmDemo.utils.FileUploadUtil;
import com.bifangan.dmDemo.utils.HttpUtil;
import com.bifangan.dmDemo.utils.ImageBase64Converter;
import com.bifangan.dmDemo.vo.RegFaceUserVO;

/**
 * 注册人脸用户
 *
 * @author lihongda
 * @date 2020-07-02 16:42:43
 */
@Service
@Primary
public class TRegFaceUserServiceImpl extends ServiceImpl<TRegFaceUserMapper, TRegFaceUser> implements TRegFaceUserService {

	@Autowired
	private TRegFaceUserMapper tRegFaceUserMapper;
	
	@Override
	public R blacklist(Map param) {
		QueryWrapper<TRegFaceUser> query = new QueryWrapper<>();
		query.eq("faceid", param.get("faceid"));
		TRegFaceUser faceUser = tRegFaceUserMapper.selectOne(query);
		
		R result = new R();
		
		if(faceUser != null) {
			if(faceUser.getIsBlacklist() == 1) {
				result.setCode(0);
			} else {
				result.setCode(1);
			}
		}
		return result;
	}

	@Override
	public boolean save(RegFaceUserVO user) {
		/*
		 * Map param = new HashMap(); param.put("id_card", user.getIdCard());
		 */
		QueryWrapper<TRegFaceUser> query = new QueryWrapper<>();
		query.eq("id_card", user.getIdCard());
		TRegFaceUser faceUser = tRegFaceUserMapper.selectOne(query);
		
		MultipartFile photo = user.getPhotoFile();
		boolean isUpload = FileUploadUtil.upload(photo);
		if(isUpload) {
			if(regUser(user)) {
				if(faceUser == null) {
					return retBool(baseMapper.insert(user));
				} else {
					return retBool(baseMapper.updateById(user));
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean regUser(RegFaceUserVO user) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		String imageBase64 = ImageBase64Converter.GetImageStr(user.getPhoto());
				
		data.put("image", imageBase64); // 人脸照片的base64数据
		data.put("id", user.getFaceId());	//人脸注册时的id
		data.put("card", user.getIdCard());	// id卡的卡号
		data.put("ip", user.getFaceMachineIp());	//注册的目标设备IP地址
		
		String responseBody = HttpUtil.doPost(CommonConstants.FACE_USER_ADD, JSON.toJSONString(data));
		
		@SuppressWarnings("rawtypes")
		Map result = (Map) JSON.parse(responseBody);
		Integer code = (Integer) result.get("code");
		if(code == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int importUser(MultipartFile file) throws IOException {
		int result = 0;
		
		List<TRegFaceUser> userList = new ArrayList<TRegFaceUser>();
		
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		
		InputStream ins = file.getInputStream();
		
		Workbook wb = null;
		
		if(suffix.equals("xlsx")){
			
			wb = new XSSFWorkbook(ins);
			
		}else{
			wb = new HSSFWorkbook(ins);
		}

		/**
		 * 获取excel表单
		 */
		Sheet sheet = wb.getSheetAt(0);
		if(null != sheet){
			
			for(int line = 2; line <= sheet.getLastRowNum();line++){
				
				TRegFaceUser user = new TRegFaceUser();
				
				Row row = sheet.getRow(line);
				
				if(null == row){
					continue;
				}
				
				
				String name = row.getCell(0).getStringCellValue();
				
				int gender = "男".equals(row.getCell(1).getStringCellValue()) ? 1 : 0;
				
				Date birthday = row.getCell(2).getDateCellValue();
				
				String idCard = row.getCell(3).getStringCellValue();
				
				String jobNumber = row.getCell(4).getStringCellValue();
				
				String phone = row.getCell(5).getStringCellValue();
				
				String email = row.getCell(6).getStringCellValue();
				
				int isBlacklist = "是".equals(row.getCell(7).getStringCellValue()) ? 1 : 0;
				user.setName(name);
				user.setGender(gender);
				user.setBirthday(birthday);
				user.setIdCard(idCard);
				user.setJobNumber(jobNumber);
				user.setPhone(phone);
				user.setEmail(email);
				user.setIsBlacklist(isBlacklist);
				
				userList.add(user);
	
			}
			
			for(TRegFaceUser userInfo:userList){
			
				/**
				 * 判断数据库表中是否存在用户记录，若存在，则更新，不存在，则保存记录
				 */
				String idCard = userInfo.getIdCard();
				
				QueryWrapper query = new QueryWrapper();
				query.eq("id_card", idCard);
				
				TRegFaceUser faceUser = tRegFaceUserMapper.selectOne(query);
				
				if(faceUser == null){			
					result = tRegFaceUserMapper.insert(userInfo);
				}else{
					userInfo.setId(faceUser.getId());
					result = tRegFaceUserMapper.updateById(userInfo);
				}
				
			}
		}
 
		return result;

	}

}
