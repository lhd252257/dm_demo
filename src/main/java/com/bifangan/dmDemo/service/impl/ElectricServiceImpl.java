package com.bifangan.dmDemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bifangan.dmDemo.entity.*;
import com.bifangan.dmDemo.mapper.ElectricMapper;
import com.bifangan.dmDemo.service.ElectricService;
import com.bifangan.dmDemo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service("electricServiceImpl")
public class ElectricServiceImpl implements ElectricService {

    @Value("${user_id}")
    private String userId;

    @Value("${secret}")
    private String secret;

    @Autowired
    private ElectricMapper electricMapper;

    @Override
    public List<Electric> selectElectricList() {
        return electricMapper.selectElectricList();
    }

    @Override
    public List<ElectricLine> electricLinesByDid(String did) {
        return electricMapper.electricLinesByDid(did);
    }

    @Override
    public List<ElectricLine> lineRealtimingdataByLineId(String lid) {
        return electricMapper.lineRealtimingdataByLineId(lid);
    }

    @Override
    public ElectricLine selectLineByLineId(String lineId) {
        return electricMapper.selectLineByLineId(lineId);
    }

    @Override
    public boolean updateLineStatusByLineId(String lineId) {
        return electricMapper.updateLineStatusByLineId(lineId);
    }

    @Override
    public void insertLineRealtiming(ElectricLine lineRealtiming) {
        electricMapper.insertLineRealtiming(lineRealtiming);
    }

    @Override
    public DeviceInfo selectDeviceInfo(String sn) {

        String nonce = getRandomString(6);

        String token = getToken(userId, secret);

        int timeStamp = getSecondTimestamp(new Date());

        SortedMap<Object,Object> parameters = new TreeMap<>();

        parameters.put("SN", sn);

        parameters.put("Nonce", nonce);

        parameters.put("TimeStamp", String.valueOf(timeStamp));

        parameters.put("UserID", userId);

        String signature = createSign(parameters, token);

        UserInfo userInfo = new UserInfo();

        userInfo.setUserID(userId);

        userInfo.setNonce(nonce);

        userInfo.setTimeStamp(String.valueOf(timeStamp));

        userInfo.setSignature(signature);

        String jsonString = JSON.toJSONString(userInfo);

        String authorization = null;
        try {
            authorization = Base64.getEncoder().encodeToString(jsonString.getBytes("gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String get = HttpUtils.doGet("http://ex-api.jalasmart.com/api/v2/devices/"+sn,authorization);

        JSONObject fromObject = JSONObject.parseObject(get);

        String string = fromObject.get("Data").toString();

        DeviceInfo deviceInfo = JSON.parseObject(string,DeviceInfo.class);

        return deviceInfo;
    }


    @Override
        public String electricPowerOff(String controllerId, Integer lineNo, Integer status){

        String token = getToken(userId, secret);

        String nonce = getRandomString(6);

        LineStatus lineStatus = new LineStatus();

        lineStatus.setLineNo(lineNo);

        lineStatus.setStatus(status);

        List<LineStatus> arrayList = new ArrayList<>();

        arrayList.add(lineStatus);

        String lines = JSON.toJSONString(arrayList);

        int timeStamp = getSecondTimestamp(new Date());

        SortedMap<Object,Object> parameters = new TreeMap<>();

        parameters.put("ControllerID", controllerId);

        parameters.put("Lines", lines);

        parameters.put("Nonce", nonce);

        parameters.put("TimeStamp", String.valueOf(timeStamp));

        parameters.put("UserID", userId);

        String signature = createSign(parameters,token);

        UserInfo userInfo = new UserInfo();

        userInfo.setUserID(userId);

        userInfo.setNonce(nonce);

        userInfo.setTimeStamp(String.valueOf(timeStamp));

        userInfo.setSignature(signature);

        String jsonString = JSON.toJSONString(userInfo);

        String authorization = null;
        try {
            authorization = Base64.getEncoder().encodeToString(jsonString.getBytes("gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(authorization);

        LinesUpOrOff linesUpOrOff = new LinesUpOrOff();

        linesUpOrOff.setControllerID(controllerId);

        linesUpOrOff.setLines(arrayList);

        String linesUpOrOffString = JSON.toJSONString(linesUpOrOff);

        String put = HttpUtils.doPut("http://ex-api.jalasmart.com/api/v2/status/"+controllerId, authorization, linesUpOrOffString);

        JSONObject fromObject = JSONObject.parseObject(put);

        String string = fromObject.get("Data").toString();

        return string;
    }


    public static String getToken(String userID,String secret){

        User user = new User();

        user.setID(userID);

        user.setSecret(secret);

        String userSting = JSON.toJSONString(user);

        String doput = HttpUtils.doPut("http://ex-api.jalasmart.com/api/v2/platform/Token", null, userSting);

        JSONObject jsonObject = JSONObject.parseObject(doput);//将String转为JSON数据

        return jsonObject.get("Data").toString();
    }

    public static String createSign(SortedMap<Object,Object> parameters,String key){
        StringBuffer sb = new StringBuffer();
        StringBuffer sbkey = new StringBuffer();
        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if(null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
                sbkey.append(k + "=" + v + "&");
            }
        }
        //System.out.println("字符串:"+sb.toString());
        sbkey=sbkey.append("Token="+key);
        //MD5加密,结果转换为大写字符
        System.out.println("MD5加密前:  "+sbkey.toString());
        String signature = MD5Utils.encrypt32(sbkey.toString());
        return signature;
    }


    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
