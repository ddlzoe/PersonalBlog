<template>
    <div>
        <top-header :topHeaderConfigs="topHeaderConfigs"></top-header>
        <div id="content">
            <el-container id="mainPage">
                <el-main style="padding: 0px;">
                    <content-container v-for="blog in blogsArray" :key="blog.id" :shortBlog="blog" ></content-container>
                </el-main>
                <el-aside width="25%" style="background-color: white; margin-left: 10px; height: 600px;">
                    <div style="height: 250px">
                        <div class="asideTitle">推荐文章</div>
                        <p>正在开发</p>
                        <!-- <div v-for="wen in 7" :key="wen">
                            <div class="asideArticleTitle" > 文章标题啦啦啦 文章标题啦啦啦 文章标题啦啦啦</div>
                            <div style="height: 2px;"/>
                        </div> -->
                    </div>
                    <div style="height: 10px; background-color: #e9ecef;"/>
                    <div style="height: 340px">
                        <div class="asideTitle">标签</div>
                        <p>正在开发</p>
                        <!-- <div class="asideTagsBox">
                            <el-tag class="asideTag" size="mini">超标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小啦标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小标签</el-tag>
                            <el-tag class="asideTag" size="mini">超标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小啦标签</el-tag>
                            <el-tag class="asideTag" size="mini">超小标签</el-tag>
                        </div> -->
                    </div>
                </el-aside>
            </el-container>
        </div>
    </div>
</template>

<script>
import ContentContainer from './components/ContentContainer';
import TopHeader from "@/components/TopHeader/TopHeader";
import api from "../../api/api";

export default {
    name: "mainpage",
    components: {
        ContentContainer,
        TopHeader
    },
    data () {
        return {
            blogsArray: [],
            topHeaderConfigs: {
                userName: "User Name",
                topTitle: "Top Title",
                topSubTitle: "Top Sub Title"
            }
        }
    },
    created: function() {
        this.loadBlogs();
        this.loadTopHeaderConfig();
    },
    methods: {
        loadBlogs(){
            api.findAllBlogsWithoutContent().then(res => {
                this.blogsArray = res.data;
            });
        },
        loadTopHeaderConfig: function() {
            api.getTopHeaderConfig().then(res => {
                this.topHeaderConfigs = res.data;
            });
        }
    }
};
</script>

<style scoped>
#mainPage {
    height: 100%;
}
.asideTitle {
    padding: 10px 10px 20px 10px;
    font-size: 15px;
    font-weight: bold;
    text-align: left;
}
.asideArticleTitle {
    padding: 2px 10px;
    text-align: left;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
    cursor: pointer;
    color: darkslategray;
}
.asideArticleTitle:hover {
    color: blue;
    
}
.asideTagsBox {
    text-align:left; 
    padding-left:7px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 9;
    overflow: hidden;
}
.asideTag {
    margin: 5px 3px;
}

#content {
  width: 1200px;
  margin-top: 10px;
  margin-left: auto;
  margin-right: auto;
}
</style>