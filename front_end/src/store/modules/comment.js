import {
    commitCommentAPI,
    getUserCommentsAPI,
    getHotelCommentsAPI,
} from '@/api/comment'
import { message } from 'ant-design-vue'

const comment = {
    state: {
        comments: [],
        currentHotelId: '',
        commentId:0,
    },

    mutations: {
        set_comments: function (state, data) {
            state.comments = data
        },
        set_curHotelId: function (state, data) {
            state.currentHotelId = data
        },
        set_currentCommentId: function (state, data) {
            state.commentId = data
        }
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
        // getCommentById: async ({commit, state}) => {
            // const res = await getCommentByIdAPI({
            //     commentId: state.currentCommentId
            // })
            // if (res) {
            //     commit('set_currentHotelInfo', res)
                // console.log(state.currentCommentInfo)
        //     }
        // },
        set_currentCommentInfo: function (state, data) {
            state.currentCommentInfo = {
                ...state.currentCommentInfo,
                ...data,
            }
        },
        getUserComments: async ({state, commit},data) => {
            const res = await getUserCommentsAPI({userId:data});
            if (res) {
                commit('set_comments', res)
            }
        },
    },
}

export default comment