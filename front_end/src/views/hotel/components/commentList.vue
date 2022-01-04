<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="list">
            <a-table
                    :columns="columns"
                    :dataSource="comments"
            >
            </a-table>
        </div>
    </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";

const columns = [
    {
        title: '序号',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
        key: 'userName',
    },
    {
        title: '评价内容',
        dataIndex: 'comments',
        key: 'comments',
    },
    {
        title: '评价时间',
        dataIndex: 'create_time',
        key: 'create_time',
    },
];
export default {
    name: 'commentList',
    props: {
        comments: {
            type: Array
        }
    },
    mutations: {
        set_curHotelId: function (state, data) {
            state.currentHotelId = data
        },
    },
    data() {
        return {
            columns
        }
    },
    computed: {
        ...mapGetters([

        ])
    },
    monuted() {
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_comments'
        ]),
        ...mapActions([
            'addNum',
            'subNum'
        ]),
    }
}
</script>

<style scoped>

</style>