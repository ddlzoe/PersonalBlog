import Vue from 'vue'
import Router from 'vue-router'
const mainpage = resolve => require(["@/components/mainpage/mainpage"], resolve)
const article = resolve => require(["@/components/article/article"], resolve)

Vue.use(Router)
const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/mainpage',
            component: mainpage
        },
        {
            path: '/article',
            name: 'Article',
            component: article
        },
        {
            path: '/*',
            component: mainpage
        }
    ]
})

export default router