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
    },

    mutations: {
        set_comments: function (state, data) {
            state.comments = data
        },
        set_curHotelId: function (state, data) {
            state.currentHotelId = data
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
    },
}

export default comment