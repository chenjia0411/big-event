package com.heima.service;

import com.heima.pojo.User;

/**
 * @Auther: chenjia
 * @Date: 2023/11/22 - 11 - 22 - 17:43
 * @Description: com.heima.service
 * @version: 1.0
 */
public interface UserService {
    //根据用户名查询用户

    public User findByUserName(String username);

    //注册
    void register(String username, String password);
    //更新
    void update(User user);
    //更新头像
    void updataAvatat(String avatarUrl);

    void updatePwd(String newPwd);
}
