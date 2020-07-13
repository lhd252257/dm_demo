package com.bifangan.dmDemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bifangan.dmDemo.entity.DeviceInfo;
import com.bifangan.dmDemo.entity.Electric;
import com.bifangan.dmDemo.entity.ElectricLine;

import java.util.List;

public interface ElectricService {

    List<Electric> selectElectricList();

    List<ElectricLine> electricLinesByDid(String did);

    List<ElectricLine> lineRealtimingdataByLineId(String lid);

    DeviceInfo selectDeviceInfo(String sn ,Integer timeStamp);

    String electricPowerOff(ElectricLine electricLine);

    ElectricLine selectLineByLineId(String lineId);

    boolean updateLineStatusByLineId(String lineId);

    void insertLineRealtiming(ElectricLine lineRealtiming);

    List<ElectricLine> electricLineList();

    DeviceInfo selectDeviceEnergy(String deviceId, Integer startTime, Integer timeStamp);
}
