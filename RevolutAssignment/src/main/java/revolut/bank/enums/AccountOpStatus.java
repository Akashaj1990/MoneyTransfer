package revolut.bank.enums;

public enum AccountOpStatus {

    DEBIT_SUCCESS("00", "Successfully Debited"),
    INSUFFICIENT_BALANCE("01", "Insufficient Balance"),
    CREDIT_SUCCESS("00", "Successfully Credited");

    private String code;
    private String msg;

    private AccountOpStatus(String code, String msg) {
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
