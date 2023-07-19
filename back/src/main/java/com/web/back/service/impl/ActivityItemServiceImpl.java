package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.ActivityItem;
import com.web.back.mapper.ActivityItemMapper;
import com.web.back.service.ActivityItemService;
import org.springframework.stereotype.Service;

/**
 * @author by hongdou
 * @date 2023/7/19.
 * @DESC:
 */
@Service
public class ActivityItemServiceImpl extends ServiceImpl<ActivityItemMapper, ActivityItem> implements ActivityItemService {
}
