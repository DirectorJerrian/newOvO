<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="账户管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager">
                        <a-icon type="plus"/>
                        添加用户
                    </a-button>
                </div>
                <a-table
                        :columns="columns"
                        :dataSource="managerList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="action" slot-scope="text, record">
                        <a-popconfirm
                                title="你确定删除该用户吗？"
                                @confirm="confirmDeleteManager(record.id)"
                                @cancel="cancelDelete"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger">删除用户</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
    </div>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddManagerModal from './components/addManagerModal'

    const columns = [
        {
            title: '用户邮箱',
            dataIndex: 'email',
        },
        {
            title: '用户名',
            dataIndex: 'userName',
        },
        {
            title: '用户密码',
            dataIndex: 'password',
        },
        {
            title: '用户手机号',
            dataIndex: 'phoneNumber',
        },
        {
            title: '信用值',
            dataIndex: 'credit',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    export default {
        name: 'manageHotel',
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, {name: 'manageUser'}),
            }
        },
        components: {
            AddManagerModal
        },
        computed: {
            ...mapGetters([
                'addManagerModalVisible',
                'managerList'
            ])
        },
        mounted() {
            this.getManagerList()
        },
        methods: {
            ...mapActions([
                'getManagerList',
                'deleteManager'
            ]),
            ...mapMutations([
                'set_addManagerModalVisible'
            ]),
            addManager() {
                this.set_addManagerModalVisible(true)
            },
            confirmDeleteManager(userId){
                this.deleteManager(userId)
            },
            cancelDelete() {}
        }
    }
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        z-index: 1;
        border-radius: 36px;
        background: transparent;
        margin-top: 50px;
        padding: 50px;

        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>