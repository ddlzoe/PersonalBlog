<template>
    <div>
        <top-header :topHeaderConfigs="topHeaderConfigs"></top-header>
        <div id="blog">
            <el-container>
                <el-main style="padding: 0px;">
                    <div class="main_blog ql-container ql-snow">
                        <div class="ql-editor" v-html="blog.content"></div>
                    </div>
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
import TopHeader from "@/components/TopHeader/TopHeader";
import api from "../../api/api";

export default {
    name: "article",
    components: {
        TopHeader
    },
    data () {
        return {
            blogId: "",
            topHeaderConfigs: {
                userName: "",
                topTitle: "Top Title",
                topSubTitle: "Top Sub Title"
            },
            blog: {}
        }
    },
    created: function() {
        this.blogId = this.$route.query.id;
        api.findBlogById(this.blogId).then(res => {
            this.blog = res.data;
            this.topHeaderConfigs.topTitle = this.blog.title;
            this.topHeaderConfigs.topSubTitle = this.blog.subTitle;
        });
    }
}
</script>

<style scoped>
#blog {
  width: 1200px;
  margin-top: 10px;
  margin-left: auto;
  margin-right: auto;
}

.main_blog {
    padding: 10px;
    background-color: white;
    text-align: left;
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
</style>