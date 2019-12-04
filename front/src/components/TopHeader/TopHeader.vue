<template>
    <header id="topHeader">
        <!-- <img style="width: 100%" src="../../assets/MainPage.jpg"> -->
    </header>
</template>

<script>
import api from "../../api/api"

export default {
    name : 'TopHeader',
    created(){
        console.info('Create TopHeader');
        api.helloWorld();
    }
}
</script>

<style scoped>
    #topHeader {
        background: no-repeat center center;
        background-color: #333;
        height: 300px;
    }
</style>