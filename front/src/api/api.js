import http from './http'

function getTopHeaderConfig() {
    return http.fetchGet('/mainpage/config/topheader', {});
}

export default {
    getTopHeaderConfig
}