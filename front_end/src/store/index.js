import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import hotel from './modules/hotel'
import comment from './modules/comment'
import hotelManager from './modules/hotelManager'
import admin from './modules/admin'
import marketingStaff from './modules/marketingStaff'
import getters from './getters'
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    hotel,
    user,
    hotelManager,
    admin,
    marketingStaff,
    comment,
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  getters
})
