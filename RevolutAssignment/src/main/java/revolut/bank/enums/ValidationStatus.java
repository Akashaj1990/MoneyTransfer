package revolut.bank.enums;

public enum ValidationStatus {

    INVALID_REQUEST("06", "Invalid Request");

    private String code;
    private String msg;

    private ValidationStatus(String code, String msg) {
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
