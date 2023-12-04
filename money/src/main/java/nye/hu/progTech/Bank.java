package nye.hu.progTech;

import java.math.BigDecimal;
import java.util.Currency;

public interface Bank {
    default BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        return null;
    }
}
