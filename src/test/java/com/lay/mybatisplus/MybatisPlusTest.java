package com.lay.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.DeleteBatchByIds;
import com.lay.mybatisplus.entity.User;
import com.lay.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Dragon code!
 * @create 2022-08-23 16:14
 */
@SpringBootTest
public class MybatisPlusTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        //BaseMapper的条件查询，null表示没有条件，通过条件构造器来查询一个list集合
        //为什么没有指定去哪个表中查询数据还可以将数据给查询出来呢？
        //答案是BaseMapper接口指定了泛型
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 增加一条数据
     * 其中的id是通过mybatis-plus自带的雪花算法生成的id
     */
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setAge(11);
        user.setEmail("854108766@qq.com");
        user.setName("汤世佳");
        int result = userMapper.insert(user);
        String info = result == 1 ? "插入成功" : "插入失败";
        System.out.println(info);
    }

    /**
     * 删除一条数据
     * alt+7 查看basemapper接口中的方法
     */
    @Test
    public void testDeleteUser(){
        //注意默认是int类型如果使用的是非常长的id的话会出现编译错误，正确的做法是在数字的结尾加上一个L表示是Long类型的数据
/*        int result = userMapper.deleteById(1561998784409235457L);
        System.out.println(result);*/

/*        //通过map中的条件来删除
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);*/

        //通过id进行批量删除，使用的方法是in（1l,2l,3l），而不是 id = 1l or id = 2l or id = 3l
        List<Long> list = Arrays.asList(1l, 2l, 3l);
        int result = userMapper.deleteBatchIds(list);
        System.out.println(result);
    }

    /**
     * 按照id来进行更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        //这里的id就是where id = 的值
        user.setId(4l);
        user.setEmail("666@qq.com");
        user.setName("易磊");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    /**
     * 按照id来执行查询操作
     */
    @Test
    public void testSelect(){
        User user = userMapper.selectById(1l);
        System.out.println(user);
    }

    /**
     * 根据多个id来查询多个用户
     */
    @Test
    public void testSelectByIds(){
        ArrayList<Long> list = new ArrayList<Long>(Arrays.asList(1l,2l,3l));
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);
    }

    /**
     * 根据条件来查询用户
     * sql: SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
     * 可以看到生成的sql语句并没有使用到or而是使用到and来连接查询
     */
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "易磊");
        map.put("age",21);
        System.out.println(userMapper.selectByMap(map));

        //查询所有的数据
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 实现两表联查或者是实现我们的自定义sql语句该怎么实现
     * mybatis-plus在mybatis的基础上只做增强而不做改变！
     */

    @Test
    public void testSelectMapById(){
        //首先要保证我们的resource下的mapper接口能够被扫描到，默认是/mapper/*/*.xml,但是如果自定义的话需要到我们的yml文件中配置mapper-location的位置
        Map<String, Object> map = userMapper.selectMapById(3l);
        System.out.println(map);
    }

    /**
     * 查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
     * sql: SELECT id,name,age,email FROM t_user WHERE (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
     */
    @Test
    public void testQueryWrapper() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "a").between(User::getAge,20,30).isNotNull(User::getEmail);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询用户名包含a并且(年龄在20到30之间，并且邮箱不为null的用户信息)
     * sql:
     */
    @Test
    public void testQueryWrapper1() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName,"a").and(i-> i.between(User::getAge,20,30).isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void test08() {
//定义查询条件，有可能为null（用户未输入或未选择）
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//StringUtils.isNotBlank()判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username","a");
        }
        if(ageBegin != null){
            queryWrapper.ge("age", ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.le("age", ageEnd);
        }

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }




}
