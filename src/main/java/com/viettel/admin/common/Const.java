package com.viettel.admin.common;

public class Const {
  public static class DATETIME_FORMAT{
    public static final String JAPANESE_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  }
  public static class VALIDATE_INPUT {
    public static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String regexPhone = "^[0-9]{10}$";
    public static final String regexPass = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,255}$";
    public static final String regexDate = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

    public static final String regexImageFile = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
  }

  public static class RESET_PASSWORD{
    public static final String DEFAULT_RESET_PASSWORD="Vship@123";
  }
  public static class REGEX{
    public static final String REGEX_FOR_POSITIVE_INTEGER="^\\+?(0|[1-9]\\d*)$";
  }

  public static class VALUE_EXCEL {
    public static final String[] TITLE_EXPORT_USER = new String[]{
        "STT",
        "Mã NV",
        "Họ & tên",
        "Email",
        "SĐT",
        "Chức danh SSO",
        "Đơn vị qly trên SSO",
        "Chức danh set"
    };
    public static final String[] TITLE_IMPORT_PARTNER = new String[]{
        "STT",
        "Mã số thuế",
        "Tên giao dịch đối tác",
        "Dịch vụ cung cấp",
        "Phân loại đại lý",
        "Quốc gia",
        "Người liên hệ",
        "Email",
        "SĐT"};

    public static final String[] TITLE_EXPORT_CUSTOMER = new String[]{
            "Stt",
            "Mã khách hàng",
            "Mã số thuế (*)",
            "Tên giao dịch khách hàng  (*)",
            "Địa chỉ (*)",
            "Người đại diện (*)",
            "CMT/Hộ chiếu (*)",
            "Ngày cấp (*)",
            "Nơi cấp (*)",
            "Email",
            "Phân loại khách hàng (*)",
            "Mã NV bán hàng  (*)",
            "NV bán hàng",
            "Đơn vị chăm sóc/phục vụ",
            "Địa chỉ giao dịch  (*)"
    };
    public static final String SPACE = "  ";
  }

  public static class DEFAULT_VALUE_RESPONSE {
    public static final Double DEFAULT_DOUBLE_VALUE = -999999d;
    public static final Integer DEFAULT_INTEGER_VALUE = -999999;
  }

  public static class USER_STATUS {
    public static final Integer ACTIVE = 1;
    public static final Integer DEACTIVE = 2;
  }

  public static class BANNER_STATUS {
    public static final Integer ACTIVE = 1;
    public static final Integer DEACTIVE = 2;

    public static final Integer ALL = 3;
  }

  public static class FILE_STYLE {
    public static final String BANNER = "BANNER";
    public static final String PROFILE = "PROFILE";
  }

  public static class MESSAGE {
    public static final String INVALID_INPUT = "INVALID INPUT";
    public static final String CODE_EXISTS = "CODE EXISTS";
  }

  public static class MESSAGE_CODE {
    public static final String OK = "IEM0";
    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String INVALID_EMAIL = "IEM2";
    public static final String INVALID_MOBILE = "IEM3";
    public static final String MOBILE_EXISTED = "IEM4";
    public static final String EMAIL_EXISTED = "IEM5";
    public static final String INVALID_PASSWORD = "IEM6";
    public static final String WRONG_PASSWORD = "IEM7";
    public static final String EMPTY_USERNAME_PASSWORD = "IEM8";
    public static final String EMPTY_PASSWORD = "IEM9";
    public static final String EMPTY_USERNAME = "IEM10";
    public static final String EMPTY_CONFIRM_PASSWORD = "IEM11";
    public static final String NO_SUCH_USER_ID = "IEM12";
    public static final String CONFIRMING_PASS_NOT_MATCH = "IEM13";
    public static final String USER_CREATE_SUCCESS = "SC1";

    public static final String EMAIL_NOT_FOUND = "IEM14";

    public static final String OTP_EXPIRED = "IEM15";

    public static final String OTP_5_TIMES = "IEM16";

    public static final String OTP_INCORRECT = "IEM17";

    public static final String USER_NOT_FOUND = "IEM18";

    public static final String BANNER_NOT_FOUND = "IEM19";

    public static final String START_END_DATE_INVALID = "IEM20";

    public static final String START_END_DATE_REQUIRED = "IEM21";

    public static final String BANNER_STATUS_INVALID = "IEM22";

    public static final String BANNER_NAME_REQUIRED = "IEM23";

    public static final String BANNER_STATUS_REQUIRED = "IEM24";

    public static final String BANNER_IMAGE_URL_REQUIRED = "IEM25";

    public static final String ACCOUNT_DISABLED = "IEM26";

    public static final String INVALID_CREDENTIALS = "IEM27";

    public static final String TOKEN_INVALID = "IEM28";

    public static final String ADDRESS_NOT_EXIST = "IEM29";

    public static final String ADDRESS_STATUS_INVALID = "IEM30";

    public static final String USER_ID_INVALID = "IEM31";

    public static final String TEN_BANNER_ACTIVE = "IEM32";

    public static final String ACTIVE_DATE_EXPIRED = "IEM33";

    public static final String CITY_NOT_EXIST = "IEM34";

    public static final String DISTRICT_NOT_EXIST = "IEM35";

    public static final String WARDS_NOT_EXIST = "IEM36";

    public static final String CITY_ID_REQUIRED = "IEM37";

    public static final String DISTRICT_ID_REQUIRED = "IEM38";

    public static final String ADDRESS_ID_REQUIRED = "IEM39";

    public static final String WARDS_ID_REQUIRED = "IEM40";

    public static final String USER_ID_REQUIRED = "IEM41";

    public static final String INVALID_DATE = "IEM42";

    public static final String INVALID_FILE_TYPE = "IEM43";

    public static final String ADDRESS_INVALID = "IEM44";

    public static final String PERMISSION_ID_INVALID = "IEM45";

    public static final String ROLE_NAME_REQUIRED = "IEM46";

    public static final String ROLE_ID_REQUIRED = "IEM47";

    public static final String ROLE_NOT_EXIST = "IEM48";

    public static final String PRODUCTS_EXISTED = "IEM49";
    public static final String FILE_ISEMPTY = "IEM54";

    public static final String CAN_NOT_CREATE_EXCEL_FILE = "IEM55";

    public static final String INPUT_REQUEST_INCORRECT = "IEM56";
    public static final String CAN_NOT_CREATE_DIRECTORY = "IEM57";
    public static final String CAN_NOT_SAVE_FILE = "IEM58";
    public static final String CAN_NOT_CREATE_IMAGE_THUMBNAIL = "IEM59";
    public static final String FILE_SIZE_INVALID = "IEM60";

    public static final String ORDER_DETAIL_NOT_FOUND = "IEM61";
    public static final String TEMPLATE_EXCEL_INCORRECT = "IEM62";
    public static final String JP_INVENTORY_NOT_FOUND = "IEM63";
    public static final String BILL_CODE_ALREADY_EXIST = "IEM64";
    public static final String BILL_CODE_OR_PACKAGE_CODE_NOT_EXIST = "IEM65";
    public static final String PACKAGE_CODE_OBJECT_NOT_FOUND = "IEM66";
    public static final String PACKAGE_WEIGHT_INVALID = "IEM67";
    public static final String IMAGE_DUPLICATE = "IEM68";
    public static final String LIST_REQUEST_IMPORT_EMPTY = "IEM69";
    public static final String FORMAT_FILE_EXPORT_DELIVERY_BILL_NOT_EXIST = "IEM70";
    public static final String FORMAT_ROW_EXCEL_NOT_FOUND = "IEM71";
    public static final String CAN_NOT_CREATE_ROW_IN_EXCEL_FILE = "IEM72";
    public static final String EXPORT_EXCEL_CONDITION_NOT_FOUND = "IEM73";
    public static final String NO_DATE_TO_EXPORT_EXCEL = "IEM74";
    public static final String CUSTOMER_NOT_FOUND = "IEM75";
    public static final String SHIP_COST_JP_VN_NOT_FOUND = "IEM76";
    public static final String CUSTOMER_DELIVERY_BILL_NOT_FOUND = "IEM77";
    public static final String FORMAT_FILE_EXPORT_FOLLOW_IMPORTED_NOT_EXIST = "IEM78";
    public static final String RATE_JP_VN_NOT_FOUND = "IEM79";
    public static final String FORMAT_FILE_EXPORT_FOLLOW_EXPORTED_NOT_EXIST = "IEM80";
    public static final String FORMAT_FILE_DELIVERY_GOODS_NOT_FOUND = "IEM81";
    public static final String OLD_PACKAGE_CODE_SAME_NEW_PACKAGE_CODE = "IEM82";
    public static final String DELIVERY_STATUS_NOT_CORRECT = "IEM83";
    public static final String LOGIN_TO_CONTINUE = "IEM84";
    public static final String FORMAT_FILE_CANCELLED_ORDER_NOT_FOUND = "IEM85";
    public static final String CANNOT_DELETE_SUCCESSFUL_DELIVERY = "IEM86";
    public static final String CANNOT_DELETE_VN_EXPORTED_DELIVERY = "IEM87";
    public static final String CANNOT_DELETE_CANCELLED_DELIVERY = "IEM88";
    public static final String CANNOT_DELETE_BEING_TRANSPORTED_DELIVERY = "IEM89";
    public static final String OLD_BILL_CODE_SAME_NEW_BILL_CODE = "IEM90";
    public static final String Internal_Server_Error = "ITEM91";
    public static final String NEGATIVE_JPY_RATE= "IEM92";

    public static final String NONEXISTENT_SHIPPING_CODE= "IEM94";

    public static final String  DUPLICATE_SHIPPING_CODE= "IEM95";

    public static final String NOT_VALID_SHIPPING_DATE="IEM96";

    public static final String NOT_EQUAL_SHIPPING_TYPE="IEM97";

    public static final String NOT_EQUAL_CUSTOMER_CODE="IEM98";
    public static final String EMPTY_FIELD_NOTE="IEM99";

    public static final String DUPLICATE_FEE = "IEM100";
    public static final String NON_EXIST_SHIPPING_TYPE = "IEM101";
    public static final String EMPTY_SHIPPING_CODE = "IEM102";
  }

  public static class ADDRESS_STATUS {
    public static final Integer DEFAULT = 1;

    public static final Integer IS_NOT_DEFAULT = 2;

    public static final Integer ACTIVE = 1;

    public static final Integer INACTIVE = 2;
  }

  public static class FILE_TYPE_UPLOAD {
    public static final Integer IMAGE = 1;
    public static final Integer OTHER = 2;
    public static final String SLASH = "/";
    public static final String THUMBNAIL = "Thumbnail_";
  }

  public static class ROLE_STATUS {

    public static final Integer ACTIVE = 1;

    public static final Integer INACTIVE = 2;
  }

  public static class TRANSACTION_TYPE {
    public static final Integer DEPOSITING = 0;

    public static final Integer PURCHASE = 1;
  }

  public static class FEE_STATUS {
    public static final Integer UPDATED = 1;
    public static final Integer NOT_UPDATED = 2;
  }

  public static class TERMS_PRIVACY_POLICY {
    public static final Integer TERMS = 1;
    public static final Integer PRIVACY_POLICY = 2;
  }

  public static class DELIVERY_STATUS {
    public static final Integer JP_WAREHOUSE_WAIT = 0;
    public static final Integer JP_WAREHOUSE_IMPORTED = 1;
    public static final Integer VN_WAREHOUSE_WAIT = 2;
    public static final Integer VN_WAREHOUSE_IMPORTED = 3;
    public static final Integer VN_WAREHOUSE_EXPORTED = 4;
    public static final Integer SUCCESSFUL_DELIVERY = 5;
    public static final Integer DELIVERY_FAILED = 6;
    public static final Integer SHIPPING = 7;

    public static final Integer EXPORT_RETURN = 8;
  }

  public static class BILL_TYPE {
    public static final Integer SOURCE_CODE = 1;
    public static final Integer GEN_CODE = 2;
    public static final Integer ALL_CODE = 3;
  }

  public static class JP_INVENTORY_DELIVERY_STATUS {
    public static final Integer IMPORTED = 1;
    public static final Integer EXPORTED = 2;
  }

  public static class PROCESS_STATUS {
    public static final Integer PROCESSED = 1;
    public static final Integer UN_PROCESS = 2;
    public static final Integer All_PROCESS = 0;
    public static final Integer NO_PROCESS = 3;
  }

  public static class COMPARE_STATUS {
    public static final Integer MATCH = 1;
    public static final Integer UN_MATCH = 2;
  }
}
