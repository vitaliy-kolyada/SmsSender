package model.constants;

public enum Address {
    SEND_MESSAGE("https://api.mobizon.com/service/message/sendsmsmessage?"),
    GET_SMS_STATUS("https://api.mobizon.com/service/message/getsmsstatus?"),
    LIST_MESSAGES("https://api.mobizon.com/service/message/list?");
    private String address;

    Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
