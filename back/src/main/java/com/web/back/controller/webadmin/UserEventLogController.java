package com.web.back.controller.webadmin;

import com.github.pagehelper.PageInfo;
import com.web.back.base.BaseApiController;
import com.web.back.domain.UserEventLog;
import com.web.back.service.AuthenticationService;
import com.web.back.service.UserEventLogService;
import com.web.back.state.RestResponse;
import com.web.back.utils.DateTimeUtil;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.user.UserEventLogVM;
import com.web.back.viewmodel.admin.user.UserEventPageRequestVM;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
@Api("usereventlog的Api")
@RestController("UserEventLogController")
@RequestMapping(value = "api/webadmin/usereventlog")
public class UserEventLogController extends BaseApiController {

    private final UserEventLogService userEventLogService;

    private final AuthenticationService authenticationService;

    @Autowired
    public UserEventLogController(UserEventLogService userEventLogService, AuthenticationService authenticationService){
        this.userEventLogService =userEventLogService;
        this.authenticationService = authenticationService;

    }
    @RequestMapping(value = "/event/page/list", method = RequestMethod.POST)
    public RestResponse<PageInfo<UserEventLogVM>> eventPageList(@RequestBody UserEventPageRequestVM model) {
        System.out.println(model.getUserName());
        System.out.println(model.getUserId());
        PageInfo<UserEventLog> pageInfo = userEventLogService.page(model);
        PageInfo<UserEventLogVM> page = PageInfoHelper.copyMap(pageInfo, d -> {
            UserEventLogVM vm = modelMapper.map(d, UserEventLogVM.class);
            System.out.println(d.getCreateTime());
            vm.setCreateTime(DateTimeUtil.dateFormat(d.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
}
