package revolut.bank.service;

import revolut.bank.entity.Money;
import revolut.bank.entity.response.TransferResponse;

public interface BankingService {

    public TransferResponse processTransfer(Long fromAccountNumber, Long toAccountNumber, Money money);
    public TransferResponse getTransfer(Long txnId);
}
