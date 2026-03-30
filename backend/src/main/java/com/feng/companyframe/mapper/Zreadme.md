特殊字符   替代符号

     &            &amp;

     <            &lt;

     >            &gt;

     "             &quot;

     '              &apos;
```sql
    <where>
      deleted = 1
      <if test="roleId != null and roleId != ''">
        and id = #{roleId}
      </if>
      <if test="roleName != null and roleName != ''">
        and name like concat('%', #{roleName}, '%')
      </if>
      <if test="status != null and status != ''">
        and status = #{status}
      </if>
      <if test="startTime != null and startTime != ''">
        and start_time &gt;= #{startTime}
      </if>
      <if test="endTime != null and endTime != ''">
        and start_time &lt;= #{endTime}
      </if>
    </where>
```

@Param("sysUser")注解
int deletedUsers(@Param("sysUser") SysUser sysUser, @Param("list") List<String> list);