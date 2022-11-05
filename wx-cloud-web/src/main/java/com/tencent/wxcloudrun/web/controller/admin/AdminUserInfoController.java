
package com.tencent.wxcloudrun.web.controller.admin;

import com.tencent.wxcloudrun.common.annotation.ApiRequest;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.common.request.*;
import com.tencent.wxcloudrun.common.response.*;
import com.tencent.wxcloudrun.web.service.AdminUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/11/04
 */
@Api(tags = "用户管理-后台模块")
@RestController
@RequestMapping("/admin")
public class AdminUserInfoController {

    @Autowired
    private AdminUserInfoService adminUserInfoService;

    @ApiOperation("用户后台-登录")
    @PostMapping("/v1/login")
    @ApiRequest
    public Result<Void> login(@RequestBody @Validated AdminUserLoginParam param) {
        adminUserInfoService.login(param);
        return Result.Success();
    }

    @ApiOperation("用户后台-分页")
    @PostMapping("/v1/user/page")
    @ApiRequest
    public Result<PageDTO<UserInfoResult>> wxUserPage(@RequestBody @Validated AdminUserPageParam param) {
        PageDTO<UserInfoResult> result = adminUserInfoService.page(param);
        return Result.Success(result);
    }

    @ApiOperation("用户后台-邀请客户详情")
    @PostMapping("/v1/user/invite-user-detail")
    @ApiRequest
    public Result<List<InviteUserDetailResult>> inviteUserDetail(@RequestBody @Validated BaseInviteCodeParam param) {
        List<InviteUserDetailResult> list = adminUserInfoService.inviteUserDetail(param);
        return Result.Success(list);
    }

    @ApiOperation("用户后台-邀请付费详情")
    @PostMapping("/v1/user/invite-pay-detail")
    @ApiRequest
    public Result<List<InvitePayDetailResult>> invitePayDetail(@RequestBody @Validated BaseInviteCodeParam param) {
        return Result.Success(adminUserInfoService.invitePayDetail(param));
    }

    @ApiOperation("用户后台-个人下单详情")
    @PostMapping("/v1/user/user-pay-detail")
    @ApiRequest
    public Result<List<UserPayDetailResult>> userPayDetail(@RequestBody @Validated BaseWxUserParam param) {
        return Result.Success(adminUserInfoService.userPayDetail(param));
    }


}
