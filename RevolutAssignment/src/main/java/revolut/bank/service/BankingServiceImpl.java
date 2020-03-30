package revolut.bank.service;

import revolut.bank.database.InMemoryDb;
import revolut.bank.entity.Money;
import revolut.bank.entity.response.TransferResponse;
import revolut.bank.enums.BankOperation;
import revolut.bank.enums.TransferStatus;
import revolut.bank.utils.IdGenerator;

import java.util.List;

public class BankingServiceImpl implements BankingService {

    private BankingServiceImpl() {

    }

    private AccountService accountService = AccountServiceImpl.getInstance();

    public TransferResponse processTransfer(Long fromAccountNumber, Long toAccountNumber, Money money) {
        long txnId = IdGenerator.generateTxnId();
        TransferResponse resp = new TransferResponse(TransferStatus.PENDING, txnId, fromAccountNumber, toAccountNumber, money);
        List<Long> accounts = List.of(fromAccountNumber, toAccountNumber);
        try {
            //Acquiring write locks
            if (InMemoryDb.acquireWriteLockOnAccounts(accounts)) {
                accountService.processRequest(BankOperation.DEBIT, fromAccountNumber, money);
                accountService.processRequest(BankOperation.CREDIT, toAccountNumber, money);
                resp.setStatus(TransferStatus.SUCCESS);
            } else {
                resp.setStatus(TransferStatus.LOCK_UNAVAILABLE);
            }
        } catch (InterruptedException e) {
            resp.setStatus(TransferStatus.FAIL);
        } finally {
            //Releasing locks
            InMemoryDb.releaseWriteLockOnAccounts(accounts);
        }
        return resp;
    }

    public TransferResponse getTransfer(Long txnId) {
        return InMemoryDb.getTransfer(txnId);
    }

    public static BankingService getInstance() {
        return ServiceHolder.INSTANCE;
    }

    private static class ServiceHolder {
        private static final BankingService INSTANCE = new BankingServiceImpl();
    }
}
