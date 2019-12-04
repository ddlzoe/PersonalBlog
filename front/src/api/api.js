import http from './http'

function helloWorld() {
    http.fetchGet('/helloworld', {}).then((data) => {
        console.info(data);
    }).catch((err) => {
        console.info(err);
    })
}

export default {
    helloWorld
}