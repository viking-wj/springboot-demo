

# SpringBoot项目搭建

## 最简（SpringBoot+Mybatis-Plus）

1. 新建项目

   ![image-20231228112607422](https://gitee.com/Wj-Space/pic-go/raw/master/PicGO/202312281126489.png)

2. 添加SpringBoot依赖

   pom.xml

   ```xml
   <!--继承SpringBoot父工程-->
   <parent>
         <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>2.6.7</version>
       </parent>
   <!--在dependencies标签中 添加web依赖-->
    <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
   <!--添加测试依赖（可选）-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-test</artifactId>
               <scope>test</scope>
           </dependency>
   ```

   

3. 编写项目启动类和配置文件

   DemoApplicatioin.java

   ```java
   @SpringBootApplication
   public class DemoApplication {
       public static void main(String[] args) {
           SpringApplication.run(DemoApplication.class, args);
       }
   }
   ```

​		application.yml
   ```yml
   server:
     port: 8000
   spring:
     application:
       name: demo
   
   ```

   

4. 引入Mybatis-Plus依赖和MySQL驱动

pom.xml

```xml
<!--统一定义版本号位置-->
<properties>
      
        <mybatis-plus-spring-boot-starter>3.5.5</mybatis-plus-spring-boot-starter>
    </properties>

<!---添加Mybatis-plus依赖-->
<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis-plus-spring-boot-starter}</version>
        </dependency>

        <!--mysql 驱动，不同SpringBoot版本可能会需要定义版本号-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```



4. 在配置文件中添加对应配置

```yaml
spring:
  datasource:
    url: jdbc:mysql://数据库ip：端口号/数据库名称?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: 用户名
    password: 密码
    driver-class-name: com.mysql.cj.jdbc.Driver
```

**常用配置**

```java
@Configuration
@MapperScan("com.wj.demo.mapper")
public class MybatisPlusConfig {

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 如果配置多个插件,切记分页最后添加
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 如果有多数据源可以不配具体类型 否则都建议配上具体的DbType
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
```

自动填充配置

```java
@Component
@Slf4j
public class MyBatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        // 或者
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        // 或者
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        // 或者
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        // 或者
        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }
}
```

