package com.wj.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author w
 * {@code @time:} 11:52
 * Description: 接口
 */
@Mapper
public interface UserDemoMapper extends BaseMapper<User> {
}
