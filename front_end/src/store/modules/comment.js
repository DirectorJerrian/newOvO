import {
    commitCommentAPI,
    getUserCommentsAPI,
    getHotelCommentsAPI,
} from '@/api/comment'
import { message } from 'ant-design-vue'


const comment = {
    state: {
        comments: [],
        commentInDetail:'',
        currentHotelId: '',
    },

    mutations: {
        set_comments: function (state, data) {
            state.comments = data
        },
        set_curHotelId: function (state, data) {
            state.currentHotelId = data
        },
        set_commentInDetail: function (state, data) {
            state.commentInDetail = data
        },
    },

    actions: {
        getHotelComments: async ({state, commit}) => {
            const res = await getHotelCommentsAPI({
                hotelId: state.currentHotelId
            })
            if (res) {
                commit('set_comments', res)
            }
        },
        getUserComments: async ({state, commit},data) => {
            console.log("传入的数据"+data);
            const res = await getUserCommentsAPI({userId:data});
            if (res) {
                commit('set_comments', res)
            }
        },
    },
}

export default comment