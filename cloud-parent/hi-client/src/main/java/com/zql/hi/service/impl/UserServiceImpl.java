package com.zql.hi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zql.hi.entity.User;
import com.zql.hi.mapper.UserMapper;
import com.zql.hi.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{
}
