<template>
    <a-card hoverable class="commentCard" :title=comment.userName>
        <a-card-grid style="width:20%;padding: 12px 24px 0 24px;text-align:left; box-shadow: none">
            {{ targetOrder.roomType==="Family"?"大床房":(targetOrder.roomType==="BigBed"?"大床房":"双床房") }}
        </a-card-grid>
        <a-card-grid style="width:80%;padding: 12px 24px 0 24px;text-align:left; box-shadow: none; color: #9f9f9f">
            {{ comment.create_time }} 点评
        </a-card-grid>
        <a-card-grid style="width:20%;text-align:left; box-shadow: none">
            入住时间: {{ targetOrder.checkInDate }}<br><br>
            退房时间: {{ targetOrder.checkOutDate }}
        </a-card-grid>
        <a-card-grid style="width:80%;text-align:left; box-shadow: none; font-size:16px; font-weight: bold">
            {{ comment.comments }}
        </a-card-grid>
    </a-card>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";
    export default {
        name: "commentCard",
        props: {
            comment: {}
        },
        data(){
            return{
                targetOrder: {},
            }
        },
        computed:{
            ...mapGetters([
                // 'currentHotelComments',
                // 'currentCommentId',
                // 'currentCommentInfo',
                'hotelOrderList',
            ])
        },
        async mounted() {
            const that = this;
            console.log(that.hotelOrderList)
            // that.set_currentHotelId(Number(that.comment.id))
            // await that.getUserHotelOrders(that.currentHotelId)
            for(const i in that.hotelOrderList){
                if(that.hotelOrderList[i].id === Number(that.comment.orderId)){
                    that.targetOrder = that.hotelOrderList[i];
                }
                console.log(that.comment)
            }
        },
        methods:{
            ...mapMutations([
                // 'set_currentCommentId',
            ]),
            ...mapActions([
                // 'getCommentById',
            ]),
        }
    }
</script>

<style scoped>
.commentCard{
    min-height: 250px;
}
</style>