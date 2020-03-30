package revolut.bank.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import revolut.bank.annotation.ChecksumParam;
import revolut.bank.entity.Money;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TransferRequestBody implements Serializable {

    @ChecksumParam
    private long fromAccountNumber;

    @ChecksumParam
    private long toAccountNumber;

    @ChecksumParam
    private Money transferAmount;
}
