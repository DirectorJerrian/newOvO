<template>
    <div class="manageUserOrder-wrapper">
        <a-table
                :columns="columns"
                :dataSource="userOrderList"
                rowKey="id"
                bordered
        >
                    <span slot="hotelDetial" slot-scope="record">
                         <a-button type="link" size="small" @click="hotelDetail(record)">{{record}}</a-button>
                    </span>
            <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
            <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
            <a-tag slot="orderState" color="blue" slot-scope="text">
                {{ text }}
            </a-tag>
            <span slot="action" slot-scope="record">
                        <a-button type="default" size="small" @click="detail(record)">查看</a-button>
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                        <a-popconfirm
                                title="你确定撤销该笔订单吗？"
                                @confirm="confirmCancelOrder(record.id)"
                                @cancel="cancelCancelOrder"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical" v-if="record.orderState == '已入住'"></a-divider>
                        <a-button type="primary" size="small" v-if="record.orderState == '已入住'"
                                  @click="showRateModal(record.id)">评价</a-button>



                    </span>
        </a-table>
    </div>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'

    const moment = require('moment')
    const columns = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            key: 'hotelDetail',
            dataIndex:'hotelName',
            scopedSlots: {customRender: 'hotelDetial'},//who spelled it???
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: {customRender: 'roomType'}
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: {customRender: 'checkInDate'}
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: {customRender: 'checkOutDate'}
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {text: '已入住', value: '已入住'}],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: {customRender: 'orderState'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },

    ];
    export default {
        name: "manageUserOrder",
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns,
                form: this.$form.createForm(this, {name: 'manageUserOrder'}),
            }
        },
        computed: {
            ...mapGetters([
                'userId',
                'userOrderList',
            ]),
        },
        async mounted() {
            await this.getUserOrders()
        },
        methods: {
            ...mapMutations([]),
            ...mapActions([
                'getUserOrders',
                'cancelOrder',
                'orderRate',
            ]),
            cancelCancelOrder() {},
        }
    }
</script>

<style scoped lang="less">
    .manageUserOrder-wrapper {
        padding: 50px;
        border-radius: 36px;
        background: transparent;
        z-index: 1;
        margin-top: 50px;
    }

</style>
<style lang="less">
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    .manageUserOrder-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>