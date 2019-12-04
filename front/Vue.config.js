module.exports = {
    outputDir: process.env.NODE_ENV === 'production' ? '../src/main/resources/static' : 'static',
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