const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
  VIP: state=>state.user.VIP,
  VIPInfo: state=> state.user.VIPInfo,
  userAvailableVoucherList: state=>state.user.userAvailableVoucherList,
  //hotel
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelListParams: state => state.hotel.hotelListParams,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  hotelOrderList: state=>state.hotel.hotelOrderList,
  orderModalVisible: state => state.hotel.orderModalVisible,
  orderInfoVisible: state => state.hotel.orderInfoVisible,
  searchModalVisible: state => state.hotel.searchModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  userCreditRecord: state => state.user.userCreditRecord,
  viewModalVisible: state=> state.user.viewModalVisible,
  currentOrderId:state=> state.user.currentOrderId,
  currentOrderInfo:state=>state.user.currentOrderInfo,
  userVoucherList: state=> state.user.userVoucherList,
  orderPass: state=>state.hotel.orderPass,
  UserHotelOrderList: state=>state.user.UserHotelOrderList,
  bizRegions: state=>state.hotel.bizRegions,
  //admin
  managerList: state => state.admin.managerList,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  targetorderList: state=> state.hotelManager.targetorderList,
  targethotelList: state=> state.hotelManager.targethotelList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
  activeOrderId: state => state.hotelManager.activeOrderId,
  hasBig: state=> state.hotelManager.hasBig,
  hasDouble: state=> state.hotelManager.hasDouble,
  hasFam: state=> state.hotelManager.hasFam,
  //marketingStaff
  allVoucherList: state=>state.marketingStaff.allVoucherList,
  addAllVoucherVisible: state=>state.marketingStaff.addAllVoucherVisible,
  modifyVisible: state => state.hotelManager.modifyVisible,
  //Marketingstaff
  strangeOrderList: state=>state.marketingStaff.targetorderList,
  //comment
  currentHotelComments: state=>state.comment.comments,
  }
  
  export default getters