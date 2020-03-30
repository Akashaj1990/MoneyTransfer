package revolut.bank.utils;

import revolut.bank.entity.Money;
import revolut.bank.enums.Currency;

import java.math.BigDecimal;

public class CurrencyConverter {

    private CurrencyConverter() {
    }

    public static Money convertCurrency(Money baseMoney, Currency curr) {
        return new Money(curr, convertCurrency(baseMoney.getBalance(), baseMoney.getBaseCurrency(), curr));
    }

    private static BigDecimal convertCurrency(BigDecimal baseAmt, Currency base, Currency convertedCurr) {
        //based on conversion rates
        return baseAmt;
    }

}
