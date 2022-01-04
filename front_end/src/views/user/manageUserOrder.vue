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
            <div
                    slot="filterDropdown"
                    slot-scope="{setSelectedKeys, selectedKeys,confirm, clearFilters}"
                    style="padding: 8px"
            >
                <a-date-picker v-model="datePicker" @change="e => setSelectedKeys(e ? [e.format('YYYY-MM-DD')] : [])"/>
                <br>
                <a-button
                        type="primary"
                        icon="search"
                        size="small"
                        style="width: 45%; margin-right: 19px; margin-top: 10px"
                        @click="() => handleSearch(confirm,selectedKeys)"
                >
                    Search
                </a-button>
                <a-button size="small" style="width: 45%" @click="() => handleReset(clearFilters)">
                    Reset
                </a-button>
            </div>
            <a-icon
                    slot="filterIcon"
                    slot-scope="filtered"
                    :style="{ color: filtered ? '#108ee9' : '#bfbfbf' }"
                    type="filter"
            />
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
        <rateModal :show="show" :title="title" :rateId="rateId" :rate="rate" @hideModal="hideModal" @submit="submit"
                   @changeRate="changeRate">

        </rateModal>
        <viewModal></viewModal>
    </div>
</template>

<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import rateModal from "./rateModal";
    import viewModal from "./components/viewModal";

    const moment = require('moment')
    const columns = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            key: 'hotelDetail',
            dataIndex: 'hotelName',
            scopedSlots: {customRender: 'hotelDetial'},//who spelled it???
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: {customRender: 'roomType'}
        },
        {
            title: '入住时间',
            onFilter: (filterDate, record) => record.checkInDate.includes(filterDate),
            dataIndex: 'checkInDate',
            scopedSlots: {
                customRender: 'checkInDate',
                filterDropdown: 'filterDropdown',
                filterIcon: 'filterIcon',
            }
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
            filters: [{text: '已预订', value: '已预订'}, {text: '已取消', value: '已取消'}, {
                text: '已入住',
                value: '已入住'
            }, {text: '异常', value: '异常'}, {text: '已评价', value: '已评价'}],
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
                filterDate: '',
                datePicker:undefined,
                title: "请对本次酒店服务做出评价",
                show: false,
                rateId: 0,
                rate: 0,
            }
        },
        components: {
            rateModal,
            viewModal
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
            ...mapMutations([
                'set_viewModalVisible',
                'set_currentOrderInfo',
            ]),
            ...mapActions([
                'getUserOrders',
                'cancelOrder',
                'orderRate',
            ]),
            cancelCancelOrder() {
            },
            confirmCancelOrder(orderId) {
                this.cancelOrder(orderId)
            },
            // onChange(value) {
            //     console.log(value.format('YYYY-MM-DD'));
            //     this.filterDate=value.format('YYYY-MM-DD')
            // },
            handleReset(clearFilters) {
                clearFilters();
                // this.filterDate = '';
                this.datePicker=undefined
            },
            handleSearch(confirm,selectedKeys) {
                confirm();
                this.filterDate = selectedKeys[0];
            },
            hideModal() {
                // 取消弹窗回调
                this.show = false
                this.rate
            },

            submit() {
                // 确认弹窗回调
                this.show = false
                //console.log("评价的orderid是", this.rateId, "评分是", this.rate)
                let data1 = {
                    id: 0,
                    rate: 0
                };
                data1.id = this.rateId
                data1.rate = this.rate
                this.orderRate(data1)

            },
            showRateModal(id) {
                this.rateId = id
                //console.log(id)
                this.show = true
            },
            changeRate(value){
                this.rate=value
                //console.log("评分被改为",this.rate)
            },
            detail(value){
                this.set_currentOrderInfo(value)
                this.set_viewModalVisible(true)
            },
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
    .manageUserOrder-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>