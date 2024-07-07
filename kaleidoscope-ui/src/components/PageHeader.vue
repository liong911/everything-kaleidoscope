<template>
  <div class="header">
    <nav>
      <div>
        <router-link to="/" class="link">导航</router-link>
      </div>
      <div>
        <router-link to="/generator" class="link">代码生成</router-link>
      </div>
    </nav>
    <div class="banner">
      <img src="@/assets/header-banner.jpg" alt="">
      <div class="logo">
        <router-link to="/" class="logo-link">
          <img src="@/assets/logo.png" alt="">
        </router-link>
      </div>
      <div class="mask"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PageHeader',
  data() {
    return {
    }
  },
  methods: {
  }
}
</script>

<style lang="less" scoped>
.header {
  display: flex;
  justify-content: center;

  position: relative;
  
  nav {
    position: absolute;
    top: 0;
    z-index: 1000;
    
    display: flex;
    align-items: center;
    
    div:not(:first-child) {
      margin-left: 20px;
    }
    div {
      margin-top: 6px;

      .link {
        color: #f1f2f3
      }
      .link:hover {
        color: #fff;
        font-weight: bold;
      }
    }
  }
  
  .banner {
    height: 155px;
    width: 100%;

    position: relative;
    
    img {
      position: absolute;
      left: 0;
      top: 0;

      width: 100%;
      height: 100%;
      object-fit: cover;
     }

    .logo {
      height: 100%;
      padding: 0 40px;

      /*子元素按底边对齐*/
      display: flex;
      align-items: flex-end;

      .logo-link {
        width: 180px;
        height: 50px;

        position: relative;
        margin-bottom: 20px;

        img {
          object-fit: contain;
        }
        
        // 确保能被点击
        z-index: 1000;
      }
    }

    .mask {
      /*遮罩层*/
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;

      /*渐变色，使banner的文字更清晰*/
      background: linear-gradient(rgba(0,0,0,.4), transparent);
    }
  }
  
}
</style>