<template>
  <div class="home">
    <el-card class="box-card" v-for="cardItem in kaleidoscope">
      <div slot="header" class="clearfix">
        <svg v-if="!isFieldEmpty(cardItem, 'titleIcon')" class="icon" aria-hidden="true">
          <use v-bind:xlink:href="`#${cardItem.titleIcon}`"></use>
        </svg>
        <span style="margin-left: 10px">{{ cardItem.title }}</span>
      </div>
      <div class="popovers">
        <el-popover
            placement="bottom"
            width="auto"
            trigger="click"
            class="my-popover"
            v-for="serviceItem in cardItem.serviceItems">
          <el-tabs :value=serviceItem.environments[0].tag>
            <el-tab-pane :label=environment.tag :name=environment.tag v-for="environment in serviceItem.environments">
              
              <div class="environment-context">
                <div>
                  <a v-if="!isFieldEmpty(environment, 'url')" :href=environment.url target="_blank" class="link">[进入]</a>
                </div>
                <p v-if="!isFieldEmpty(environment, 'account')">account: {{ environment.account }}</p>
                <p v-if="!isFieldEmpty(environment, 'password')">password: {{ environment.password }}</p>
                <span v-if="!isFieldEmpty(environment, 'url')" class="context-copy"
                   v-clipboard:copy="environment.url"
                   v-clipboard:success="onCopy"
                   v-clipboard:error="onError">复制地址</span>
                <span v-if="!isFieldEmpty(environment, 'account')" class="context-copy" 
                   v-clipboard:copy="environment.account"
                   v-clipboard:success="onCopy"
                   v-clipboard:error="onError">复制</span>
                <span v-if="!isFieldEmpty(environment, 'password')" class="context-copy"
                   v-clipboard:copy="environment.password"
                   v-clipboard:success="onCopy"
                   v-clipboard:error="onError">复制</span>
              </div>
            </el-tab-pane>
          </el-tabs>
          <el-button slot="reference">
            <div class="button-contain">
              <svg v-if="!isFieldEmpty(serviceItem, 'nameIcon')" class="icon" aria-hidden="true">
                <use v-bind:xlink:href="`#${serviceItem.nameIcon}`"></use>
              </svg>
              <span>{{ serviceItem.name }}</span>
            </div>
          </el-button>
        </el-popover>
      </div>

    </el-card>
  </div>
</template>

<script>
// import kaleidoscope from "@/data/kaleidoscope.json";
import {getKaleidoList} from "@/api/biz/kaleido";

export default {
  name: 'HomeView',
  components: {
  },
  data() {
    return {
      kaleidoscope: []
    }
  },
  created() {
    // let kaleidoscope = require("@/data/kaleidoscope.json");
    // console.log(kaleidoscope);
    // this.kaleidoscope = kaleidoscope;

    // 通过接口获取数据
    getKaleidoList().then(data => {
      console.log(data);
      this.kaleidoscope = data;
    }).catch(() => {});
  },
  methods: {
    isFieldEmpty(obj, field) {
      if (obj.hasOwnProperty(field)) {
        const value = obj[field];
        return value === null || value === undefined || value === '' || (Array.isArray(value) && value.length === 0);
      }
      return true;
    },
    onError() {
      console.error("复制失败");
    },
    // 复制
    onCopy() {
      this.$message.success("复制成功");
      // console.error("复制成功");
    },
  }
}
</script>

<style lang="less" scoped>
.home {
  padding: 10px;

  .box-card:not(:first-child) {
    margin-top: 15px;
  }
  
  .popovers {
    width: 100%;
    
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-gap: 10px;
    
    text-align: center;
    
    @media (min-width: 768px) {
      grid-template-columns: repeat(4, 1fr);
    }

    @media (min-width: 992px) {
      grid-template-columns: repeat(6, 1fr);
    }
    
    @media (min-width: 1280px) {
      grid-template-columns: repeat(7, 1fr);
    }
  }
  
  .el-button {
    width: auto;
    min-width: 160px;
    height: 60px;
    
    padding: 10px;
    
    .button-contain {
      display: flex;
      align-items: center;
      justify-content: center;
      
      svg {
        width: 25px;
        height: 25px;
      }
      
      span {
        margin-left: 15px;
      }
    }
  }
  
}

.link {
  color: #1e792d;
  text-decoration: underline;
}
.link:hover {
  color: #1e792d;
  font-weight: bold;
}

.environment-context {
  display: grid;
  grid-auto-flow: column;
  grid-column-gap: 50px;
  
  .context-copy {
    color: #1e792d;
    text-align: center;
  }
  .context-copy:hover {
    color: #1e792d;
    font-weight: bold;
  }
}

/* 只有一个 div 子元素时 */
.environment-context:has(> div):not(:has(> p)) {
  grid-template-rows: repeat(1, 1fr);
  //grid-auto-flow: column;
}

/* 只有一个 div 和多个 p 子元素时 */
.environment-context:has(> div):has(> p) {
  grid-template-rows: repeat(3, 1fr);
}
</style>
