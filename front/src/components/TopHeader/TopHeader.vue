<template>
    <header id="topHeader">
        <el-container  style="color: white">
            <el-header>
                <el-row>
                    <el-col :span="4" style="height: 60px"><p id="userName">{{ topHeaderConfigs.userName }}</p></el-col>
                    <el-col :span="4" :offset="16">
                        <!-- <el-col :span="8" style="height: 60px; padding-top: 10px;"><p>主页</p></el-col>
                        <el-col :span="8" style="height: 60px; padding-top: 10px;"><p>图片</p></el-col>
                        <el-col :span="8" style="height: 60px; padding-top: 10px;"><p>关于我</p></el-col> -->
                    </el-col>
                </el-row>
            </el-header>
            <el-main style="height: 250px">
                <div>
                    <p id="topTitle">{{ topHeaderConfigs.topTitle }}</p>
                </div>
                <div id="topSubTitle">
                    <p>{{ topHeaderConfigs.topSubTitle }}</p>
                </div>
            </el-main>
        </el-container>
    </header>
</template>

<script>


export default {
    name : 'TopHeader',
    props: {
        topHeaderConfigs: {}
    },
    data () {
        return {
        }
    },
    created(){
        console.info('Create TopHeader');
    },
    methods: {
    }
}
</script>

<style scoped>
    #topHeader {
        background: no-repeat center center;
        background-color: #333;
    }
    #userName {
        font-size: 20px;
        text-align: left;
        font-weight: 900;
    }
    #topTitle {
        font-size: 60px;
        margin-bottom: 10px;
    }
</style>