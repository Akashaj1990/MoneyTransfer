package revolut.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BankAccount extends AbstractBankAccount {

    private List<Transaction> lastTenTransactions;

    public void addTransaction(Transaction txn) {
        lastTenTransactions.add(txn);
    }
}
