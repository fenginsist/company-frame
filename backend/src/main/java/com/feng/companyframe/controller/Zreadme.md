@RequestParam(value="aa", required = false)  String id
1. value：前端传过来的名为 aa, 与id 进行绑定
2. require: 标志着此属性id 是否为必须的， true 为必须，如果不传id 则报错，false 为不必须，不传此值不报错


token三数据：
{
  "alg": "HS256"
}
{
  "jwt-permissions-key_": [
    "sys:user:list",
    "sys:dept:update",
    "sys:permission:add",
    "sys:user:role:update",
    "sys:user:add",
    "sys:dept:delete",
    "sys:permission:update",
    "sys:role:update",
    "sys:log:delete",
    "sys:role:detail",
    "sys:dept:list",
    "sys:user:delete",
    "sys:dept:add",
    "sys:role:delete",
    "sys:user:update",
    "sys:permission:delete",
    "sys:role:list",
    "sys:permission:list",
    "sys:role:add",
    "sys:log:list"
  ],
  "sub": "9a26f5f1-cbd2-473d-82db-1d6dcf4598f8",
  "jwt-roles-key_": [
    "超级管理员"
  ],
  "iss": "fengfanli.com",
  "jwt-user-name-key": "admin",
  "exp": 1583418923,
  "iat": 1583411723
}
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  your-256-bit-secret
)