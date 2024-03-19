package com.heima.controller;

import com.heima.pojo.Result;
import com.heima.pojo.User;
import com.heima.service.UserService;
import com.heima.utils.JwtUtil;
import com.heima.utils.Md5Util;
import com.heima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: chenjia
 * @Date: 2023/11/22 - 11 - 22 - 17:42
 * @Description: com.heima.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
@Validated//对注册接口的参数进行合法性校验
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register") //-------->注册请求
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password){
        //查询用户存不存在
        User u=userService.findByUserName(username);    

        if (u == null) {
            //没有占用，注册
            userService.register(username,password);
            return Result.success();
        }else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password){
        //根据用户名查询用户
        User u = userService.findByUserName(username);
        if (u==null){
            return Result.error("用户名不存在");
        }

        //判断密码是否真确 loginUser对象中的password是密文
        if(Md5Util.getMD5String(password).equals(u.getPassword())){
            //登录成功
            Map<String,Object> claims= new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.genToken(claims);
            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,7, TimeUnit.DAYS);
            return Result.success(token);
        }
            return Result.error("密码错误");
    }

    //获取用户详细信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        //根据用户名查询用户
      /*  Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updataAvatat(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> Params, @RequestHeader("Authorization") String token){
        //1.校验参数
        String oldPwd = Params.get("old_pwd"); //旧密码
        String newPwd = Params.get("new_pwd"); //新密码
        String rePwd = Params.get("re_pwd");  //确认新密码

        //判断是否为空
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
             return Result.error("缺少必要的参数");
        }
         //原密码是否正确
        //调用userService根据用户名拿到原密码，再和old_pwd比对
        Map<String,Object> map=ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的密码不一样");
        }

        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}
