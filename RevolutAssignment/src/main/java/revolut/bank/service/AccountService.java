package revolut.bank.service;

import revolut.bank.entity.Money;
import revolut.bank.enums.AccountOpStatus;
import revolut.bank.enums.BankOperation;

public interface AccountService {
    public AccountOpStatus processRequest(BankOperation operation, Long accountNumber, Money money);
}
