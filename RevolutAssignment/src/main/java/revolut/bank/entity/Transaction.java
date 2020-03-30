package revolut.bank.entity;

import revolut.bank.enums.BankOperation;

import java.math.BigDecimal;

public class Transaction {
    private BankOperation operation;
    private Money openingBalance;
    private Money closeBalance;
}
