package revolut.bank.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static final AtomicLong txnId = new AtomicLong(10000);
    private static final AtomicLong accountNumber = new AtomicLong(10000);

    private IdGenerator() {

    }

    public static long generateTxnId() {
        return txnId.getAndIncrement();
    }

    public static long generateAccountNumber() {
        return accountNumber.getAndIncrement();
    }
}
