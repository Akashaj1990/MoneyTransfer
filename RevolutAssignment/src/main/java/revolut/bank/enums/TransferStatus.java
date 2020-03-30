package revolut.bank.enums;

public enum TransferStatus {

    SUCCESS("00", "Successfully Transferred"), INSUFFICIENT_BALANCE("01", "Insufficient Balance"),
    PENDING("02", "In Progress"),
    FAIL("03", "Error Occurred"), SYSTEM_ERROR("04", "Internal System Error"),
    LOCK_UNAVAILABLE("05", "Unable to acquire lock on accounts");

    private String code;
    private String msg;

    private TransferStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Status{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
