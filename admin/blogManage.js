//Dev.
const preUrl = 'http://localhost:20001/blog/joe/admin';
//Prod.
// const preUrl = 'admin';
const emptyContent = '<p>Hello World</p>';

function iGetInnerText(str) {
    var resultStr = str.replace(/\ +/g, ""); //去掉空格
    resultStr = str.replace(/[ ]/g, "");    //去掉空格
    resultStr = str.replace(/[\r\n]/g, ""); //去掉回车换行
    return resultStr;
}

Vue.use(window.VueQuillEditor)
Vue.component('blog-manage', {
    data: function () {
        return {
            title: '',
            subTitle: '',
            tags: '',
            content: emptyContent,
            editorOption: {
                modules: {
                    toolbar: [
                        ['bold', 'italic', 'underline', 'strike'],
                        ['blockquote', 'code-block'],
                        [{ 'header': 1 }, { 'header': 2 }],
                        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
                        [{ 'script': 'sub' }, { 'script': 'super' }],
                        [{ 'indent': '-1' }, { 'indent': '+1' }],
                        [{ 'size': ['small', false, 'large', 'huge'] }],
                        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                        [{ 'font': [] }],
                        [{ 'color': [] }, { 'background': [] }],
                        [{ 'align': [] }],
                        ['link', 'image']
                    ],
                    syntax: {
                        highlight: text => hljs.highlightAuto(text).value
                    }
                }
            },
            blogData:[],
            oldData: {},
            showEditor: false,
            createBlog: true
        }
    },
    created: function() {
        console.info("Blog Manage Creating...");
        this.loadAllBlogs();
    },
    methods: {
        onEditorChange({ quill, html, text }) {
            this.content = html
            this.partContent = iGetInnerText(text).substring(0, 300);
        },
        createNewBlogBtn() {
            this.showEditor = true;
            this.createBlog = true;
            this.emptyBlog();
        },
        updateBlogBtn(row) {
            this.$confirm('更新选中Blog?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.get(preUrl + '/findBlogById/' + row.id,{}).then(res => {
                    if(res.status == 200) {
                        let result = res.data;
                        this.content = result.content;
                        this.title = result.title;
                        this.subTitle = result.subTitle;
                        this.tags = result.tags;
                        this.oldData = result;
                        this.showEditor = true;
                        this.createBlog = false;
                    } else {
                        this.$alert('获取Blog失败.', '获取失败', {
                            confirmButtonText: '确定'
                        });
                    }
                }).catch(err => {
                    this.$alert('获取Blog失败.', '获取失败', {
                        confirmButtonText: '确定'
                    });
                });
            });
        },
        emptyBlog() {
            this.content = emptyContent;
            this.title = '';
            this.subTitle = '';
            this.tags = '';
            this.oldData = {};
        },
        saveBlog() {
            if(!this.createBlog) {
                this.$alert('Cannot recreate Blog.', '保存失败', {
                    confirmButtonText: '确定'
                });
                return;
            }
            let data = {
                title: this.title,
                subTitle: this.subTitle,
                content: this.content,
                tags: this.tags,
                partContent: this.partContent
            };
            axios.post(preUrl + '/addBlog', data).then(res => {
                if(res.data == 'Success') {
                    this.$message({
                        type: 'success',
                        message: 'Blog已经创建成功!'
                    });
                    this.createBlog = false;
                    this.oldData = data;
                } else {
                    this.$alert('后台保存数据失败', '保存失败', {
                        confirmButtonText: '确定'
                    });
                }
            }).catch(err => {
                this.$alert('数据传输失败', '保存失败', {
                    confirmButtonText: '确定'
                });
            });
        },
        updateBlog(){
            if(this.isContentChanged()) {
                let data = this.oldData;
                data.content = this.content;
                data.partContent = this.partContent;
                data.title = this.title;
                data.subTitle = this.subTitle;
                data.tags = this.tags;
                axios.post(preUrl + '/updateBlog', data).then(res => {
                    if(res.data == 'Success') {
                        this.$message({
                            type: 'success',
                            message: 'Blog已经修改成功!'
                        });
                        this.createBlog = false;
                        this.oldData = data;
                    } else {
                        this.$alert('后台保存数据失败', '保存失败', {
                            confirmButtonText: '确定'
                        });
                    }
                }).catch(err => {
                    this.$alert('数据传输失败', '保存失败', {
                        confirmButtonText: '确定'
                    });
                });
            } else {
                this.$message({
                    type: 'warning',
                    message: 'Blog 没有变化'
                });
            }
        },
        revertChange() {
            this.$confirm('修改将会丢失, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.content = this.oldData.content;

                this.title = this.oldData.title;
    
                this.subTitle = this.oldData.subTitle;
    
                this.tags = this.oldData.tags;
            });
        },
        goBack() {
            if(this.isContentChanged()) {
                this.$confirm('修改将会丢失, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.emptyBlog();
                    this.showEditor = false;
                });
            } else {
                this.emptyBlog();
                this.showEditor = false;
            }
            this.loadAllBlogs();
        },
        isContentChanged() {
            return this.content != this.oldData.content ||
                this.tags != this.oldData.tags || 
                this.title != this.oldData.title ||
                this.subTitle != this.oldData.subTitle;
        },
        deleteRow(row) {
            this.$confirm('此操作将永久删除该Blog, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.post(preUrl + '/deleteBlog/' + row.id, window.Qs.stringify({})).then(res => {
                    if(res.data == 'Success') {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.loadAllBlogs();
                    } else {
                        this.$alert('后台删除数据失败', '删除失败', {
                            confirmButtonText: '确定'
                        });
                    }
                }).catch(err => {
                    this.$alert('删除数据异常', '删除失败', {
                        confirmButtonText: '确定'
                    });
                });
            });
            
        },
        loadAllBlogs() {
            console.info("Load blogs without content.");
            axios.post(preUrl + '/findAllBlogs', window.Qs.stringify({})).then(res => {
                let result = res.data;
                if(typeof result == "undefined" || result == null) {
                    return;
                } else {
                    this.blogData = result;
                }
            }).catch(err => {
                this.$alert('当查询所有Blogs时出现异常', '查找失败', {
                    confirmButtonText: '确定'
                });
            });
        }
    },
    computed: {
    },
    mounted() {
        console.log('mounted')
    },
    template: '#blogManage'
})