import { axios } from '@/utils/request'
const api = {
    commentPre: '/api/comment'
}
export function commitCommentAPI(data) {
    return axios({
        url: `${api.commentPre}/addComment`,
        method: 'POST',
        data,
    })
}
export function getUserCommentsAPI(param) {
    return axios({
        url: `${api.commentPre}/${param.userId}/getUserComments`,
        method: 'GET'
    })
}
export function getHotelCommentsAPI(param) {
    return axios({
        url: `${api.commentPre}/${param.hotelId}/getHotelComments`,
        method: 'GET'
    })
}