# 一、配置顺序
1、先开发CustomAccessControlerFilter
2、CustomUsernamePasswordToken
3、CustomHashedCredentialsMatcher
4、CustomRealm
5、编写 shiro 配置类，里面必备：Realm、SecurityManager、ShiroFilterFactoryBean
6、配置缓存工具类RedisCache 和 缓存管理器RedisCacheManager
7、把第 6 步中的缓存管理器 配置到 shiroConfig 中。

# 二、shiro 使用顺序
1、登录：用户名、密码=》进入到 /api/user/login接口，这个接口在 shiroConfig 中 的 filter 设置成白名单

2、在UserServiceImpl 中的 login() 方法中进行登录。密码验证成功后返回，携带 token。不涉及到shiro。

3、接下来处理其他接口，进入过滤器：CustomAccessControllerFilter =》 从请求头中获取token 并验证是否有token

4、根据 已有token 生成 CustomUsernamePasswordToken ，通过 getSubject().login()，然后 进入 自定义的 CustomRealm 类，进行认证

5、先进行 doGetAuthenticationInfo() 用户认证，返回用户信息。
     进入 AuthenticatingRealm 类中的  getAuthenticationInfo()方法，
     先判断 缓存中是否有信息，没有则获取doGetAuthenticationInfo()  中返回 用户信息。（这里也是自定义 配置缓存管理器的 关键之处）

6、然后 进入到  CustomHashedCredentialsMatcher类   doCredentialsMatch()  中，进行对token 进一步 处理、判断。  （重要、这里是自定义的）
    一直下一步 会到 DefaultSecurityManager类中的 login（）方法。
     然后回到 realm 中进入权限认证。

7、在进行 doGetAuthorizationInfo() 权限认证，返回 权限信息。
     进入 AuthenticatingRealm 类中的  getAuthorizationInfo()方法，
      先判断 缓存中是否有信息，没有则获取doGetAuthenticationInfo()  中返回 用户信息。 （这里也是自定义 配置缓存管理器的 关键之处）

8、进而进入 controller层 开始 业务逻辑处理。



疑问的地儿
1、什么时候进入到 这个方法的doGetAuthorizationInfo()
2、缓存管理器的配置
3、缓存管理器中 的 缓存工具类的重写 为什么不能注入 redisUtils 工具类。
