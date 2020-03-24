# 一、项目需求
开发一个后台权限管理系统，技术链：springboot+shiro+jwt+Redis+thymeleaf

# 二、开发项目流程 简洁版
yml 配置的顺序

* 1、端口
* 2、项目名称
* 3、加上jdbc的依赖后 ，就要配置 spring.datasource 数据源
* 4、整合 swagger2
* 5、整合 redis
* 6、整合mybatis 和 逆向工程
* 7、jwt
* 8、分页
* 9、thymeleaf


# 三、开发项目流程 1
1. 搭建springboot项目
2. 添加springboot坐标：web、thymeleaf、mybatis、mysql-connector-java、
    spring-boot-starter-jdbc、druid、redis commons-pool2、swagger2 swagger-ui、
    shiro-spring、devtools、fastjson、jjwt、aop、lombok、test
   
3. 配置 application.yml 文件，配置数据源。 配置 server：port、spring:application:name、datasource 三个配置即可
   * 启动项目，因为使用的是 阿里的 druid数据源，所以登录此 URL 调试：http://localhost:8083/druid/index.html

4. 在阿里云服务器上docker 容器MySQL镜像上，创建一个用户表，
   配置 generatorConfig.xml, 在pom.xml文件的下面添加一个 mybatis 的 plugin插件，
   双击idea右侧maven-》Plugin-》mybatis-generator-》mybatis-generator:generate，进行 mybatis 逆向工程。生成 实体类和xml(mapper)文件和接口。
   
5. 配置mybatis
   在项目主类上添加注解扫描 mapper 接口文件 @MapperScan("com.feng.companyframe.mapper")
    yml文件配置：mybatis:mapper-locations: classpath:mapper/*.xml

6. 集成swagger2 文档
   yml文件设置开关 #swagger 开关 swagger2:enable: true
   创建 Swagger 配置文件：SwaggerConﬁg.java
   主类 加启动类：@EnableSwagger2 
   开发时：在controller 上添加@Api 和 @ApiOperation 等注解进行 swagger 文档开发。
   * 启动项目：调试URL：http://localhost:8083/swagger-ui.html
   
7. 集成配置 Redis
   yml 文件 添加关于 Redis 的配置文件
   创建 Redis 配置文件，需要对 value 进行序列化，否则存到数据库中为处理后的值
   这里创建了关于 Redis 的工具类。
   * 启动项目 不报错，即可，可以测试上面的两个组件自带的URL。

# 四、开发项目流程 2

1. 封装前后端数据 DataResult 类 : 前后端分离需要进行数据统一，所以首先对项目进行数据统一封装。
   把 自定义的 code 和 errorMsg 封装成enum枚举类。【com.feng.companyframe.exception.code.BaseResponseCode枚举类和ResponseCodeInterface接口】
   然后新增 controller 接口在swagger中进行测试
   
   TestController： 测试一、测试二、测试三 都是
   
2. 全局异常处理
   使用 @RestControllerAdvice 和 @ExceptionHandler 两个类进行全局异常处理开发
   【com.feng.companyframe.exception.handler.RestExceptionHandler 类 】
   然后新增 controller 接口在swagger中进行测试
   
   TestController：测试三、测试四 是
   
   BusinessException 类 这个为自定义运行时异常 runtimeException类
   然后新增 controller 接口进行测试
   
   TestController： 测试五 是
   
3. Hibernate Validator 注解使用
   然后在 全局异常处理 中进行编写对应的 ExceptionHandler，为：MethodArgumentNotValidException.class
   然后新增 controller 接口进行测试
   
   TestController：测试五、测试六 皆可

4、JWT
   工具类封装 JwtTokenUtil，不交于 spring 管理
   相关参数配置到 yml 文件中，POJO类，初始化类（将数据设置到 工具类中），
   
5、用户认证签发token
   
6、mybatis-使用 pagehelper 实现分页封装
   
7、Spring Boot+Shiro+JWT+redis 前后端分离脚手架-自定义AccessControlFilter token认证
   这里就到了开发 shiro 的时候。
   * a、先开发CustomAccessControlerFilter
   * b、CustomUsernamePasswordToken
   * c、CustomHashedCredentialsMatcher
   * d、CustomRealm
   * e、配置 shiro 管理器，里面必备：Realm、SecurityManager、ShiroFilterFactoryBean
   * f、配置缓存工具类RedisCache 和 缓存管理器RedisCacheManager
   * g、把第 6 步中的缓存管理器 配置到 shiroConfig 中。

# 五、总结
以上 就是一个后端开发的基础脚手架。
包含技术栈：springboot+shiro+jwt+redis+mybatis+MySQL


   
   
# 六、需要深入了解学习的 
1. 基于 aop 的日志   
2. 