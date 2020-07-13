package com.bifangan.dmDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bifangan.dmDemo.entity.ControllerIdAndLineNo;
import com.bifangan.dmDemo.entity.Electric;
import com.bifangan.dmDemo.entity.ElectricLine;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ElectricMapper extends BaseMapper<Electric> {

    List<Electric> selectElectricList();

    List<ElectricLine> electricLinesByDid(String did);

    List<ElectricLine> lineRealtimingdataByLineId(String lid);

    ElectricLine selectLineByLineId(String lineId);

    boolean updateLineStatusByLineId(String lineId);

    void insertLineRealtiming(ElectricLine lineRealtiming);

    List<ElectricLine> electricLineList();

    ControllerIdAndLineNo getControllerIdAndLineNoByDeptId(String deptId);

    boolean updateStatusByDeptId(ElectricLine electricLine);
}
