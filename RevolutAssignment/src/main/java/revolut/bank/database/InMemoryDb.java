package revolut.bank.database;

import revolut.bank.entity.BankAccount;
import revolut.bank.entity.response.TransferResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryDb {

    private InMemoryDb() {

    }

    //map that holds lock for each InMemory account obj
    //will be replaced by distributed lock in distributed design like n/2 locks
    private static final ConcurrentMap<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Long, BankAccount> accountMap = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Long, TransferResponse> transferMap = new ConcurrentHashMap<>();

    public static boolean acquireLockOnAccount(Long bankAccountNumber) throws InterruptedException {
        Lock lock = lockMap.get(bankAccountNumber);
        return lock.tryLock(10, TimeUnit.SECONDS);
    }

    public static boolean acquireWriteLockOnAccounts(List<Long> accountNumbers) throws InterruptedException {
        //sorting and then trying lock to remove deadlock or live lock scenario
        Collections.sort(accountNumbers);
        Optional<Long> result = accountNumbers.stream().filter(bankAccountNumber -> {
                    try {
                        return !lockMap.get(bankAccountNumber).tryLock(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        return true;
                    }
                }
        ).findFirst();
        return result.isEmpty();
    }

    public static void releaseWriteLockOnAccounts(List<Long> accountNumbers) {
        accountNumbers.forEach(bankAccountNumber -> {
            ReentrantLock lock = lockMap.get(bankAccountNumber);
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        });
    }

    public static TransferResponse getTransfer(Long txnId) {
        return transferMap.get(txnId);
    }
}
