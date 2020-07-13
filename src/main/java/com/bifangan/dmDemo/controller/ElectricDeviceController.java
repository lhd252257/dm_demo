package com.bifangan.dmDemo.controller;

import com.bifangan.dmDemo.common.R;
import com.bifangan.dmDemo.entity.ElectricLine;
import com.bifangan.dmDemo.service.ElectricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/electricDevice" )

public class ElectricDeviceController {

    @Autowired
    private ElectricService electricService;

    @GetMapping("/electricList" )
    public R electricList() {

        return new R<>(electricService.selectElectricList());
    }

    @GetMapping("/electricLinesByDid" )
    public R electricLinesByDid(String did) {

        return new R<>(electricService.electricLinesByDid(did));
    }

    @GetMapping("/electricLineList" )
    public R electricLineList() {

        return new R<>(electricService.electricLineList());
    }

    @GetMapping("/lineRealtimingdataByLineId" )
    public R lineRealtimingdataByLineId(String lid) {

        return new R<>(electricService.lineRealtimingdataByLineId(lid));
    }

    /*@GetMapping("/electricPowerOff" )
    public R electricPowerOff(String controllerId ,Integer lineNo,Integer status) {

        return new R<>(electricService.electricPowerOff(controllerId,lineNo,status));
    }*/

    @GetMapping("/electricPowerOff" )
    public R electricPowerOff(ElectricLine electricLine) {

        return new R<>(electricService.electricPowerOff(electricLine));
    }

}
