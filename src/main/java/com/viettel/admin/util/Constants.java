package com.viettel.admin.util;

import org.springframework.util.ObjectUtils;

public interface Constants {

    public interface COMMON {
        String patternDate = "dd-MM-yyyy";
        String patternXMLDate = "dd-MM-yyyy HH:mm:ss";
        String patternXMLDatetime = "yyyy-MM-dd HH24:MI:ss.FF6";
        String patternXMLDatetime1 = "ddMMyyyyHH24mmss";
        String patternSqlDate = "YYYYMMDD";
        String patternDateDanCu = "dd/MM/yyyy";
        String patternSqlDateDC = "yyyyMMdd";
        String patternDateDDMMYYYY = "DDMMYYYY";
        String patternSqlDateFromLocal = "yyyyMMdd";
        Integer THANH_CONG = 1;
        Integer THAT_BAI = 0;
        String CHUA_QUET = "0";
        String DA_QUET = "1";
        String X_TOTAL_COUNT = "X-Total-Count";
        String SUCCESS = "success";
        String ERROR = "error";

        public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
        public static final String SIGNING_KEY = "com123tnr";
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final String AUTHORITIES_KEY = "scopes";
    }

    public interface SYNC_FROM {
        String LANDSOFT = "LANDSOFT";
    }

    public interface LAND_SOFT_ENDPOINT {
        String DS_KHACH_HANG = "api/Promotion/GroupCustomerList";
        String DS_THANH_PHO = "api/Promotion/ProvinceList";
        String DS_HUYEN_XA = "api/Promotion/DistrictList?ProvinceID=";
        String PAYMENT = "api/Promotion/PaymentList?";

        String PAYMENT_CODE = "api/Deposit/CheckReferenceCode?";
        String GET_TOKEN = "auth/token";
        String PTG = "api/Promotion/PTG";
        String PRICE_LIST = "api/Promotion/PriceList";
        String LOCK = "api/Deposit/Lock";
        String INSERT = "api/Deposit/Insert";
        String LIQUIDATION = "api/Transaction/Liquidation";
        String DEPOSIT_REFUND = "api/Transaction/DepositRefund";
        String TRANSFER_PAYMENT = "api/Transaction/Transfer";
        String CHANGE_PRODUCT = "api/Transaction/ChangeProduct";
        String TRANSACTION_CUSTOMER = "api/Customer/Transaction";

        String PAYMENT_HISTORY = "api/Transaction/GetData?Identity=";

        String PAYMENT_HISTORY_DETAIL = "api/Transaction/Get";

        String UPDATE_PRODUCTION_STATUS = "api/Transaction/Get?TransactionId=";
    }

    public interface SEND_MAIL_SUBJECT {
        String ACTIVE_ACCOUNT = "Xác thực tài khoản tại TNR VIETNAM";
        String FORGET_PASSWORD = "Đặt lại mật khẩu tại TNR VIETNAM";
        String NOTIFICATION_ACTIVE_ACCOUNT_SUCCESS= "Chào mừng bạn đến với TNR VIETNAM";
        String RESET_PASSWORD = "Đặt lại mật khẩu tại TNR VIETNAM";
        String NOTIFICATION_CHANGE_PASSWORD_SUCCESS = "Đặt lại mật khẩu thành công tại TNR VIETNAM";
        String PAYMENT_QR = "Lưu phiếu thanh toán tại TNR VIETNAM";
        String SUBMIT_PAYMENT_SUCCESS = "Đặt chỗ thành công tại TNR VIETNAM";
        String SUBMIT_PAYMENT_FAIL = "Đặt chỗ Không thành công tại TNR VIETNAM";
        String NOTIFICATION_RECEIVER_REQUEST_CUSTOMER = "Thông báo nhận được yêu cầu của Quý Khách tại TNR VIETNAM";
        String NOTIFICATION_RECEIVER_REQUEST_CUSTOMER_SERVICE = "Thông báo nhận được yêu cầu của Khách Hàng tại TNR VIETNAM";
        String SUBJECT_DEPOSIT_REFUND = "Yêu cầu hoàn cọc";
        String SUBJECT_CHANGE_PAYMENT_PRODUCT = "Yêu cầu thay đổi Bất động sản";
        String SUBJECT_TRANSFER_PRODUCT = "Yêu cầu chuyển nhượng Bất động sản";
        String SUBJECT_CHANGE_PRODUCT_SUCCESS = "Xác nhận đổi lô/đổi căn thành công tại TNR VIETNAM";
        String SUBJECT_CHANGE_PRODUCT_FAIL = "Xác nhận đổi lô/đổi căn không thành công tại TNR VIETNAM";
        String SUBJECT_LIQUIDATION_AND_DEPOSIT_SUCCESS = "Thanh lý cọc/ hoàn cọc thành cộng tại TNR VIETNAM";
        String SUBJECT_LIQUIDATION_AND_DEPOSIT_FAIL = "Thanh lý cọc/ hoàn cọc không thành cộng tại TNR VIETNAM";
        String SUBJECT_TRANSFER_TRANSACTION_SUCCESS = "Chuyển nhượng thành công tại TNR VIETNAM";
        String SUBJECT_TRANSFER_TRANSACTION_FAIL = "Chuyển nhượng không thành công tại TNR VIETNAM";
        String SUBJECT__RECEIVER_TRANSFER_TRANSACTION_FAIL = "Nhận chuyển nhượng thành công và hướng dẫn đăng ký tài khoản tại TNR VIETNAM";
        String SUBJECT_NOTICE_PAYMENT_MOBILE_BANKING_CUSTOMER_SERVICE = "Yêu cầu duyệt giao dịch thanh toán tại TNR VIETNAM";
        String SUBJECT_NOTICE_PAYMENT_MOBILE_BANKING_CUSTOMER = "Tạo phiếu thanh toán và lock thành công tại TNR VIETNAM";
        String SUBJECT_COMPLETED_PAYMENT_DEPOSIT = "Thông báo thời hạn bổ sung số tiền đặt cọc";
    }

    public interface KEYCLOAK {
        String CLIENT_CREDENTIALS = "client_credentials";
    }

    public static boolean checkParam(Object... params){
        for (Object o : params){
            if(ObjectUtils.isEmpty(o)){
                return true;
            }
        }
        return false;
    }

}
