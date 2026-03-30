<template>
   
  <div class="app-container">
    <!-- 搜索 -->
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float: right"
          @click="handleSearchList()"
          type="primary"
          size="small"
        >
          查询结果
        </el-button>
        <el-button
          style="float: right; margin-right: 15px"
          @click="handleResetSearch()"
          size="small"
        >
          重置
        </el-button>
      </div>
      <div style="margin-top: 15px">
        <el-form
          :inline="true"
          :model="listQuery"
          size="small"
          label-width="140px"
        >
          <el-form-item label="比赛名称">
            <el-input
              style="width: 203px"
              v-model="listQuery.testname"
              placeholder="比赛名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="比赛负责人：">
            <el-input
              style="width: 203px"
              v-model="listQuery.username"
              placeholder="负责人"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button class="btn-add" @click="handleAddTest()" size="mini">
        添加
      </el-button>
    </el-card>

    <!-- 数据展示 -->
    <div class="table-container">
      <el-table
        ref="productTable"
        :data="list"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="listLoading"
        border
      >
        <el-table-column
          type="selection"
          width="60"
          align="center"
        ></el-table-column>
        <el-table-column label="编号" width="50" align="center">
          <template slot-scope="scope">{{
            (listQuery.pageNum - 1) * listQuery.pageSize + scope.$index + 1
          }}</template>
        </el-table-column>
        <el-table-column label="ID" width="100" align="center">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>
        <el-table-column label="比赛名称" width="100" align="center">
          <template slot-scope="scope">{{ scope.row.testname }}</template>
        </el-table-column>
        <el-table-column label="负责人" width="110" align="center">
          <template slot-scope="scope"
            ><p>{{ scope.row.username }}</p></template
          >
        </el-table-column>
        <el-table-column label="手机号" width="110" align="center">
          <template slot-scope="scope"
            ><p>{{ scope.row.phone }}</p></template
          >
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template slot-scope="scope">{{
            scope.row.status == 1 ? "发布" : "未发布"
          }}</template>
        </el-table-column>
        <el-table-column label="比赛开始时间" width="100" align="center">
          <template slot-scope="scope">{{ scope.row.startTime }}</template>
        </el-table-column>
        <el-table-column label="比赛结束时间" width="100" align="center">
          <template slot-scope="scope">{{ scope.row.endTime }}</template>
        </el-table-column>
        <el-table-column label="比赛发布时间" width="100" align="center">
          <template slot-scope="scope">{{ scope.row.publishTime }}</template>
        </el-table-column>
        <el-table-column label="创建时间" width="100" align="center">
          <template slot-scope="scope"
            ><p>{{ scope.row.createTime }}</p></template
          >
        </el-table-column>
        <el-table-column label="操作" width="160" align="center">
          <template slot-scope="scope">
            <p>
              <el-button
                size="mini"
                @click="handlePublishProduct(scope.$index, scope.row)"
                >发布
              </el-button>
              <el-button
                size="mini"
                @click="handleUpdateProduct(scope.$index, scope.row)"
                >舍弃
              </el-button>
            </p>
            <p>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
                >删除
              </el-button>
            </p>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes, prev, pager, next, jumper"
        :page-size="listQuery.pageSize"
        :page-sizes="[5, 10, 15]"
        :current-page.sync="listQuery.pageNum"
        :total="total"
      >
      </el-pagination>
    </div>

    <el-dialog title="添加赛事" :visible.sync="dialogVisible" width="600px">
      <!-- :rules="addTestRules" -->
      <el-form
        :model="testData"
        ref="userInfoFormRef"
        label-width="120px"
        class="form-inner-container"
        size="small"
      >
        <el-form-item label="比赛名称">
          <el-input v-model="testData.testname"></el-input>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="testData.username"></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="testData.phone"></el-input>
        </el-form-item>
        <el-form-item label="比赛开始时间">
          <el-date-picker
            v-model="testData.startTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="12:00:00"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛结束时间">
          <el-date-picker
            v-model="testData.endTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="12:00:00"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛发布时间">
          <el-date-picker
            v-model="testData.publishTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="12:00:00"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="testData.createTime"
            type="datetime"
            placeholder="选择日期时间"
            default-time="12:00:00"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item style="text-align: center">
          <el-button type="primary" size="medium" @click="saveTest()"
            >保存</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
  <script>
import { formatDateStr } from "@/utils/date";

const defaultListQuery = {
  pageNum: 1,
  pageSize: 5,
  username: null,
  phone: null,
};
export default {
  name: "testList",
  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      list: null,
      total: 0,
      listLoading: true,
      selectProductCateValue: null,
      multipleSelection: [],
      dataMock: [
        {
          id: 1,
          testname: "山东省平平球大赛",
          username: "田立彬",
          phone: "17852086724",
          status: 1,
          startTime: "20250501 23:23:00",
          endTime: "20250509 23:23:00",
          publishTime: "20250401 23:23:00",
          createTime: "20250401 23:23:00",
        },
        {
          id: 2,
          testname: "社区比赛",
          username: "小田",
          phone: "17852086724",
          status: 2,
          startTime: "20250501 23:23:00",
          endTime: "20250509 23:23:00",
          publishTime: "20250401 23:23:00",
          createTime: "20250401 23:23:00",
        },
        {
          id: 3,
          testname: "北京大学乒乓球比赛",
          username: "小田",
          phone: "17852086724",
          status: 1,
          startTime: "20250501 23:23:00",
          endTime: "20250509 23:23:00",
          publishTime: "20250401 23:23:00",
          createTime: "20250401 23:23:00",
        },
      ],
      dialogVisible: false,
      testData: {
        testname: "",
        username: "",
        phone: "",
        startTime: null,
        endTime: null,
        publishTime: null,
        createTime: null,
      },
      addTestRules: [],
    };
  },
  created() {
    this.getList();
  },
  watch: {
    selectProductCateValue: function (newValue) {
      if (newValue != null && newValue.length == 2) {
        this.listQuery.productCategoryId = newValue[1];
      } else {
        this.listQuery.productCategoryId = null;
      }
    },
  },
  filters: {
    verifyStatusFilter(value) {
      if (value === 1) {
        return "审核通过";
      } else {
        return "未审核";
      }
    },
  },
  methods: {
    formatDateStr,
    getList() {
      this.listLoading = true;
      console.log("getList() this.listQuery:", JSON.stringify(this.listQuery));

      setTimeout(() => {
        // 这里的代码会在2秒后执行
        console.log("2秒后执行的代码");
        this.listLoading = false;
        this.list = this.dataMock;
        this.total = this.dataMock.length;
      }, 1000);
    },
    handleSearchList() {
      this.listQuery.pageNum = 1;

      this.dataMock = this.dataMock.filter((item) => {
        // 如果两个搜索关键字都为空，返回所有数据
        if (!this.listQuery.testname && !this.listQuery.username) {
          return true;
        }
        // 如果只有 testname 为空，根据 username 过滤
        if (!this.listQuery.testname) {
          return item.username.includes(this.listQuery.username);
        }
        // 如果只有 username 为空，根据 testname 过滤
        if (!this.listQuery.username) {
          return item.testname.includes(this.listQuery.testname);
        }
        // 如果两个搜索关键字都不为空，同时根据两个条件过滤
        return (
          item.testname.includes(this.listQuery.testname) &&
          item.username.includes(this.listQuery.username)
        );
      });

      this.getList();
    },
    handleAddTest() {
      //   this.$router.push({ path: "/test/addTest" });
      this.dialogVisible = true;
    },
    handleSizeChange(val) {
      this.listQuery.pageNum = 1;
      this.listQuery.pageSize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val;
      this.getList();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    handleResetSearch() {
      this.selectProductCateValue = [];
      this.listQuery = Object.assign({}, defaultListQuery);
    },
    handleDelete(index, row) {
      console.log("index:", JSON.stringify(index));
      console.log("row:", JSON.stringify(row));
      this.$confirm("是否要进行删除操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.dataMock = this.dataMock.filter((item) => item.id !== row.id);
        this.getList();
      });
    },
    handleUpdateProduct(index, row) {
      console.log("handleUpdateProduct", row);
      this.$confirm("是否要取消发布操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.dataMock = this.dataMock.map((item) => {
          if (item.id === row.id) {
            return { ...item, status: 2 };
          }
          return item;
        });
        this.getList();
      });
    },
    handlePublishProduct(index, row) {
      console.log("handlePublishProduct", row);
      this.$confirm("是否要进行发布操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.dataMock = this.dataMock.map((item) => {
          if (item.id === row.id) {
            return { ...item, status: 1 };
          }
          return item;
        });
        this.getList();
      });
    },
    saveTest() {
      console.log("saveTest", JSON.stringify(this.testData));

      this.dataMock.push({
        id: this.dataMock[this.dataMock.length - 1].id + 1,
        testname: this.testData.testname,
        username: this.testData.username,
        phone: this.testData.phone,
        status: 1,
        startTime: this.testData.startTime,
        endTime: this.testData.endTime,
        publishTime: this.testData.publishTime,
        createTime: this.testData.createTime,
      });
      this.dialogVisible = false;
      this.getList();
    },
    convertUTCToLocal(utcString) {
      // 将 UTC 字符串转换为 Date 对象
      const date = new Date(utcString);

      // 转换为本地时间
      const localDate = new Date(
        date.getTime() + date.getTimezoneOffset() * 60000
      );

      // 设置你想要的特定时间
      localDate.setHours(23, 23, 0, 0);

      // 将日期更改为 2025-05-01
      localDate.setFullYear(2025, 4, 1); // 月份是从 0 开始的，所以 4 表示 5 月

      // 格式化日期和时间为 "yyyyMMdd HH:mm:ss" 的格式
      const formattedDate =
        localDate.getFullYear() +
        ("0" + (localDate.getMonth() + 1)).slice(-2) +
        ("0" + localDate.getDate()).slice(-2) +
        " " +
        ("0" + localDate.getHours()).slice(-2) +
        ":" +
        ("0" + localDate.getMinutes()).slice(-2) +
        ":" +
        ("0" + localDate.getSeconds()).slice(-2);

      return formattedDate;
    },
  },
};
</script>
  <style></style>
  
  
  