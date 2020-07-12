package com.bifangan.dmDemo.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.bifangan.dmDemo.common.UUIDUtils;
import com.bifangan.dmDemo.constant.CommonConstants;
import com.bifangan.dmDemo.entity.TBedLogs;
import com.bifangan.dmDemo.entity.TDept;
import com.bifangan.dmDemo.entity.TRegFaceUser;
import com.bifangan.dmDemo.global.CounterGlobal;
import com.bifangan.dmDemo.mapper.TBedLogsMapper;
import com.bifangan.dmDemo.mapper.TDeptMapper;
import com.bifangan.dmDemo.mapper.TRegFaceUserMapper;
import com.bifangan.dmDemo.service.TRegFaceUserService;
import com.bifangan.dmDemo.utils.FaceIdUtil;
import com.bifangan.dmDemo.utils.FileUploadUtil;
import com.bifangan.dmDemo.utils.HttpUtil;
import com.bifangan.dmDemo.utils.ImageBase64Converter;
import com.bifangan.dmDemo.utils.ObjectUtil;
import com.bifangan.dmDemo.vo.FaceDeviceAuthVO;
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
	
	@Autowired
	private TBedLogsMapper tBedLogsMapper;
	
	@Autowired
	private TDeptMapper tDeptMapper;
	
	@Override
	public R blacklist(FaceDeviceAuthVO fda) {
		QueryWrapper<TRegFaceUser> query = new QueryWrapper<>();
		query.eq("face_id", fda.getSrc().getFaceid());
		TRegFaceUser faceUser = tRegFaceUserMapper.selectOne(query);
		R result = new R();
		
		if(faceUser != null) {
			if(faceUser.getIsBlacklist() == 1) {
				result.setCode(0);
			} else {
				result.setCode(1);
				
				TDept dept = tDeptMapper.selectById(faceUser.getDeptId());
				
				Date nowDate = new Date();
				TBedLogs bedLogs = new TBedLogs();
				bedLogs.setId(UUIDUtils.getUUID36());
				bedLogs.setUserId(faceUser.getId());
				bedLogs.setUserName(faceUser.getName());
				bedLogs.setDeptId(faceUser.getDeptId());
				bedLogs.setDept(faceUser.getDept());
				bedLogs.setFaceid(faceUser.getFaceId());
				bedLogs.setDeviceId(fda.getID());
				bedLogs.setIoTime(nowDate);
				// 1进 2出
				if(fda.getID().equals(dept.getInFaceId())) {
					bedLogs.setIoFlag(1);
				} else {
					bedLogs.setIoFlag(2);
				}
				
				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTime(nowDate);
				startCalendar.set(Calendar.HOUR_OF_DAY, getTimeX(faceUser.getPassingTime(), "start", Calendar.HOUR_OF_DAY));
				startCalendar.set(Calendar.MINUTE, getTimeX(faceUser.getPassingTime(), "start", Calendar.MINUTE));
				startCalendar.set(Calendar.SECOND, 0);
				
				Calendar endCalendar = Calendar.getInstance();
				startCalendar.setTime(nowDate);
				startCalendar.set(Calendar.HOUR_OF_DAY, getTimeX(faceUser.getPassingTime(), "end", Calendar.HOUR_OF_DAY));
				startCalendar.set(Calendar.MINUTE, getTimeX(faceUser.getPassingTime(), "end", Calendar.MINUTE));
				startCalendar.set(Calendar.SECOND, 0);
				if(nowDate.compareTo(startCalendar.getTime()) > 1 && nowDate.compareTo(endCalendar.getTime()) <1) {
					// 晚归
					bedLogs.setBedState(2);
				} else {
					// 在寝
					bedLogs.setBedState(1);
				}
				tBedLogsMapper.insert(bedLogs);
				
				// 计数器，判断宿舍人员是否有人
				Integer counter = CounterGlobal.get(faceUser.getDeptId());
				if(bedLogs.getIoFlag() == 1) {
					System.out.println("counter:"+counter);
					if(counter == 0) {
						// TODO 宿舍送电
						System.out.println(faceUser.getDept() + "===========================送电");
					}
					CounterGlobal.put(faceUser.getDeptId(), ++counter);
					System.out.println("counter:"+counter);
				} else {
					System.out.println("counter:"+counter);
					if(counter != 0) {
						CounterGlobal.put(faceUser.getDeptId(), --counter);
					}
					if(counter == 0) {
						// TODO 宿舍断电
						System.out.println(faceUser.getDept() + "===========================断电");
					}
					System.out.println("counter:"+counter);
				}
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
		
		TDept dept = tDeptMapper.selectById(user.getDeptId());
		user.setInFaceDevice(dept.getInFaceMachineIp());
		user.setOutFaceDevice(dept.getOutFaceMachineIp());
		
		user.setFaceId(FaceIdUtil.getFaceId(user.getName()));
		
		MultipartFile photo = user.getPhotoFile();
		if(photo != null) {
			String filePath = FileUploadUtil.upload(photo);
			if(!"null".equals(filePath) && !"error".equals(filePath)) {
				user.setPhoto(filePath);
				if(regUser(user)) {
					try {
						if(faceUser == null)
							faceUser = new TRegFaceUser();
						ObjectUtil.cpoyObjAttr(user, faceUser, TRegFaceUser.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(null == faceUser.getId()) {
						faceUser.setId(UUIDUtils.getUUID36());
						return tRegFaceUserMapper.insert(faceUser) > 0;
					} else {
						return tRegFaceUserMapper.updateById(faceUser) > 0;
					}
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
		data.put("card", "");	// id卡的卡号
		data.put("ip", user.getInFaceDevice());	//注册的目标设备IP地址
		String params = JSON.toJSONString(data);
		System.out.println(params);
		String responseBody = HttpUtil.doPost(CommonConstants.FACE_USER_ADD, params);
		
		@SuppressWarnings("rawtypes")
		Map result = (Map) JSON.parse(responseBody);
		Integer code = Integer.parseInt(result.get("code").toString());
		if(code == 1) {
			Map<String, Object> data1 = new HashMap<String, Object>();
			
					
			data1.put("image", imageBase64); // 人脸照片的base64数据
			data1.put("id", user.getFaceId());	//人脸注册时的id
			data1.put("card", "");	// id卡的卡号
			data1.put("ip", user.getOutFaceDevice());
			String params1 = JSON.toJSONString(data1);
			System.out.println(params1);
			String responseBody1 = HttpUtil.doPost(CommonConstants.FACE_USER_ADD, params1);
			
			@SuppressWarnings("rawtypes")
			Map result1 = (Map) JSON.parse(responseBody1);
			Integer code1 = Integer.parseInt(result1.get("code").toString());
			if(code1 == 1) {
				return true;
			}
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

	@Override
	public int refreshState() {
		int result = 0;
		
		// TODO 因为演示所以此功能没有设置昨天到今天的时间，设置的是今天到明天6点的时间
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("is_blacklist", 0);
//		queryWrapper.notExists("select * from t_bed_logs where io_time between "+ startDate +" and " + endDate);
		// 查询非黑名单注册用户
		List<TRegFaceUser> userList = tRegFaceUserMapper.selectObjs(queryWrapper);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(new Date());
		endCalendar.add(Calendar.DATE, 1);
		endCalendar.set(Calendar.HOUR_OF_DAY, 6);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		Date endDate = endCalendar.getTime();
		
		if(userList != null && !userList.isEmpty()) {
			
			for(Iterator<TRegFaceUser> i = userList.iterator(); i.hasNext(); ) {
				TRegFaceUser user = i.next();
				String[] times = user.getPassingTime().split("-")[0].split(":");
				Calendar startCalendar = Calendar.getInstance();
				startCalendar.setTime(new Date());
				startCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
				startCalendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
				startCalendar.set(Calendar.SECOND, 0);
				Date startDate = startCalendar.getTime();
				
				QueryWrapper<TBedLogs> wrapper = new QueryWrapper();
				wrapper.eq("user_id", user.getId());
				wrapper.between("io_time", startDate, endDate);
				TBedLogs tBedLogs = tBedLogsMapper.selectOne(queryWrapper);
				if(tBedLogs == null) {
					TBedLogs bedLogs = new TBedLogs();
					bedLogs.setUserId(user.getId());
					bedLogs.setUserName(user.getName());
					bedLogs.setDeptId(user.getDeptId());
					bedLogs.setDept(user.getDept());
					bedLogs.setFaceid(user.getFaceId());
					// 未归
					bedLogs.setBedState(4);
					tBedLogsMapper.insert(bedLogs);
					result++;
				} else {
					String InFaceId = tDeptMapper.getInFaceIdByUserDeptId(user.getDeptId());
					if(!tBedLogs.getDeviceId().equals(InFaceId)) {
						// 滞留
						tBedLogs.setBedState(3);
						tBedLogsMapper.updateById(tBedLogs);
						result++;
					}
				}
			}
		}
		
		return 0;
	}

	/**
	 * 
	 * @param time
	 * @param startOrEnd
	 * @param calendarType
	 * @return
	 */
	private int getTimeX(String time, String startOrEnd, int calendarType) {
		String[] times = time.split("-");
		if(null != startOrEnd) {
			if("start".equals(startOrEnd)) {
				if(Calendar.HOUR_OF_DAY == calendarType) {
					return Integer.parseInt(times[0].split(":")[0]);
				}else if(Calendar.MINUTE == calendarType) {
					return Integer.parseInt(times[0].split(":")[1]);
				}
			} else if("end".equals(startOrEnd)) {
				if(Calendar.HOUR_OF_DAY == calendarType) {
					return Integer.parseInt(times[1].split(":")[0]);
				}else if(Calendar.MINUTE == calendarType) {
					return Integer.parseInt(times[1].split(":")[1]);
				}
			}
		} 
		return 0;
	}

	@Override
	public List<TRegFaceUser> textFullSearch(String content) {
		QueryWrapper<TRegFaceUser> wrapper = new QueryWrapper<TRegFaceUser>();
		if(content != null && !content.isEmpty()) {
			wrapper.like("name", content);
			wrapper.or();
			wrapper.like("job_number", content);
			wrapper.or();
			wrapper.like("phone", content);
			wrapper.or();
			wrapper.like("address", content);
			wrapper.or();
			wrapper.like("id_card", content);
		}
		
		return tRegFaceUserMapper.selectList(wrapper);
	}

	@Override
	public List<TRegFaceUser> getblackList() {
		QueryWrapper<TRegFaceUser> wrapper = new QueryWrapper<TRegFaceUser>();
		wrapper.eq("is_blacklist", 1);
		return tRegFaceUserMapper.selectList(wrapper);
	}
	
}
