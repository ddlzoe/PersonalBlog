module.exports = {
    outputDir: process.env.NODE_ENV === 'production' ? 'static' : '../src/main/resources/static',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '': {
                target: 'http://localhost:20001',
                changeOrigin: true
            }
        }
    }
}