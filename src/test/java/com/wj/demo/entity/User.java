package com.wj.demo.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author w
 * {@code @time:} 11:54
 * Description:
 */
@TableName("user_demo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String name;

    private Integer age;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDel;
}
