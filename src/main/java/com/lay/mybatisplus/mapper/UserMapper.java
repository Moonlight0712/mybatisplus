package com.lay.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lay.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Dragon code!
 * @create 2022-08-23 16:09
 */
//直接选择继承mybatisplus的BaseMapper，里面包含很多的方法，
//都是mybatisplus为我们提供的方法

public interface UserMapper extends BaseMapper<User> {
    /**
     * @param id
     * @return
     */
    //自定义我们的mapper方法
    Map<String,Object> selectMapById(long id);
}
