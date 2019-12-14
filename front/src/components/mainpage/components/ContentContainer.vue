<template>
    <div class="main_cc">
        <div class="main_title">{{ contentTitle }}</div>
        <div class="main_content" v-on:click="clickContent">{{ content }}</div>
        <div class="main_bar">
            <el-row :gutter="20" style="width: 500px;">
                <el-col :span="8">
                    <i class="el-icon-date"></i>
                    <span style="padding-left:10px;">{{ createdDate }}</span>
                </el-col>
                <el-col :span="8">
                    <i class="el-icon-edit-outline"></i>
                    <span style="padding-left:10px;">{{ commentTimes }}评论</span>
                </el-col>
                <el-col :span="8">
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
        data () {
            return {
                contentTitle : 'VUE初次使用',
                content : 'dart代码转换成app.dill形式的kernel文件。frontend_server.dart.snapshot入口位于Flutter引擎中的 flutter/frontend_server/bin/starter.dart。lutter build aot命令将dart源码编译成AOT产物，其主要工作为前端编译器frontend_server和机器码生成，本文先介绍前端编译器frontend_server的工作原理。可',
                createdDate : '2019-12-08',
                commentTimes : 0,
                viewTimes : 0
            }
        },
        methods:{
            clickContent: function() {
                console.info("Client Context");
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