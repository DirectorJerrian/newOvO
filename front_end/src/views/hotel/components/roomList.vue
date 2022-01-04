<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="rooms"
            >
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="text, record" >
                    <a-button type="primary" @click="order(record)" v-if="userInfo.userType=='Client'">预定</a-button>
                    <a-button-group v-if="userInfo.userType=='HotelManager'">
                      <a-button icon="plus" @click="handleAdd(record)"/>
                      <a-button icon="minus" @click="handleSub(record)"/>
                    </a-button-group>
                </span>
            </a-table>
        </div>
        <OrderModal style="width: 70%"></OrderModal>
        <a-modal
                title="您的订单已预订成功！"
                :visible="orderInfoVisible"
                :confirm-loading="confirmLoading"

                okText="确认"
                @ok="set_orderInfoVisible(false)"
                @cancel="set_orderInfoVisible(false)"
        >
            <div class="a">
                <span class="l">酒店名称:</span>
                <span class="r">{{successfulOrderInfo.hotelName}}</span>
            </div>
            <div class="a">
                <span class="l">价格:</span>
                <span class="r">{{successfulOrderInfo.price}}</span>
            </div>
            <div class="a">
                <span class="l">入住时间:</span>
                <span class="r">{{successfulOrderInfo.checkInDate}}</span>
            </div>
            <div class="a">
                <span class="l">离店时间:</span>
                <span class="r">{{successfulOrderInfo.checkOutDate}}</span>
            </div>
            <div class="a">
                <span class="l">订单状态:</span>
                <span class="r">已下单</span>
            </div>
            <div class="a">
                <span class="l">房型:</span>
                <span class="r">{{successfulOrderInfo.roomType}}</span>
            </div>
            <div class="a">
                <span class="l">房间数:</span>
                <span class="r">{{successfulOrderInfo.roomNum}}</span>
            </div>
            <div class="a">
                <span class="l">入住人数:</span>
                <span class="r">{{successfulOrderInfo.peopleNum}}</span>
            </div>
            <template slot="footer">
                <a-button type="primary" @click="set_orderInfoVisible(false)">确认</a-button>
            </template>
        </a-modal>
    </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
const columns = [
    {  
      title: '房型',
      dataIndex: 'roomType',
      key: 'roomType',
    },
    {
      title: '当前房间数量',
      dataIndex: 'curNum',
      key: 'curNum',
    },
    {
      title: '房间总数',
      dataIndex: 'total',
      key: 'total',
    },
    {
      title: '房价',
      key: 'price',
      dataIndex: 'price',
      scopedSlots: { customRender: 'price'}
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
const columns1 = [
    {
        title: '勾选',
        dataIndex: 'id',
        scopedSlots: {customRender: 'id'}

    },
    {
        title: '优惠描述',
        dataIndex: 'description',

    },
    {
        title: '会员专享折扣',
        dataIndex: 'discount',
    },

    {
        title: '剩余数量',
        dataIndex: 'number',

    },
    {
        title: '优惠金额',
        dataIndex: 'discount_money',
    },
];
export default {
    name:'roomList',
    props: {
        rooms: {
            type: Array
        }
    },
    data() {
        return {
            columns,
            columns1,
            confirmLoading: false,
        }
    },
    components: {
        OrderModal
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'userInfo',
            'userId',
            'orderInfoVisible',
            'successfulOrderInfo'
        ])
    },
    monuted() {
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom',
            'set_orderInfoVisible'
        ]),
        ...mapActions([
            'addNum',
            'subNum'
        ]),
        order(record) {
            this.set_currentOrderRoom(record)
            this.set_orderModalVisible(true)
        },
        handleAdd(record){
            //console.log(record)
            const data={
                roomId:record.id
            }
            record.total++
            record.curNum++
            this.addNum(data)
        },
        handleSub(record){
            const data={
                roomId:record.id
            }
            if(record.total>0) {
                record.curNum--
                record.total--
                this.subNum(data)
            }
        }
    }
}
</script>
<style scoped>
    .a{
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .l{
        display: inline-block;
        width: 100px;
        margin-left: 40px;
        font-size: 15px;
    }
    .r{
        margin-left: 10px;
        font-size: 15px;
    }
</style>