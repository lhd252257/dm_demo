package com.bifangan.dmDemo.SchedulerJob;

import com.bifangan.dmDemo.entity.DeviceInfo;
import com.bifangan.dmDemo.entity.ElectricLine;
import com.bifangan.dmDemo.entity.Lines;
import com.bifangan.dmDemo.service.ElectricService;
import com.bifangan.dmDemo.utils.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class Scheduler{


    //每隔2秒执行一次
    @Scheduled(fixedRate = 200000)
    public void selectDeviceLinesInfo() {
        String sn = "J191291273418";
        ElectricService electricService = (ElectricService) ApplicationContextUtil.getBean("electricServiceImpl");
        DeviceInfo deviceInfo = electricService.selectDeviceInfo(sn);

        List<Lines> lines = deviceInfo.getLines();

        for (Lines Line:lines) {
            System.out.println(Line);
            ElectricLine electricLine = electricService.selectLineByLineId(Line.getLineID());
            //线路离线后及时更改线路表线路状态
            if(electricLine != null){
                /*if(Line.getLine_Status() == 0){
                    electricService.updateLineStatusByLineId(Line.getLineID());
                }*/
                ElectricLine lineRealtiming = new ElectricLine();

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


}
