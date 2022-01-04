<template>
    <div class="room-list">
        <div class="list">
            <a-layout class="listLayout">
                <a-layout-content style="min-width: 800px;">
                    <CommentCard :comment="item"
                                 v-for="item in comments"
                                 :key="item.index"></CommentCard>
                </a-layout-content>
            </a-layout>
        </div>
<!--        <div class="list">-->
<!--            <a-table-->
<!--                    :columns="columns"-->
<!--                    :dataSource="comments"-->
<!--            >-->
<!--            </a-table>-->
<!--        </div>-->
    </div>
</template>

<script>
    import CommentCard from './commentCard';
    import {mapActions, mapGetters, mapMutations} from "vuex";

    // const columns = [
    //     {
    //         title: '序号',
    //         dataIndex: 'id',
    //         key: 'id',
    //     },
    //     {
    //         title: '用户名',
    //         dataIndex: 'userName',
    //         key: 'userName',
    //     },
    //     {
    //         title: '评价内容',
    //         dataIndex: 'comments',
    //         key: 'comments',
    //     },
    //     {
    //         title: '评价时间',
    //         dataIndex: 'create_time',
    //         key: 'create_time',
    //     },
    // ];
    export default {
        name: 'commentList',
        components: {
            CommentCard,
        },
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
                // columns
            }
        },
        computed: {
            ...mapGetters([])
        },
        monuted() {
        },
        methods: {
            ...mapMutations([
                'set_comments'
            ]),
            ...mapActions([
                'getOrderById',
            ]),
        }
    }
</script>

<style scoped>

</style>