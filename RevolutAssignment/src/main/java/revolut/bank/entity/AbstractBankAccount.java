package revolut.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class AbstractBankAccount {
    private long accountNumber;
    private long accountHolderNumber;
    private String branchCode;
    private Money currentAccountBalance;
}
