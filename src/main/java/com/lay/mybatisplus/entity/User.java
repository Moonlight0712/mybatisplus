package com.lay.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dragon code!
 * @create 2022-08-23 16:03
 * Alt + 7打开当前类的一个结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//设置数据库中表名和我们的实体类进行映射，如果已经是相同的就没必要加这个注解
@TableName("t_user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
