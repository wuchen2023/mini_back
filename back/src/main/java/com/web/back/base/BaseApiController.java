package com.web.back.base;



import com.web.back.context.WebContext;
import com.web.back.domain.Teacher;
import com.web.back.utils.ModelMapperSingle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 3.3.0
 * @description: The type Base api controller.
 * Copyright (C), 2019-2021, 武汉思维跳跃科技有限公司
 * @date 2021 /5/26 10:45
 */
public class BaseApiController {
    /**
     * The constant DEFAULT_PAGE_SIZE.
     */
    protected final static String DEFAULT_PAGE_SIZE = "10";
    /**
     * The constant modelMapper.
     */
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    /**
     * The Web context.
     */
    @Autowired
    protected WebContext webContext;

    /**
     * Gets current user.
     *
     * @return the current user
     */
    protected Teacher getCurrentTeacher() {
        return webContext.getCurrentUser();
    }
}
