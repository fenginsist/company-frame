刷新token 流程
1. 先编写好 刷新token的接口
   1) 设置 是否存在刷新的标识   refreshToken()方法中：：redis set（Constant.JWT_REFRESH_IDENTIFICATION + newAccessToken）；

2. 在 管理员 赋予用户角色时，进行token刷新流程
   1) 调用保存用户角色的接口/api/user/roles
   2) 进入到 token 过滤时进行类: CustomHashedCredentialsMatcher
   3) 然后在业务层进行保存数据到数据库，
   4) 在setUserOwnRole()方法：redis set Constant.JWT_REFRESH_KEY + vo.getUserId()
   5) 重点：保存成功后，紧跟下一次调用另一接口时，会进入到  token 过滤类: CustomHashedCredentialsMatcher 中的最后一个校验器
   6) 重点：先判断 Constant.JWT_REFRESH_KEY + vo.getUserId()，再判断 Constant.JWT_REFRESH_IDENTIFICATION + newAccessToken
   7) 重点：然后返回到前端 4010002， token失效,请刷新token
   8) 重点：前端进行刷新token，返回新的 token ，在自动刷新上一个请求接口
