package revolut.bank.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import revolut.bank.entity.Money;
import revolut.bank.enums.TransferStatus;
import revolut.bank.enums.TransferStatus;

@Data
@AllArgsConstructor
public class TransferResponse {

    private TransferStatus status;
    private Long txnId;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private Money transferredAmt;

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransferResponse{");
        sb.append("txnId=").append(txnId);
        sb.append(", status=").append(status);
        sb.append(", fromAccountNumber='").append(fromAccountNumber).append('\'');
        sb.append(", toAccountNumber='").append(toAccountNumber).append('\'');
        sb.append(", transferredAmt=").append(transferredAmt);
        sb.append('}');
        return sb.toString();
    }
}
