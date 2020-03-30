package revolut.bank.entity.response;

import revolut.bank.enums.TransferStatus;

public class BankOperationResponse {
    private TransferStatus status;
    private long txnId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransferResponse{");
        sb.append("status=").append(status);
        sb.append(", txnId=").append(txnId);
        sb.append('}');
        return sb.toString();
    }
}
