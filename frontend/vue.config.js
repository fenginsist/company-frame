// 该文件未生效
module.exports = {
    devServer: {
        port: 8099, // 更改为3000端口
        proxy: {
        '/api': {
            target: 'http://localhost:8080', // 后端服务实际地址
            changeOrigin: true, // 是否改变域名
            pathRewrite: {
            '^/api': '' // 路径重写
            }
        }
        }
    }
};
  