<template>
  <div class="app-container">
    <h2>
    <a name="%E8%BF%8E%E5%AD%A6%E5%90%8E%E5%8F%B0%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F"
       class="md-header-anchor">
    </a><span>乒乓球比赛管理系统</span>
</h2>
<h3>
    <a name="1.-%E7%AE%80%E4%BB%8B" class="md-header-anchor">
    </a><span>1. 简介</span>
</h3>
<p><span>这是一套基于spring boot 2.16、shiro、jwt、redis、swagger2、mybatis 、vue2、vuex、element-ui、axios 的后台智慧社区管理系统， 权限控制的方式为 RBAC(Role-Based Access Control )。代码通熟易懂 、JWT（无状态token）过期自动刷新，数据全程 ajax 获取，封装 ajax 工具类、菜单无线层级展示。</span>
</p>
<h3><span>2. 系统功能</span></h3>
<ol>
    <li><span>用户管理：用户是系统操作者，该功能主要完成系统用户配置。</span></li>
    <li><span>比赛管理：配置比赛的信息（兵乓球、羽毛球等），树结构展现。</span></li>
    <li><span>菜单管理：配置系统菜单，操作权限，按钮权限标识等。</span></li>
    <li><span>角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。</span></li>
    <li><span>接口管理：根据业务代码自动生成相关的 api 接口文档。</span></li>
    <li><span>SQL 监控：对系统使用的 sql 进行监控，可快速查询运行效率。</span></li>
    <li><span>日志管理：对用户的操作进行记录。</span></li>
    <li><span>地图管理：查看当前小区所在地址，并可以查看其他地区。</span></li>

</ol>
<h3><a name="3.-%E5%85%B7%E6%9C%89%E5%A6%82%E4%B8%8B%E7%89%B9%E7%82%B9"
       class="md-header-anchor"></a><span>3. 具有如下特点</span></h3>
<ul>
    <li><span>灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求</span></li>
    <li><span>当角色或者菜单权限发生变化的时候能够自动刷新用户权限无需退出登录</span></li>
    <li><span>完善的比赛管理。</span></li>
    <li><span>支持分布式部署，jwt 无状态身份认证。</span></li>
    <li><span>友好的代码结构及注释，便于阅读及二次开发</span></li>
    <li><span>页面交互使用thymeleaf+layui ，极大的提高了开发效率。</span></li>
    <li><span>菜单支持无线层级展示、解决 layui.tree 树形组件数据回显错乱。</span></li>
    <li><span>引入swagger文档支持，方便编写API接口文档。</span></li>

    <li><p><span>企业实战项目脚手架搭建(即适合前后端分离项目、又适合前后端不分离项目)。</span></p>
        <ul>
            <li><span>全局异常处理</span></li>
            <li><span>数据格式封装</span></li>
            <li><span>swagger讲解</span></li>
            <li><span>mockMvc单元测试</span></li>
            <li><span>lombok 使用</span></li>
            <li><span>jwt 详解(access_token、refresh_token实战场景)</span></li>
            <li><span>整合 thymeleaf</span></li>
            <li><span>引入 layui前段框架</span></li>

        </ul>
    </li>
    <li><p><span>实战阶段</span></p>
        <p><span>基于spring boot 2.16、shiro、jwt、redis、swagger2、mybatis 、thymeleaf、layui 后台管理系统， 权限控制的方式为 RBAC。代码通熟易懂 、JWT（无状态token）过期自动刷新，数据全程 ajax 获取，封装 ajax 工具类、菜单无线层级展示，解决 layui.tree 树形组件，回显问题。</span>
        </p>
    </li>
    <li><p><span>前后端分离实战</span></p>
        <p><span>(前后端分离时所需接口实现)实现基于spring boot 2.16、shiro、jwt、redis、swagger2、mybatis 后台管理系统， 权限控制的方式为 RBAC。、JWT（无状态token）过期自动刷新</span>
        </p>
    </li>
</ul>
  </div>
</template>

<script>
  import {str2Date} from '@/utils/date';
  import img_home_order from '@/assets/images/home_order.png';
  import img_home_today_amount from '@/assets/images/home_today_amount.png';
  import img_home_yesterday_amount from '@/assets/images/home_yesterday_amount.png';
  const DATA_FROM_BACKEND = {
    columns: ['date', 'orderCount','orderAmount'],
    rows: [
      {date: '2018-11-01', orderCount: 10, orderAmount: 1093},
      {date: '2018-11-02', orderCount: 20, orderAmount: 2230},
      {date: '2018-11-03', orderCount: 33, orderAmount: 3623},
      {date: '2018-11-04', orderCount: 50, orderAmount: 6423},
      {date: '2018-11-05', orderCount: 80, orderAmount: 8492},
      {date: '2018-11-06', orderCount: 60, orderAmount: 6293},
      {date: '2018-11-07', orderCount: 20, orderAmount: 2293},
      {date: '2018-11-08', orderCount: 60, orderAmount: 6293},
      {date: '2018-11-09', orderCount: 50, orderAmount: 5293},
      {date: '2018-11-10', orderCount: 30, orderAmount: 3293},
      {date: '2018-11-11', orderCount: 20, orderAmount: 2293},
      {date: '2018-11-12', orderCount: 80, orderAmount: 8293},
      {date: '2018-11-13', orderCount: 100, orderAmount: 10293},
      {date: '2018-11-14', orderCount: 10, orderAmount: 1293},
      {date: '2018-11-15', orderCount: 40, orderAmount: 4293}
    ]
  };
  export default {
    name: 'home',
    data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              let start = new Date(2018,10,1);
              const end = new Date(start.getTime() + 1000 * 60 * 60 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一月',
            onClick(picker) {
              let start = new Date(2018,10,1);
              const end = new Date(start.getTime() + 1000 * 60 * 60 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        orderCountDate: '',
        chartSettings: {
          xAxisType: 'time',
          area:true,
          axisSite: { right: ['orderAmount']},
        labelMap: {'orderCount': '订单数量', 'orderAmount': '订单金额'}},
        chartData: {
          columns: [],
          rows: []
        },
        loading: false,
        dataEmpty: false,
        img_home_order,
        img_home_today_amount,
        img_home_yesterday_amount
      }
    },
    created(){
      this.initOrderCountDate();
      this.getData();
    },
    methods:{
      handleDateChange(){
        this.getData();
      },
      initOrderCountDate(){
        let start = new Date(2018,10,1);
        const end = new Date(start.getTime() + 1000 * 60 * 60 * 24 * 7);
        this.orderCountDate=[start,end];
      },
      getData(){
        setTimeout(() => {
          this.chartData = {
            columns: ['date', 'orderCount','orderAmount'],
            rows: []
          };
          for(let i=0;i<DATA_FROM_BACKEND.rows.length;i++){
            let item=DATA_FROM_BACKEND.rows[i];
            let currDate=str2Date(item.date);
            let start=this.orderCountDate[0];
            let end=this.orderCountDate[1];
            if(currDate.getTime()>=start.getTime()&&currDate.getTime()<=end.getTime()){
              this.chartData.rows.push(item);
            }
          }
          this.dataEmpty = false;
          this.loading = false
        }, 1000)
      }
    }
  }
</script>

<style scoped>
  .app-container {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
  }

</style>
