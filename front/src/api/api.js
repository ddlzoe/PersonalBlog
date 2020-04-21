import http from './http'

function getTopHeaderConfig() {
    return http.fetchGet('/mainpage/config/topheader', {});
}

function findAllBlogsWithoutContent() {
    return http.fetchGet('/mainpage/findAllBlogsWithoutContent', {});
}

function findBlogById(blogId) {
    return http.fetchGet('/mainpage/findBlogById/' + blogId, {})
}

export default {
    getTopHeaderConfig, findAllBlogsWithoutContent, findBlogById
}