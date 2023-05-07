package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Group;
import com.web.back.service.GroupService;
import com.web.back.mapper.GroupMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【group】的数据库操作Service实现
* @createDate 2023-05-07 13:09:55
*/
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group>
    implements GroupService{

}




