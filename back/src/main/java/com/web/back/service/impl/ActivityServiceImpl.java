package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Activity;
import com.web.back.service.ActivityService;
import com.web.back.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【activity】的数据库操作Service实现
* @createDate 2023-05-07 13:09:23
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




