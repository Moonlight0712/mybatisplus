package com.lay.mybatisplus;

import com.lay.mybatisplus.entity.User;
import com.lay.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dragon code!
 * @create 2022-08-23 19:26
 */
@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    private UserService userService;

    /**
     * 测试总数据的条数
     */
    @Test
    public void testGetCount(){
        int count = userService.count();
        System.out.println(count);
    }

    /**
     * 插入多条记录
     */
    @Test
    public void testAddUsersByIds(){
        ArrayList<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("tsj"+i);
            user.setAge(20+i);
            user.setEmail(i+"@qq.com");
            list.add(user);
        }
        userService.saveBatch(list);
    }


    @Test
    public void testGetUsersByIds(){
        ArrayList<Long> list = new ArrayList<Long>(Arrays.asList(1l,2l,3l,4l,5l));
        List<User> users = userService.listByIds(list);
        users.forEach(System.out::println);
    }


}
