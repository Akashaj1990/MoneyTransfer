package revolut.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import revolut.bank.enums.Currency;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Money {
    private Currency baseCurrency;
    private BigDecimal balance;
}
