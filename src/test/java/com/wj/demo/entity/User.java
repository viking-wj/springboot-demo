package com.wj.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDel;
}
