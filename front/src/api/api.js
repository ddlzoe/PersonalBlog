import http from './http'

function getTopHeaderConfig() {
    return http.fetchGet('/mainpage/config/topheader', {});
}

function findAllBlogsWithoutContent() {
    return http.fetchGet('/mainpage/findAllBlogsWithoutContent', {});
}

export default {
    getTopHeaderConfig, findAllBlogsWithoutContent
}