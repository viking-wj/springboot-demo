package com.wj.demo;

import com.wj.demo.entity.User;
import com.wj.demo.mapper.UserDemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author w
 * {@code @time:} 11:50
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MybatisPlusTest {

    @Resource
    private UserDemoMapper userDemoMapper;

    @Test
    public void selectUser(){
        List<User> users = userDemoMapper.selectList(null);
        log.info(users.toString());
    }
    @Test
    public void insertUser(){
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        int insert = userDemoMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId("1998");
        user.setName("李四");
        userDemoMapper.updateById(user);
    }

    @Test
    public void deleteUser(){
        userDemoMapper.deleteById("1998");
    }
}
