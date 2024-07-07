import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/icon/iconfont'
import VueClipboard from 'vue-clipboard2';
// md编辑器
import 'mavon-editor/dist/css/index.css'
import mavonEditor from 'mavon-editor'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueClipboard);
Vue.use(mavonEditor)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
