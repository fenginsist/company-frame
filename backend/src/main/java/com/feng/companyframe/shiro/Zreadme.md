# 一、开发配置顺序
1、先开发CustomAccessControlerFilter 过滤器
2、CustomUsernamePasswordToken 
3、CustomHashedCredentialsMatcher 自定义过滤token
4、CustomRealm 自定义数据源，进行认证和授权
5、编写 shiro 配置类，里面必备：Realm、SecurityManager、ShiroFilterFactoryBean
6、编写 配置缓存工具类RedisCache 和 缓存管理器 RedisCacheManager 两个关于shiro的缓存类
7、在 shiro 配置类中 把第 6 步中的缓存管理器 配置到 shiroConfig 中。

# 二、未加入缓存的 shiro 代码执行顺序
## 1. 登录过程，登录过程不涉及到 授权部分。
1、登录：用户名、密码=》进入到 /api/user/login接口，这个接口在 shiroConfig 中 的 filter 设置成白名单

2、在UserServiceImpl 中的 login() 方法中进行登录。密码验证成功后返回，携带 token。不涉及到shiro。

3、接下来调用/index/home接口，跳转到 home视图。

4、home视图初始化调用 /api/home 接口，进入shiro配置好的filter过滤器：CustomAccessControllerFilter =》 从请求头中获取token 并验证是否有token

5、根据 已有token 生成 CustomUsernamePasswordToken ，通过 getSubject().login()，然后 进入 自定义的 CustomRealm 类，进行认证

6、先进入到 doGetAuthenticationInfo() 进行用户认证，返回用户信息。
     进入 AuthenticatingRealm 类中的  getAuthenticationInfo() 方法，
     先判断 缓存中是否有信息，没有则获取 doGetAuthenticationInfo()  中返回 用户信息。（这里也是自定义 配置缓存管理器的 关键之处）

7、然后 进入到  CustomHashedCredentialsMatcher类   doCredentialsMatch()  中，进行对token 进一步 处理、判断。  （重要、这里是自定义的）
   一直下一步 会到 DefaultSecurityManager 类中的 login（）方法。
   然后回到 realm 中进入权限认证。
   这里不涉及到权限问题。 因为以上两个接口 都无注解@RequiresPermissions()

8、进而进入 controller层 的 /api/home接口 方法，开始进行业务逻辑处理。


## 2. 请求其他接口，如：权限部分， 请求其他接口如有注解@RequiresPermissions()，则进行权限授权
1、点击 权限菜单管理，发送请求 /index/menus 此请求 直接跳转视图，进到菜单页面，这里在shiro设置为了白名单

2、到了页面之后，会初始化页面获取数据，发送请求接口 /api/permissions , 该接口有注解：@RequiresPermissions("sys:permission:list")

3、发送此接口，会先进入授权方法（），到 AuthorizingRealm 类方法：getAuthorizationInfo()，通过 this.doGetAuthorizationInfo(principals)方法
     进入到 CustomRealm 中 doGetAuthorizationInfo() 授权方法 ，在此方法中：从token中获取所有的角色和权限信息，经角色和权限信息给授权器SimpleAuthorizationInfo，
     代码写到这里，逻辑上接下来到接口的部分
     在返回到this.doGetAuthorizationInfo(principals)方法。

第三步会重复好几次，why ？？？，是因为界面中的 shiro标签 有三个，

以下与上面的例1的第四部往后一致。

4、然后 进入到过滤器CustomAccessControllerFilter ， =》 从请求头中获取token 并验证是否有token

5、根据 已有token 生成 CustomUsernamePasswordToken ，通过 getSubject().login()，然后 进入 自定义的 CustomRealm 类，进行认证

6、先进行 doGetAuthenticationInfo() 用户认证，返回用户信息。

7、然后 进入到  CustomHashedCredentialsMatcher类   doCredentialsMatch()  中，进行对token 进一步 处理、判断。  （重要、这里是自定义的）

8、然后进入到 controller层 的 /api/permissions 接口 方法，开始进行业务逻辑处理。
    授权管理器在与注解进行匹配。@RequiresPermissions("sys:permission:list")，如果授权信息中有此注解的信息，则可以请求，否则被异常捕获


# 三、加入缓存管理的 shiro 代码执行顺序
主要的类 、方法 CustomRealm （ 继承AuthorizingRealm）类 getAuthorizationInfo() 方法

1、只要界面无shiro标签，则不会进入到授权的代码中，上面的两个例子流程中也证实了这一点。

2、login、home、页面中 无shiro标签，则不会进入到shiro授权代码中




# 疑问
* 什么时候进入到 这个方法的doGetAuthorizationInfo() ：当发起请求的页面 有shiro标签 的时候 会进行授权
* 缓存管理器的配置 
* 缓存管理器中 的 缓存工具类的重写 为什么不能注入 redisUtils 工具类。


# redis操作
1. redis中 存有 key: jwt， value： userId。  在哪存来着??? 退出的时候进行储存,一直有的
* 把 token 加入黑名单 禁止再访我们的系统资源 redisUtil.set(Constant.JWT_ACCESS_TOKEN_BLACKLIST + accessToken, userId,
* 把 refreshToken 加入黑名单 禁止再拿来刷新token  redisUtil.set(Constant.JWT_REFRESH_TOKEN_BLACKLIST + refreshToken, userId,

* 在 CustomHashedCredentialsMatcher 第五步 进行判断。

2. shiro的redis缓存 ，储存为 缓存管理器 缓存管理器储存，  8种情况删除，刷新token的地方（也就是更新权限的地方）就要删除旧的缓存。
key: 静态常量String IDENTIFY_CACHE_KEY="com.feng.companyframe.shiro.CustomRealm.authorizationCache:";
value: 用户角色 权限，为 doGetAuthorizationInfo() 方法中的 SimpleAuthorizationInfo 授权信息（包括角色和权限）。
* 需要授权时，在 redisCache 中 put() 方法进行存储，
* 何时删除：
a、更新权限时候删除
b、删除权限时候删除
c、更新角色时候删除
d、删除角色删除
e、首页更新密码的时候删除
f、后台管理员删除用户时候删除
g、管理员赋予用户角色时候删除
h、退出时 缓存管理器会自动清除
redisUtil.delete(Constant.IDENTIFY_CACHE_KEY+userId);