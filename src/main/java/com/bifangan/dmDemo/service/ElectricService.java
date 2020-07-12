package com.bifangan.dmDemo.service;

import com.bifangan.dmDemo.entity.DeviceInfo;
import com.bifangan.dmDemo.entity.Electric;
import com.bifangan.dmDemo.entity.ElectricLine;
import java.util.List;

public interface ElectricService {

    List<Electric> selectElectricList();

    List<ElectricLine> electricLinesByDid(String did);

    List<ElectricLine> lineRealtimingdataByLineId(String lid);

    DeviceInfo selectDeviceInfo(String sn);

    boolean electricPowerOff(String controllerId, Integer lineNo, Integer status);

    ElectricLine selectLineByLineId(String lineId);

    boolean updateLineStatusByLineId(String lineId);

    void insertLineRealtiming(ElectricLine lineRealtiming);
}
