# 项目需求
开发一个后台权限管理系统，技术链：springboot+shiro+jwt+Redis+thymeleaf

# 开发项目流程
1. 搭建springboot项目
2. 添加springboot坐标：web、thymeleaf、mybatis、mysql-connector-java、
    spring-boot-starter-jdbc、druid、redis commons-pool2、swagger2 swagger-ui、
    shiro-spring、devtools、fastjson、jjwt、aop、lombok、test
   
3. 配置 application.yml 文件，配置数据源。 配置 server：port、spring:application:name、datasource 三个配置即可
   * 启动项目，因为使用的是 阿里的 druid数据源，所以登录此 URL 调试：http://localhost:8083/druid/index.html

3. 在阿里云服务器上docker 容器MySQL镜像上，创建一个用户表，
   配置 generatorConfig.xml, 在pom.xml文件的下面添加一个 mybatis 的 plugin插件，
   双击idea右侧maven-》Plugin-》mybatis-generator-》mybatis-generator:generate，进行 mybatis 逆向工程。生成 实体类和xml(mapper)文件和接口。
   
4. 配置mybatis
   在项目主类上添加注解扫描 mapper 接口文件 @MapperScan("com.feng.companyframe.mapper")
    yml文件配置：mybatis:mapper-locations: classpath:mapper/*.xml

5. 集成swagger2 文档
   yml文件设置开关 #swagger 开关 swagger2:enable: true
   创建 Swagger 配置文件：SwaggerConﬁg.java
   主类 加启动类：@EnableSwagger2 
   开发时：在controller 上添加@Api 和 @ApiOperation 等注解进行 swagger 文档开发。
   * 启动项目：调试URL：http://localhost:8083/swagger-ui.html
   
6. 集成配置 Redis
   yml 文件 添加关于 Redis 的配置文件
   创建 Redis 配置文件，需要对 value 进行序列化，否则存到数据库中为处理后的值
   这里创建了关于 Redis 的工具类。
   * 启动项目 不报错，即可，可以测试上面的两个组件自带的URL。
   
# 总结一、
项目的框架到这里算是搭建完成了。任一框架可以说都可以复用此框架。

7. 封装前后端数据DataResult : 前后端分离需要进行数据统一，所以首先对项目进行数据统一封装。