<template>
    <div>
        <div class="search">
            <a-button icon="search" size="large" @click="search">
                精确搜索
            </a-button>
            <a-dropdown>
                <a-menu slot="overlay" @click="handleMenuClick">
                    <a-menu-item key="1"><a-icon type="crown"></a-icon>
                        星 级
                    </a-menu-item>
                    <a-menu-item key="2"><a-icon type="like"></a-icon>
                        评 分
                    </a-menu-item>
                </a-menu>
                <a-button size="large" style="margin-left: 8px; width: 120px" id="sortBtn"> 排序方式 <a-icon type="down" /> </a-button>
            </a-dropdown>
        </div>
        <div class="hotelList">
            <a-layout class="test">
                <a-layout-content style="min-width: 800px;">
                    <a-spin :spinning="hotelListLoading">
                        <div class="card-wrapper">
                            <HotelCard :hotel="item" v-for="item in hotelList.slice((hotelListParams.pageNo)*pageSize,(hotelListParams.pageNo+1)*pageSize)" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
<!--                            <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3"></div>-->
                        </div>
                        <a-pagination style="padding-top: 30px" showQuickJumper :total="hotelList.length" :pageSize.sync="pageSize"  @change="pageChange"></a-pagination>
                    </a-spin>
                </a-layout-content>
            </a-layout>
        </div>
        <search-modal></search-modal>
    </div>
</template>
<script>
import HotelCard from './components/hotelCard';
import searchModal from "./components/searchModal";
import { mapGetters, mapActions, mapMutations } from 'vuex'

export default {
  name: 'home',
  components: {
      HotelCard,
      searchModal,
  },
  data(){
      return{
          emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}],
          pageSize: 8,
          sortOrder: 0, //0为未排序，1为评分降序，2为评分升序，3为星级降序，4为星级升序
      }
  },
  async mounted() {
      const data = {
          pageNo: 0,
          pageSize: 8,
      }
      await this.set_hotelListParams(data);
      //console.log(this.hotelListParams)
      await this.getHotelList();
  },
  computed: {
    ...mapGetters([
        'hotelList',
        'hotelListLoading',
        'searchModalVisible',
        'hotelListParams'
    ])
  },
  methods: {
      ...mapMutations([
          'set_hotelListParams',
          'set_hotelListLoading',
          'set_searchModalVisible',
      ]),
      ...mapActions([
          'getHotelList',
          `StarSort`,
          `StarSort2`,
          `RateSort`,
          `RateSort2`,
      ]),
      pageChange(page, pageSize) {
          const data = {
              pageNo: page - 1
          }
          this.set_hotelListParams(data)
          this.set_hotelListLoading(true)
          //this.getHotelList()
          this.set_hotelListLoading(false)
      },
      jumpToDetails(id){
          this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
      },
      search(){
          this.set_searchModalVisible(true)
      },
      handleMenuClick(e) {
          //console.log('click', e);
          //console.log(e.key)
          if(e.key==="2"){
              console.log(this.sortOrder)
              if (this.sortOrder === 1){
                  this.RateSort2();
                  this.sortOrder = 2;
                  document.getElementById("sortBtn").innerHTML = "评&nbsp&nbsp&nbsp分&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp↑";
              }else {
                  this.RateSort();
                  this.sortOrder = 1;
                  document.getElementById("sortBtn").innerHTML = "评&nbsp&nbsp&nbsp分&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp↓";
              }
          }
          else if(e.key==="1"){
              if (this.sortOrder === 3) {
                  this.StarSort();
                  this.sortOrder = 4;
                  document.getElementById("sortBtn").innerHTML = "星&nbsp&nbsp&nbsp级&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp↑";
              }else {
                  this.StarSort2();
                  this.sortOrder = 3;
                  document.getElementById("sortBtn").innerHTML = "星&nbsp&nbsp&nbsp级&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp↓";
              }
          }
      },

  }
}
</script>
<style scoped lang="less">
    .test{
        border-radius: 36px;
    }
    .search{
        margin-bottom: 50px;
        padding-bottom: 20px;
        padding-left: 50px;
        padding-top: 300px;
        width: 100%;
        height: auto;
        opacity: 90%;
        background: url('../../assets/back.jpg') no-repeat;
        background-size: cover;
    }
  .hotelList {
    background: transparent;
    text-align: center;
    padding: 50px 0;
    padding-top: 0;
    .emptyBox {
      height: 0;
      margin: 10px 10px
    }
    .card-wrapper{
      display: flex;
        background: transparent;
        border-radius: 36px;
      justify-content: space-around;
      flex-wrap: wrap;
      flex-grow: 3;
      min-height: 800px
    }
    .card-wrapper .card-item {
      margin: 50px;
      position: relative;
      height: 188px;
    }
  }
</style>