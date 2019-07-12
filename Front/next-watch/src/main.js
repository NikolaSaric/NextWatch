import Vue from 'vue'
import App from './App.vue'
import router from './router.js';
import 'vuetify/dist/vuetify.min.css'
import Vuetify from 'vuetify'
Vue.config.productionTip = false

Vue.use(Vuetify)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')