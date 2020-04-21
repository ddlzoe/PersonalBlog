<template>
    <div class="main_cc">
        <div class="main_title">{{ shortBlog.title }}</div>
        <div class="main_content" v-on:click="clickContent">{{ shortBlog.partContent }}</div>
        <div class="main_bar">
            <el-row :gutter="20" style="width: 500px;">
                <el-col :span="10">
                    <i class="el-icon-date"></i>
                    <span style="padding-left:10px;">{{ shortBlog.createdTime }}</span>
                </el-col>
                <el-col :span="7">
                    <i class="el-icon-edit-outline"></i>
                    <span style="padding-left:10px;">{{ commentTimes }}评论</span>
                </el-col>
                <el-col :span="7">
                    <i class="el-icon-reading"></i>
                    <span style="padding-left:10px;">{{ viewTimes }}浏览</span>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'ContentContainer',
        props: {
            shortBlog: {}
        },
        data () {
            return {
                commentTimes : 0,
                viewTimes : 0
            }
        },
        methods:{
            clickContent: function() {
                let routeData = this.$router.resolve({
                    name: 'Article',
                    query: {
                        id: this.shortBlog.id
                    }
                });
                window.open(routeData.href, '_blank');
            }
        }
    }
</script>

<style scoped>
    .main_cc {
        height: 200px;
        background-color: white;
        text-align: left;
        margin-bottom: 3px;
    }

    .main_title {
        padding: 20px 25px;
        font-weight: bold;
        font-size: 25px;
    }

    .main_content {
        padding-left: 25px;
        padding-right: 25px;
        height: 84px;
        font-style: italic;
        color:grey;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 4;
        overflow: hidden;
        cursor: pointer;
    }
    .main_bar {
        padding: 13px 25px;
        font-size: 15px;
    }
</style>