package com.tencent.wxcloudrun;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.common.request.AdminUserLoginParam;
import com.tencent.wxcloudrun.common.request.BasePageParam;
import com.tencent.wxcloudrun.common.response.UserInfoResult;
import com.tencent.wxcloudrun.web.WxCloudRunApplication;
import com.tencent.wxcloudrun.web.service.AdminUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tangsh
 * @date 2022/11/04
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WxCloudRunApplication.class})
public class AdminTest {

    @Autowired
    private AdminUserInfoService adminUserInfoService;


    @Test
    public void test_common_admin_check() {
        login();
    }

    private void login() {
        AdminUserLoginParam param = AdminUserLoginParam.builder().username("admin").password("a9527").build();
        adminUserInfoService.login(param);
    }


    @Test
    public void test_query_wx_user_page() {
        int pageNo = 0;
        int pageSize = 20;
        BasePageParam param = BasePageParam.builder().pageNo(pageNo).pageSize(pageSize).build();
        PageDTO<UserInfoResult> pageDTO = adminUserInfoService.page(param);
        log.info("{}", JSON.toJSONString(pageDTO));
    }
}
