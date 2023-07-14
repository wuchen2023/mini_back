package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.GroupState;
import com.web.back.service.GroupStateService;
import com.web.back.mapper.GroupStateMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【group_state】的数据库操作Service实现
* @createDate 2023-07-14 10:26:30
*/
@Service
public class GroupStateServiceImpl extends ServiceImpl<GroupStateMapper, GroupState>
    implements GroupStateService{

}




