package sender.util;

public enum Address {
    ADDRESS("http://api.myatompark.com/sms/3.0/");
    String address;

    Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
