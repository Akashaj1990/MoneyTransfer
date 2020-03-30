package revolut.bank.service;

import revolut.bank.entity.Money;
import revolut.bank.enums.AccountOpStatus;
import revolut.bank.enums.BankOperation;

public class AccountServiceImpl implements AccountService {

    private AccountServiceImpl() {
    }

    @Override
    public AccountOpStatus processRequest(BankOperation operation, Long accountNumber, Money money) {
        switch (operation) {
            case DEBIT:
                break;
            case CREDIT:
                break;
            default:
        }
        return null;
    }

    public static AccountService getInstance() {
        return ServiceHolder.INSTANCE;
    }

    private static class ServiceHolder {
        private static final AccountService INSTANCE = new AccountServiceImpl();
    }
}
