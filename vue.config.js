const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/images': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        pathRewrite: { '^/images': '/images' }
      }
    }
  }
})
