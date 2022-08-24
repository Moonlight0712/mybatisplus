package com.lay.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lay.mybatisplus.entity.User;
import com.lay.mybatisplus.mapper.UserMapper;
import com.lay.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Dragon code!
 * @create 2022-08-23 19:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
