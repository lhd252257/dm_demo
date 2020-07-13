package com.bifangan.dmDemo.SchedulerJob;

import com.bifangan.dmDemo.entity.DeviceInfo;
import com.bifangan.dmDemo.entity.ElectricLine;
import com.bifangan.dmDemo.entity.Lines;
import com.bifangan.dmDemo.service.ElectricService;
import com.bifangan.dmDemo.utils.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class Scheduler{


    //每隔20秒执行一次
    @Scheduled(fixedRate = 15000)
    public void selectDeviceLinesInfo() {
        String sn = "J191291273418";
        String deviceId = "5e1d16b54c1d6e39f47d6902";
        Integer timeStamp = getSecondTimestamp(new Date());
        Integer startTime = timeStamp-15;
        ElectricService electricService = (ElectricService) ApplicationContextUtil.getBean("electricServiceImpl");
        DeviceInfo deviceInfo = electricService.selectDeviceInfo(sn,timeStamp);

        DeviceInfo deviceEnergy = electricService.selectDeviceEnergy(deviceId,startTime,timeStamp);

        List<Lines> lines = deviceInfo.getLines();

        for (Lines Line:lines) {
            ElectricLine electricLine = electricService.selectLineByLineId(Line.getLineID());
            //线路离线后及时更改线路表线路状态
            if(electricLine != null){
                if(Line.getLine_Status() == 0){
                    electricService.updateLineStatusByLineId(Line.getLineID());
                }

                ElectricLine lineRealtiming = new ElectricLine();

                if(deviceEnergy == null){
                    lineRealtiming.setEnergy(BigDecimal.valueOf(0));
                }else{
                    //System.out.println("----------------------------------------------------------------------");
                    List<Lines> linesEnergy = deviceEnergy.getLines();
                    for (Lines lineEnergy:linesEnergy) {
                        if(Line.getLineID().equals(lineEnergy.getLineID())){
                            lineRealtiming.setEnergy(lineEnergy.getEnergy());
                        }
                    }

                }

                lineRealtiming.setId(UUID.randomUUID().toString().replace("-", ""));

                lineRealtiming.setLid(electricLine.getId());

                lineRealtiming.setVoltage(Line.getVoltage());

                lineRealtiming.setCurrent(Line.getCurrent());

                lineRealtiming.setTemp(Line.getTemp());

                lineRealtiming.setLeakage(Line.getLeakage());

                lineRealtiming.setPower(Line.getPower());

                lineRealtiming.setStatus(Line.getStatus());

                lineRealtiming.setAppendTime(new Date());

                electricService.insertLineRealtiming(lineRealtiming);

            }


        }


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


}
