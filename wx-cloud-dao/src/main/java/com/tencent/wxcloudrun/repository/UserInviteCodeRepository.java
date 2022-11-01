package com.tencent.wxcloudrun.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.entity.UserInviteCodeEntity;
import com.tencent.wxcloudrun.mapper.UserInviteCodeMapper;
import org.springframework.stereotype.Repository;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Repository
public class UserInviteCodeRepository extends BaseRepository<UserInviteCodeMapper, UserInviteCodeEntity> {

    public UserInviteCodeEntity getOneByOpenId(String openid) {
        LambdaQueryWrapper<UserInviteCodeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInviteCodeEntity::getOpenid, openid);
        return this.getOne(wrapper);
    }
}
