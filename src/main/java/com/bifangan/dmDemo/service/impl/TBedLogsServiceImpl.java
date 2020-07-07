package com.bifangan.dmDemo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bifangan.dmDemo.entity.TBedLogs;
import com.bifangan.dmDemo.mapper.TBedLogsMapper;
import com.bifangan.dmDemo.service.TBedLogsService;

/**
 * 就寝日记表
 *
 * @author lihongda
 * @date 2020-07-02 16:39:00
 */
@Service
@Primary
public class TBedLogsServiceImpl extends ServiceImpl<TBedLogsMapper, TBedLogs> implements TBedLogsService {

}
