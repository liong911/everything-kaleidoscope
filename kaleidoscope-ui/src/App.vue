<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

a {
  /*链接去掉下划线*/
  text-decoration: none;
  color: #000;
  /*鼠标出现小手手*/
  cursor: pointer;
}

/*iconfont固定配置*/
.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
