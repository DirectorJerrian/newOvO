<template>
    <div class="modal-bg" v-show="show">
        <div class="modal-container">
            <div class="modal-header">
                {{ title }}
            </div>
            <div class="modal-main">
                <a-textarea v-model="text"
                            placeholder="请输入评价内容"
                            :autoSize="{minRows: 3, maxRows: 4}"
                            allow-clear
                            @change="changeText"
                />
            </div>
            <div class="rate">
                <a-rate v-model="value" @change="changeRate" :tooltips="desc" />
                <span class="ant-rate-text">{{ desc[value - 1] }}</span>

            </div>
            <div class="modal-footer">
                <a-button @click="hideModal">取消评价</a-button>
                <a-button @click="submit">确认评价</a-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {

        name: 'rateModal',
        data(){
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 1000 },
                        sm: { span: 12 },
                    },
                    wrapperCol: {
                        xs: { span: 100000 },
                        sm: { span: 6 },
                    },
                },
                value:3,
                desc: ['很糟糕', '不太好', '一般', '优秀', '棒极了'],
                text: '',

            }
        },
        props: {
            show: {
                type: Boolean,
                default: false
            },
            title: {
                type: String,
                default: ''
            },
            rateId:{
                type:Number,
                required: true,
                default:0
            },
            rate:{
                type:Number,
                default:0
            },
            comments:{
                type: String,
                default: '',
            },
        },
        methods: {
            hideModal() {
                this.$emit('hideModal')
            },

            submit() {
                this.$emit('submit')

            },
            changeRate(v){
                let value=Number(v)
                //console.log("改变数值"+value)
                this.$emit('changeRate',value)
            },
            changeText(t){
                let text = t.target.value;
                //console.log(text);
                this.$emit('changeText', text)
            }
        }
    }
</script>

<style scoped>
    .modal-bg {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,.5);
        z-index: 10;
    }
    .modal-container {
        background: #fff;
        border-radius: 10px;
        overflow: hidden;
        position: fixed;
        top: 50%;
        left: 50%;
        width: 30%;
        height: 30%;
        transform: translate(-50%,-50%);
    }
    .modal-header {
        height: 20%;
        background: #409EFF;
        color: #fff;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: move;
    }
    .modal-main {
        height: 50%;
    }
    .rate{
        height: 15%;
        text-align: center;
    }
    .modal-footer {
        height: 5%;
        text-align: center;
    }
    .modal-footer button {

    }

</style>