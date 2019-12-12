Vue.component('blog-manage', {
    data: function () {
        return {
            count: 0
        }
    },
    created: function() {
        console.info("This is created function.")
    },
    template: '#blogManage'
})