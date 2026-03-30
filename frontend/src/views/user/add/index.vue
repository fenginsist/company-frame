<template>
    <el-card class="form-container" shadow="never">
        <div style="margin-top: 50px"> 
        <el-form :model="userData" :rules="rules" ref="userInfoFormRef" label-width="120px" class="form-inner-container" size="small">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="userData.username"></el-input>
            </el-form-item>
            <!-- <el-form-item label="头像">
                <el-input v-model="userData.avatar"></el-input>
            </el-form-item> -->
            <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="userData.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="userData.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="项目" prop="brandId">
                <el-select
                    v-model="userData.deptId"
                    @change="handleBrandChange"
                    placeholder="请选择项目">
                    <el-option
                        v-for="item in deptOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="手机">
                <el-input v-model="userData.phone"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="userData.realName"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input v-model="userData.nickName"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="userData.email"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-select v-model="userData.sex" placeholder="请选择">
                    <el-option
                    v-for="item in sex_options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item style="text-align: center">
                <el-button type="primary" size="medium" @click="saveUser()">保存</el-button>
            </el-form-item>
        </el-form>
        </div>
    </el-card>
</template>
  
<script>
import {addUser} from '@/api/user'

export default {
    name: "addUser",
    data() {
        var validatePass = (rule, value, callback) => {
            console.log('validatePass:' + value)
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.userData.password !== '') {
                    this.$refs.userInfoFormRef.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            console.log('validatePass2:' + value)
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.userData.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            userData:{
                "username": "",
                "avatar": "",
                "password": "",
                "checkPass": '',
                "phone": "",
                "realName": "",
                "nickName": "",
                "email": "",
                "sex": "",
                "deptId": "",
                "createWhere": 1,
                "userType": 2
            },
            sex_options: [
                {
                    value: '1',
                    label: '男'
                }, {
                    value: '2',
                    label: '女'
                }
            ],
            hasEditCreated:false,
            //选中商品分类的值
            selectProductCateValue: [],
            productCateOptions: [],
            brandOptions: [],
            rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                name: [
                    {required: true, message: '请输入商品名称', trigger: 'blur'},
                    {min: 2, max: 140, message: '长度在 2 到 140 个字符', trigger: 'blur'}
                ],
                username: [{required: true, message: '该项为必填项', trigger: 'blur'}]
            },
            deptOptions: [
                {
                    value: 'YXD0000001',
                    label: '乒乓球'
                }, {
                    value: 'YXD0000002',
                    label: '羽毛球'
                }, {
                    value: 'YXD0000003',
                    label: '篮球'
                }, {
                    value: 'YXD0000004',
                    label: '足球'
                }, {
                    value: 'YXD0000005',
                    label: '排球'
                }, {
                    value: 'YXD0000006',
                    label: '网球'
                }
            ]
        };
    },
    created() {
    },
    computed:{

    },
    watch: {
    },
    methods: {
        handleBrandChange(){

        },
        saveUser(){
            let userParam = this.userData
            console.log(JSON.stringify(userParam))

            // 检查表单是否可以通过

            // 给提示，是否确认添加

            this.$confirm('请检查表单信息，是否要确认保存', '确认信息', {
                distinguishCancelAndClose: true,
                confirmButtonText: '保存',
                cancelButtonText: '取消'
            }).then(() => {
                addUser(userParam).then(response=>{
                    console.log('add User', response)
                    if (response.code === 0) {
                        this.$message({
                            message: '保存成功！',
                            type: 'success'
                        });
                        console.log('用户添加成功')
                        // 跳转到用户列表
                    }
                })
            }).catch(action => {
                this.$message({
                type: 'info',
                message: action === 'cancel'
                    ? '取消保存'
                    : '停留在当前页面'
                })
            });

            
        }
    }
}
</script>
  
<style scoped>
</style>
  