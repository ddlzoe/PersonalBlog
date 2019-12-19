<template>
  <div id="app">
    <top-header :topHeaderConfigs="topHeaderConfigs"></top-header>
    <div id="content">
      <router-view/>
    </div>
  </div>
</template>

<script>
import TopHeader from "@/components/TopHeader/TopHeader";
import api from "./api/api";

export default {
  components: {
    TopHeader
  },
  data() {
    return {
      topHeaderConfigs: {
        userName: "User Name",
        topTitle: "Top Title",
        topSubTitle: "Top Sub Title"
      }
    };
  },
  name: "app",
  created: function() {
    this.loadTopHeaderConfig();
  },
  methods: {
    loadTopHeaderConfig: function() {
      api.getTopHeaderConfig().then(res => {
        this.topHeaderConfigs = res.data;
      });
    }
  }
};
</script>

<style>
body {
  margin: 0px;
  padding: 0px;
  background-color:#e9ecef;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
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
