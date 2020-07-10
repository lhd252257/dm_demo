package com.bifangan.dmDemo.utils;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by
 * 王尚飞
 * on 2020/7/9 19:10
 */
@Component
public class ElectricStatusUtil {
     static WxMpService wxMpService = new WxMpServiceImpl();
    /**
     * 缴费成功通知
     * 通知来电了
     */
    static final String success = "PmbUIvy-lIX3X5KFDfA8CvqgcNwhc9g6HBL8-qzUerw";

    /**
     * 停电警告
     * 预警通知
     * 电表电量通知
     * 电费充值提醒
     */
    static final String warning = "tp9ohWIP_FOXSOWGrAbvopwxpxqpgOt6UDiKmZuWk1H0";

    /**
     *
     */
     static {
         WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
         wxStorage.setAppId("appId=wxdd3d096b7babb247&");//appid
         wxStorage.setSecret("secret=143e4218cc5af3591f00a4fe4d86e088&");//appsecret
         wxMpService.setWxMpConfigStorage(wxStorage);
     }

    /**
     * 欠费
     * 提前缴费
     * 提醒逻辑
     * @param
     * @param roomId
     * @return
     */
    public static void warning(String roomId, List<String> openId,int count) {
        Date date = new Date();
        String s = date.toString();
        HashMap<String, String> messageTemplateMap = new HashMap<>();
        if (count == 0) {
            messageTemplateMap.put("first","您好，您宿舍"+roomId+"余电费余额已经用完，请及时充值");
            messageTemplateMap.put("keyword1","0");
            messageTemplateMap.put("keyword2",s);
            messageTemplateMap.put("remark","宿舍已经断电，充值电费即可来电");
        }
        if (count < 10&& count > 0) {
            messageTemplateMap.put("first","您宿舍"+roomId+"余电费余额为"+count+"元，请及时充值避免停电");
            messageTemplateMap.put("keyword1","0");
            messageTemplateMap.put("keyword2",s);
            messageTemplateMap.put("remark","请及时充值避免停电");
        }
        pushMsg(openId,warning,messageTemplateMap);
    }

    /**
     *
     */
    public static void  SuccessMessage(String roomId, List<String> openId,int count,int number) {
        Date date = new Date();
        String s = date.toString();
        HashMap<String, String> messageTemplateMap = new HashMap<>();
            messageTemplateMap.put("first","尊敬的用户您好");
            messageTemplateMap.put("keyword1","宿舍"+roomId);
            messageTemplateMap.put("keyword2","宿舍时间"+date);
            messageTemplateMap.put("keyword3","充值金额"+number);
            messageTemplateMap.put("keyword4","电表余额"+count);
            messageTemplateMap.put("remark","宿舍充值成功，祝您生活愉快");
        pushMsg(openId,success,messageTemplateMap);
    }


    /**
     * 通知逻辑
     * 需要传入模板ID
     * @return
     */
    public static void pushMsg( List<String> oepnId, String templateId, Map messageMap) {

        for (String openIdd: oepnId) {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser(openIdd)//要推送的用户openid
                    .templateId(templateId)//模版id
                    .build();

            Iterator map1it=messageMap.entrySet().iterator();
            while(map1it.hasNext()) {
                Map.Entry<String, String> entry=(Map.Entry<String, String>) map1it.next();
                templateMessage.addData(new WxMpTemplateData(entry.getKey(), entry.getValue()));
                /**
                 * 先打印出来看看有问题没有
                 */
                System.out.println("Key: "+entry.getKey()+" Value: "+entry.getValue());
            }
            try {
                String ss = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                System.out.println("推送成功：" + ss);
            } catch (Exception e) {
                System.out.println("推送失败：" + e.getMessage());
                e.printStackTrace();
            }

        }
    }

}
