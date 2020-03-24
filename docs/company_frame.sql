/*
 Navicat Premium Data Transfer

 Source Server         : 123.56.30.106冯凡利
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 123.56.30.106:3306
 Source Schema         : company_frame

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 19/03/2020 19:17:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `dept_no` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `pid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级id',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态(1:正常；0:弃用)',
  `relation_code` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '为了维护更深层级关系(规则：父级关系编码+自己的编码)',
  `dept_manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门经理user_id',
  `manager_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门经理名称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门经理联系电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('06d45b3d-2134-4b36-a9df-d43d2e35041f', 'YXD0000001', '智慧社区', '0', 1, 'YXD0000001', NULL, '冯凡利', '17862970812', '2020-02-19 15:02:00', NULL, 1);
INSERT INTO `sys_dept` VALUES ('09e56250-ca9f-424f-96fb-99cd38c63a54', 'YXD0000002', '智慧社区1栋', '06d45b3d-2134-4b36-a9df-d43d2e35041f', 1, 'YXD0000001YXD0000002', NULL, '小刘', '13562739640', '2020-02-19 15:07:39', NULL, 1);
INSERT INTO `sys_dept` VALUES ('13092a3e-2234-475b-8971-9da196780913', 'YXD0000003', '智慧社区2栋', '06d45b3d-2134-4b36-a9df-d43d2e35041f', 1, 'YXD0000001YXD0000003', NULL, '小程', '17084598514', '2020-02-19 15:09:34', '2020-02-28 14:41:56', 1);
INSERT INTO `sys_dept` VALUES ('22d93869-b95e-4c9a-ba0c-afc79cff9713', 'YXD0000005', '1#101室', '09e56250-ca9f-424f-96fb-99cd38c63a54', 1, 'YXD0000001YXD0000002YXD0000005', NULL, '家庭人员', '12563259870', '2020-02-29 13:53:36', '2020-03-10 10:36:57', 1);
INSERT INTO `sys_dept` VALUES ('86a242b9-65d4-4ff5-b70c-54fc4add1f1c', 'YXD0000001', '2#201', '13092a3e-2234-475b-8971-9da196780913', 1, 'YXD0000001YXD0000003YXD0000001', NULL, '冯杰', '13562739640', '2020-03-10 10:38:02', NULL, 1);
INSERT INTO `sys_dept` VALUES ('b7607afc-571d-4c23-90fb-1d7bdd89871e', 'YXD0000004', '智慧社区3栋', '06d45b3d-2134-4b36-a9df-d43d2e35041f', 1, 'YXD0000001YXD0000004', NULL, '小刘', '12352364320', '2020-02-27 23:25:24', '2020-02-28 14:42:11', 1);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `time` int(11) NULL DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限名称',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(如：sys:user:add)',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问地址URL',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源请求类型',
  `pid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单权限名称',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '排序',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '菜单权限类型(1:目录;2:菜单;3:按钮)',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态1:正常 0：禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', '', '部门管理', '', '/index/depts', 'GET', '2495c5da-d43c-4671-a67a-36565f5b61b9', 98, 2, 1, '2020-02-18 19:39:35', '2020-02-25 13:35:53', 1);
INSERT INTO `sys_permission` VALUES ('1920d007-f71f-4e28-801e-574d3c51f60a', 'btn-role-detail', '角色详情权限', 'sys:role:detail', '/api/role/*', 'GET', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', 100, 3, 1, '2020-03-02 17:44:51', NULL, 1);
INSERT INTO `sys_permission` VALUES ('205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', '', '用户管理', '', '/index/users', 'GET', '2495c5da-d43c-4671-a67a-36565f5b61b9', 99, 2, 1, '2020-02-19 15:31:43', '2020-02-25 13:37:14', 1);
INSERT INTO `sys_permission` VALUES ('24187547-f91b-4ed4-89ff-b832a279f652', '', '接口管理', '', '/swagger-ui.html', 'GET', '6686589e-2373-4040-9ba5-65d2b3390f94', 97, 2, 1, '2020-02-29 14:02:15', '2020-02-29 14:03:17', 1);
INSERT INTO `sys_permission` VALUES ('2495c5da-d43c-4671-a67a-36565f5b61b9', '', '组织管理部门', '', '', '', '0', 100, 1, 1, '2020-02-17 17:27:27', NULL, 1);
INSERT INTO `sys_permission` VALUES ('3c0a1755-2e9b-4605-8950-7be38d734699', 'btn-user-add', '新增用户权限', 'sys:user:add', '/api/addUser', 'POST', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', 100, 3, 1, '2020-03-02 12:29:55', NULL, 1);
INSERT INTO `sys_permission` VALUES ('40c17bb4-bc2d-4dc0-bedc-441e17f01267', 'btn-permission-update', '更新菜单权限', 'sys:permission:update', '/api/updatePermission', 'PUT', '4380e8d0-e3ba-4d46-af80-ec27cd5ab769', 100, 3, 1, '2020-03-02 17:26:33', NULL, 1);
INSERT INTO `sys_permission` VALUES ('4380e8d0-e3ba-4d46-af80-ec27cd5ab769', '', '权限菜单管理', '', '/index/menus', 'GET', '2495c5da-d43c-4671-a67a-36565f5b61b9', 100, 2, 1, '2020-02-17 17:34:19', NULL, 1);
INSERT INTO `sys_permission` VALUES ('5277110e-f38a-470d-9a28-a389cc5497a6', 'btn-dept-add', '新增部门权限', 'sys:dept:add', '/api/addDept', 'POST', '13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', 100, 3, 1, '2020-03-02 17:22:07', NULL, 1);
INSERT INTO `sys_permission` VALUES ('52ee7d40-8ca9-4725-a6ef-e7da8a3657b7', '', '地图管理', '', '', '', '0', 94, 1, 1, '2020-02-29 23:32:50', NULL, 1);
INSERT INTO `sys_permission` VALUES ('5a8c2021-f93d-4324-8271-ea7685fd95d0', '', '日志管理', '', '/index/logs', 'GET', '6686589e-2373-4040-9ba5-65d2b3390f94', 100, 2, 1, '2020-02-28 19:58:37', NULL, 1);
INSERT INTO `sys_permission` VALUES ('603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', '', '角色管理', '', '/index/role', 'GET', '2495c5da-d43c-4671-a67a-36565f5b61b9', 97, 2, 1, '2020-02-17 19:09:51', '2020-02-25 13:36:18', 1);
INSERT INTO `sys_permission` VALUES ('6059ad67-2b5b-4c83-970a-8b3e8fb1fb71', 'btn-role-delete', '删除角色权限', 'sys:role:delete', '/api/deleteRole', 'DELETE', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', 100, 3, 1, '2020-03-02 17:45:36', NULL, 1);
INSERT INTO `sys_permission` VALUES ('6686589e-2373-4040-9ba5-65d2b3390f94', '', '系统管理', '', '', '', '0', 98, 1, 1, '2020-02-28 19:56:48', NULL, 1);
INSERT INTO `sys_permission` VALUES ('6ec3c2ab-59c4-4a6f-82b9-b9747daf2287', 'btn-role-update', '更新角色权限', 'sys:role:update', '/api/updateRole', 'PUT', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', 100, 3, 1, '2020-03-02 17:43:46', NULL, 1);
INSERT INTO `sys_permission` VALUES ('87b27548-cdcd-480d-bc9c-aa41766ed534', 'btn-role-add', '新增角色权限', 'sys:role:add', '/api/addRole', 'POST', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', 100, 3, 1, '2020-03-02 17:43:06', NULL, 1);
INSERT INTO `sys_permission` VALUES ('87c1e5f6-462d-4339-98fa-828261ce658b', 'btn-dept-list', '查询部门信息列表权限', 'sys:dept:list', '/api/depts', 'POST', '13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', 100, 3, 1, '2020-03-02 17:21:09', NULL, 1);
INSERT INTO `sys_permission` VALUES ('90f35d56-4037-4ae4-9e80-9315f2b35497', '', 'SQL监控', '', '/druid/index.html', 'GET', '6686589e-2373-4040-9ba5-65d2b3390f94', 96, 2, 1, '2020-02-29 14:02:53', NULL, 1);
INSERT INTO `sys_permission` VALUES ('92de3f02-f5f1-42ef-a3f2-d5a9133fc3c3', 'btn-permission-add', '新增菜单权限', 'sys:permission:add', '/api/addPermission', 'POST', '4380e8d0-e3ba-4d46-af80-ec27cd5ab769', 100, 3, 1, '2020-03-02 17:25:48', NULL, 1);
INSERT INTO `sys_permission` VALUES ('983adfaf-f6da-4fce-bc74-78db35459dc3', 'btn-user-update', '列表更新用户信息权限', 'sys:user:update', '/api/updateUser', 'PUT', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', 100, 3, 1, '2020-03-02 17:18:53', NULL, 1);
INSERT INTO `sys_permission` VALUES ('9c2dd042-6e3b-41e6-9ddd-84d5291e8f49', 'btn-user-delete', '删除用户权限', 'sys:user:delete', '/api/deletedUser', 'PUT', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', 100, 3, 1, '2020-03-02 17:19:52', NULL, 1);
INSERT INTO `sys_permission` VALUES ('9e673112-0aff-4bbc-a0f4-ca36fe5aa962', 'btn-permission-delete', '删除菜单权限', 'sys:permission:delete', '/api/deletePermission', 'DELETE', '4380e8d0-e3ba-4d46-af80-ec27cd5ab769', 100, 3, 1, '2020-03-02 17:27:11', NULL, 1);
INSERT INTO `sys_permission` VALUES ('a9ec3c8c-7730-4a38-9a07-6d2ede4f74c2', 'btn-log-delete', '删除日志权限', 'sys:log:delete', '/api/deleteLogs', 'DELETE', '5a8c2021-f93d-4324-8271-ea7685fd95d0', 100, 3, 1, '2020-03-02 17:46:55', NULL, 1);
INSERT INTO `sys_permission` VALUES ('adea7aa2-0270-487e-9508-0fdf36726907', '', '查看地图', '', '/index/map', 'GET', '52ee7d40-8ca9-4725-a6ef-e7da8a3657b7', 93, 2, 1, '2020-02-29 23:33:34', NULL, 1);
INSERT INTO `sys_permission` VALUES ('b4412528-9e1d-4891-9802-29f110c03f28', 'btn-role-list', '查询角色列表权限', 'sys:role:list', '/api/roles', 'POST', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', 100, 3, 1, '2020-03-02 17:27:43', NULL, 1);
INSERT INTO `sys_permission` VALUES ('b45bbd3f-dccf-466e-9dce-b2568783df12', '', '权限菜单管理2', '', '/index/menus', 'GET', '2495c5da-d43c-4671-a67a-36565f5b61b9', 96, 2, 1, '2020-02-17 17:59:29', '2020-02-25 14:25:48', 0);
INSERT INTO `sys_permission` VALUES ('b7de7250-e244-46f8-a3a3-d33df724f2ba', 'btn-user-list', '查询用户信息列表权限', 'sys:user:list', '/api/users', 'POST', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', 100, 3, 1, '2020-03-02 12:27:15', NULL, 1);
INSERT INTO `sys_permission` VALUES ('be5722b1-bc20-40b5-a9d3-9ff53367f300', '', 'test01', '', '/test', '', '2495c5da-d43c-4671-a67a-36565f5b61b9', 96, 2, 1, '2020-02-25 14:29:21', '2020-03-02 11:49:04', 0);
INSERT INTO `sys_permission` VALUES ('c4038446-fa0b-44e2-9c40-2dc971cc366c', 'btn-dept-update', '更新部门信息权限', 'sys:dept:update', '/api/updateDept', 'PUT', '13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', 100, 3, 1, '2020-03-02 17:22:49', NULL, 1);
INSERT INTO `sys_permission` VALUES ('c469c6e5-512f-445c-9c5e-3f21c9286aa7', 'btn-permission-list', '查询菜单权限列表权限', 'sys:permission:list', '/api/permissions', 'POST', '4380e8d0-e3ba-4d46-af80-ec27cd5ab769', 100, 3, 1, '2020-03-02 17:25:05', NULL, 1);
INSERT INTO `sys_permission` VALUES ('caad778c-a7b1-444a-8f8d-5dd2d6d3f3f1', 'btn-log-list', '查询日志列表权限', 'sys:log:list', '/api/getLogs', 'POST', '5a8c2021-f93d-4324-8271-ea7685fd95d0', 100, 3, 1, '2020-03-02 17:46:13', NULL, 1);
INSERT INTO `sys_permission` VALUES ('cc7d4f67-75bf-48a5-9bd7-a392c0a72408', 'btn-user-update-role', '赋予用户角色权限', 'sys:user:role:update', '/api/user/roles', 'PUT', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', 100, 3, 1, '2020-03-02 12:31:21', NULL, 1);
INSERT INTO `sys_permission` VALUES ('d15de2e3-37f7-4799-b8af-1f6b754f03f5', 'btn-dept-delete', '删除部门权限', 'sys:dept:delete', '/api/deleteDept/*', 'DELETE', '13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', 100, 3, 1, '2020-03-02 17:24:06', NULL, 1);
INSERT INTO `sys_permission` VALUES ('eb4cbba4-738e-4480-b7e2-aebab6dd19d8', '', '权限菜单管理3', '', '/test', '', '2495c5da-d43c-4671-a67a-36565f5b61b9', 95, 2, 1, '2020-02-17 18:16:06', '2020-02-25 14:19:36', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0fee4b64-9d48-4cf1-b9a0-bfeaa723e7a0', '角色回显test', '角色回显test', 1, '2020-02-27 12:36:47', '2020-02-27 15:52:01', 0);
INSERT INTO `sys_role` VALUES ('1a815805-fab3-4146-8eec-46a2dafdd691', '超级管理员', '拥有所有权限', 1, '2020-02-18 15:26:52', '2020-02-27 15:56:16', 1);
INSERT INTO `sys_role` VALUES ('a09577b8-50b0-49fd-884f-c88a2601c613', '地图管理', '只有地图权限', 1, '2020-03-10 10:34:26', NULL, 1);
INSERT INTO `sys_role` VALUES ('b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', 'test', 'test', 1, '2020-02-27 15:59:17', '2020-02-27 15:59:56', 1);
INSERT INTO `sys_role` VALUES ('b3dda53b-73c0-4e11-9c4d-250661199e7a', 'test', '拥有所有', 1, '2020-02-20 19:58:04', '2020-02-27 15:59:49', 0);
INSERT INTO `sys_role` VALUES ('e4a87746-4d87-41ff-90d8-6347b6566dc2', '删除测试角色', '删除测试角色', 1, '2020-02-27 15:56:09', '2020-02-27 15:59:39', 0);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('00c959f3-0493-438f-840a-adb4a5fd56a1', '1a815805-fab3-4146-8eec-46a2dafdd691', 'caad778c-a7b1-444a-8f8d-5dd2d6d3f3f1', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('01e865dc-3db5-4fd7-afae-e948a66e4c55', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('02e3eb8c-cf75-4c35-a5df-f323883db904', '1a815805-fab3-4146-8eec-46a2dafdd691', '205f5f8f-8f05-4a76-ada9-0cc81c55a3c7', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('04793045-15e0-4a74-a44e-782657f82064', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('06fa7e0f-4dc6-4a90-ba1c-fe45263e5ed8', '1a815805-fab3-4146-8eec-46a2dafdd691', 'b7de7250-e244-46f8-a3a3-d33df724f2ba', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('0f8c0b7e-4de4-444a-8b3f-8b1a2541cfc9', '1a815805-fab3-4146-8eec-46a2dafdd691', 'd15de2e3-37f7-4799-b8af-1f6b754f03f5', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('11c247d4-3110-45f6-b366-b224d63e38b9', '1a815805-fab3-4146-8eec-46a2dafdd691', 'a9ec3c8c-7730-4a38-9a07-6d2ede4f74c2', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('1e62486e-1557-463c-9407-a0b02a802572', '1a815805-fab3-4146-8eec-46a2dafdd691', '6ec3c2ab-59c4-4a6f-82b9-b9747daf2287', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('258fac9f-258e-4bf2-9d28-966ed07c2db4', '1a815805-fab3-4146-8eec-46a2dafdd691', 'c4038446-fa0b-44e2-9c40-2dc971cc366c', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('2f02f1c2-5449-428e-b1ab-7dfe70e5161d', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '6686589e-2373-4040-9ba5-65d2b3390f94', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('312c4bf0-7f00-4f80-86e2-9871fd9b20b3', 'a09577b8-50b0-49fd-884f-c88a2601c613', '52ee7d40-8ca9-4725-a6ef-e7da8a3657b7', '2020-03-10 10:34:26');
INSERT INTO `sys_role_permission` VALUES ('33a01027-7d2e-437c-bc73-ee06e91b08a5', '1a815805-fab3-4146-8eec-46a2dafdd691', '2495c5da-d43c-4671-a67a-36565f5b61b9', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('37f43fb3-77eb-48de-a4b3-47ee190a2477', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('3cd56212-d5df-4da6-bbbd-a3f62fa1d4a8', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '87c1e5f6-462d-4339-98fa-828261ce658b', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('3d4c62d8-e3f0-4da7-a4b9-c135c82b5409', '1a815805-fab3-4146-8eec-46a2dafdd691', '4380e8d0-e3ba-4d46-af80-ec27cd5ab769', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('41e66403-f7b9-4bcb-b681-595550e8bfad', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', 'caad778c-a7b1-444a-8f8d-5dd2d6d3f3f1', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('4a3cc246-e0d4-4db7-b66d-a27e50dc8ece', '1a815805-fab3-4146-8eec-46a2dafdd691', '983adfaf-f6da-4fce-bc74-78db35459dc3', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('5cbffb71-ed7a-448d-953a-5629235a2e78', '1a815805-fab3-4146-8eec-46a2dafdd691', '24187547-f91b-4ed4-89ff-b832a279f652', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('5fc66163-0a51-4d25-8e8f-7259aea4d606', '1a815805-fab3-4146-8eec-46a2dafdd691', '5a8c2021-f93d-4324-8271-ea7685fd95d0', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('631589af-c66b-4863-9da8-18e56e54fc0e', '1a815805-fab3-4146-8eec-46a2dafdd691', '3c0a1755-2e9b-4605-8950-7be38d734699', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('68a2059b-d05c-4fcd-876e-50177ac7de61', '1a815805-fab3-4146-8eec-46a2dafdd691', 'adea7aa2-0270-487e-9508-0fdf36726907', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('6ce3378e-ed20-4647-9dc9-21608b8cbc6e', '1a815805-fab3-4146-8eec-46a2dafdd691', '1920d007-f71f-4e28-801e-574d3c51f60a', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('89e0de42-400c-4619-94f9-95d2c82c2c94', '1a815805-fab3-4146-8eec-46a2dafdd691', 'b4412528-9e1d-4891-9802-29f110c03f28', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('8a0ecd7e-b104-4179-b4a1-9df5faab3c37', '1a815805-fab3-4146-8eec-46a2dafdd691', '92de3f02-f5f1-42ef-a3f2-d5a9133fc3c3', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('a349c791-e71a-44ae-b121-eb3fbf7ee6b5', '1a815805-fab3-4146-8eec-46a2dafdd691', '9c2dd042-6e3b-41e6-9ddd-84d5291e8f49', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('a3ad5a16-8511-48c2-b431-6991eae39444', '1a815805-fab3-4146-8eec-46a2dafdd691', '6686589e-2373-4040-9ba5-65d2b3390f94', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('a4a9b715-6fc2-4867-acd0-c1c994f52c99', '1a815805-fab3-4146-8eec-46a2dafdd691', '6059ad67-2b5b-4c83-970a-8b3e8fb1fb71', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('b077c021-016e-48d5-a192-71c322874b52', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '2495c5da-d43c-4671-a67a-36565f5b61b9', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('b1158f7a-91ed-4777-8d64-64e1508076fc', '1a815805-fab3-4146-8eec-46a2dafdd691', '90f35d56-4037-4ae4-9e80-9315f2b35497', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('b4eee402-0024-44db-ac39-37747e952486', '1a815805-fab3-4146-8eec-46a2dafdd691', '87c1e5f6-462d-4339-98fa-828261ce658b', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('bedcb2e3-4e7e-4ae3-ae9a-e4cba6ffa514', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '5a8c2021-f93d-4324-8271-ea7685fd95d0', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('bef6c95e-5e68-4163-8eb6-e3a1c270c15c', '1a815805-fab3-4146-8eec-46a2dafdd691', '52ee7d40-8ca9-4725-a6ef-e7da8a3657b7', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('c379b546-8452-41dd-ba81-9710642bd85e', '1a815805-fab3-4146-8eec-46a2dafdd691', '40c17bb4-bc2d-4dc0-bedc-441e17f01267', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('c5b7e565-d531-4c28-bf82-9a324adf9831', '1a815805-fab3-4146-8eec-46a2dafdd691', 'c469c6e5-512f-445c-9c5e-3f21c9286aa7', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('cf330595-b7c1-45ee-ac8c-474aa549271e', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '4380e8d0-e3ba-4d46-af80-ec27cd5ab769', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('d231762b-a9f0-4dd3-ac43-fb51d5bc26fa', '1a815805-fab3-4146-8eec-46a2dafdd691', '87b27548-cdcd-480d-bc9c-aa41766ed534', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('d25c163a-0484-4dfe-87ed-4102975bf087', 'a09577b8-50b0-49fd-884f-c88a2601c613', 'adea7aa2-0270-487e-9508-0fdf36726907', '2020-03-10 10:34:26');
INSERT INTO `sys_role_permission` VALUES ('d513fbec-753f-48ea-9748-e380e6abcb51', '1a815805-fab3-4146-8eec-46a2dafdd691', '9e673112-0aff-4bbc-a0f4-ca36fe5aa962', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('ddde58a3-ac18-4918-a6b2-d1ef5d003377', '1a815805-fab3-4146-8eec-46a2dafdd691', '13a6f9f7-edb1-4e00-b95e-4e8cc00e650f', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('df04dab5-29b5-4e19-9423-e68b26799e5f', '1a815805-fab3-4146-8eec-46a2dafdd691', 'cc7d4f67-75bf-48a5-9bd7-a392c0a72408', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('e3ce1165-9033-4ae9-aae0-f58d1751625e', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', 'adea7aa2-0270-487e-9508-0fdf36726907', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('e672734a-4f7a-4e6e-8117-5ed586f0c82c', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', 'b4412528-9e1d-4891-9802-29f110c03f28', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('ebabfb84-6cb9-4661-83a2-ed6edf68764e', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', 'c469c6e5-512f-445c-9c5e-3f21c9286aa7', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('ec42ed25-7b58-4d1c-8997-9c1c460845f3', '1a815805-fab3-4146-8eec-46a2dafdd691', '603bb96d-3ff9-4d99-b6bc-6563d58d3c2c', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('ec6e4dde-8cf7-4581-af3d-b9594ef4dee3', '1a815805-fab3-4146-8eec-46a2dafdd691', '5277110e-f38a-470d-9a28-a389cc5497a6', '2020-03-05 19:37:51');
INSERT INTO `sys_role_permission` VALUES ('f3508e35-3522-4777-9ed5-ad1c8674521a', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '52ee7d40-8ca9-4725-a6ef-e7da8a3657b7', '2020-03-06 21:50:47');
INSERT INTO `sys_role_permission` VALUES ('f79a8fd4-f1dc-4151-a24c-c81f6b4a9d6b', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', 'b7de7250-e244-46f8-a3a3-d33df724f2ba', '2020-03-06 21:50:47');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户名称',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码密文',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `dept_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NULL DEFAULT 1 COMMENT '账户状态1正常2锁定',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别1男2女',
  `deleted` int(11) NULL DEFAULT 1 COMMENT '是否删除1未删除0已删除',
  `create_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_where` int(11) NULL DEFAULT NULL COMMENT '创建来源1web2Android3iOS',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('27739ac4-5e56-4a00-99f0-37006a5a5941', 'dev', '1b1b2968794247579385', '789dfe0b30d20a70e6d705e3dc396deb', '12636546980', '86a242b9-65d4-4ff5-b70c-54fc4add1f1c', NULL, NULL, NULL, 1, NULL, 1, NULL, '9a26f5f1-cbd2-473d-82db-1d6dcf4598f8', NULL, '2020-02-27 22:43:16', '2020-03-10 10:40:30');
INSERT INTO `sys_user` VALUES ('39903d11-9340-4a5b-aee3-9ea1d1dbc8f2', 'fengfanli', '81ac673918da4e328866', '2c7dc03c6dbef02e2b6dbcd79ae0ec82', '17862970812', '22d93869-b95e-4c9a-ba0c-afc79cff9713', NULL, NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, '2020-03-10 10:31:55', NULL);
INSERT INTO `sys_user` VALUES ('7e872c3f-9958-4996-87be-c56c663dde23', 'feng', '8215608fd77d497790bc', '9a66d4268c3f9353be3db7b858c64adf', '7851946', '09e56250-ca9f-424f-96fb-99cd38c63a54', NULL, NULL, NULL, 1, NULL, 0, NULL, '7e872c3f-9958-4996-87be-c56c663dde23', NULL, '2020-02-19 17:51:47', '2020-02-24 17:46:05');
INSERT INTO `sys_user` VALUES ('8a938151-53e6-4182-925a-684f3be840e8', 'feng', '9ef7440dc3bd48e7a6c6', '53d903c19dc3c7f092fe8f88e2a56e93', '17862970812', '22d93869-b95e-4c9a-ba0c-afc79cff9713', NULL, NULL, NULL, 1, NULL, 1, NULL, NULL, NULL, '2020-03-06 21:51:42', NULL);
INSERT INTO `sys_user` VALUES ('9a26f5f1-cbd2-473d-82db-1d6dcf4598f4', 'dev123', '324ce32d86224b00a02b', 'ac7e435db19997a46e3b390e69cb148b', '13666666666', '09e56250-ca9f-424f-96fb-99cd38c63a54', NULL, NULL, 'yingxue@163.com', 1, 1, 0, NULL, '7e872c3f-9958-4996-87be-c56c663dde23', 3, '2019-09-22 19:38:05', '2020-02-24 17:45:50');
INSERT INTO `sys_user` VALUES ('9a26f5f1-cbd2-473d-82db-1d6dcf4598f8', 'admin', '324ce32d86224b00a02b', 'ac7e435db19997a46e3b390e69cb148b', '13888888888', '06d45b3d-2134-4b36-a9df-d43d2e35041f', '冯凡利', NULL, 'yingxue@163.com', 1, 1, 1, NULL, '9a26f5f1-cbd2-473d-82db-1d6dcf4598f8', 3, '2019-09-22 19:38:05', '2020-03-10 10:40:58');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('03f30f09-73b8-49af-9c4f-ac2b8ded697a', '8a938151-53e6-4182-925a-684f3be840e8', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '2020-03-06 21:52:51');
INSERT INTO `sys_user_role` VALUES ('0e36bb16-67bc-4294-9958-39905b6590b4', '7e872c3f-9958-4996-87be-c56c663dde23', '1a815805-fab3-4146-8eec-46a2dafdd691', '2020-02-27 22:31:32');
INSERT INTO `sys_user_role` VALUES ('2035fce8-f30c-4314-a15b-99be2fcfa676', '7e872c3f-9958-4996-87be-c56c663dde23', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '2020-02-27 22:31:32');
INSERT INTO `sys_user_role` VALUES ('284cc9a5-9da1-4408-8abc-65a383a35897', '27739ac4-5e56-4a00-99f0-37006a5a5941', 'b0b3a0c3-83d1-4fa8-a118-ffc28e1043cc', '2020-03-05 19:51:13');
INSERT INTO `sys_user_role` VALUES ('5f54ab30-d223-44f4-8527-ec32dd696eae', '39903d11-9340-4a5b-aee3-9ea1d1dbc8f2', 'a09577b8-50b0-49fd-884f-c88a2601c613', '2020-03-10 10:35:05');
INSERT INTO `sys_user_role` VALUES ('96f19b3b-b04b-4284-acd4-d46c978298c9', '9a26f5f1-cbd2-473d-82db-1d6dcf4598f8', '1a815805-fab3-4146-8eec-46a2dafdd691', NULL);

SET FOREIGN_KEY_CHECKS = 1;
