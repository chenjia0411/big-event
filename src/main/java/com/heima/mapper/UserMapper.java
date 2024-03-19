package com.heima.mapper;

import com.heima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther: chenjia
 * @Date: 2023/11/22 - 11 - 22 - 17:44
 * @Description: com.heima.mapper
 * @version: 1.0
 */
@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //添加
    @Insert("insert into user(username, password, create_time, update_time)" +
            "values(#{username},#{password},now(),now())"
    )
    void add(String username, String password);
    //修改个人资料
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id =#{id}")
    void update(User user);
    //修改头像
    @Update("update user set user_pic=#{avatarUrl},update_time= now() where id =#{id} ")
    void updateAvatat(String avatarUrl, Integer id);

    @Update("update user set password=#{password},update_time = now() where id =#{id}")
    void updatePwd(String password, Integer id);
}
