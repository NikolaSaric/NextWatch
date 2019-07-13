import Vue from 'vue'
import App from './App.vue'
import router from './router.js';
import 'vuetify/dist/vuetify.min.css'
import Vuetify from 'vuetify'
import axios from 'axios'
import VueAxios from 'vue-axios'


Vue.config.productionTip = false

Vue.use(VueAxios, axios)
Vue.use(Vuetify)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')