import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import vuetify from './plugins/vuetify';

Vue.use(BootstrapVue)

Vue.config.productionTip = false


Vue.use(vuetify)

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')