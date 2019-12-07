<template>
  <div id="app">
    <top-header></top-header>
    <div id="content">
      <router-view/>
    </div>
  </div>
</template>

<script>
import TopHeader from "@/components/TopHeader/TopHeader"

export default {
  components: {
    TopHeader
  },
  name: 'app'
}
</script>

<style>
body {
  margin: 0px;
  padding: 0px;
  background-color: #F5F7FA;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  /* margin-left: auto;
  margin-right: auto; */
}
#content {
  width: 1200px;
  margin-top: 10px; 
  margin-left: auto;
  margin-right: auto;
}
</style>
