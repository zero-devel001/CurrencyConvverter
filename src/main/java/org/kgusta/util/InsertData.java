package org.kgusta.util;

import org.kgusta.model.*;
import org.kgusta.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InsertData {
    private static CurrencyRepository currencyRepository = new CurrencyRepository();
    private static UserRepository userRepository = new UserRepository();
    private static ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepository();
    private static ConversionRepository conversionRepository = new ConversionRepository();
    private static ConversionHistoryRepository conversionHistoryRepository= new ConversionHistoryRepository();


    public static void main(String[] args) {
        fillUsers();
        fillCurrency();
        fillExchangeRate();

        conversionHistoryRepository.findByUser(userRepository.findById(1L))
                .getConversion().forEach(System.out::println);

    }
    public static void fillUsers() {


        User user1 =  User.builder()
                .username("user1")
                .password("password1")
                .build();
        User user2 =  User.builder()
                .username("user2")
                .password("password2")
                .build();
        User user3 =  User.builder()
                .username("user3")
                .password("password3")
                .build();


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }

    public static void fillCurrency() {
        Currency dollar = Currency.builder()
                .code("USD")
                .name("USA Dollar")
                .build();
        Currency euro = Currency.builder()
                .code("EUR")
                .name("Euro")
                .build();
        Currency rub = Currency.builder()
                .code("RUB")
                .name("Rub")
                .build();
        Currency som = Currency.builder()
                .code("KGS")
                .name("Som")
                .build();
        Currency tenge = Currency.builder()
                .code("KZT")
                .name("Tenge")
                .build();
        Currency lira = Currency.builder()
                .code("TRY")
                .name("Lira")
                .build();
        currencyRepository.save(dollar);
        currencyRepository.save(euro);
        currencyRepository.save(rub);
        currencyRepository.save(som);
        currencyRepository.save(tenge);
        currencyRepository.save(lira);

    }

    public static void fillExchangeRate() {
        User user = userRepository.findByUsername("user1");

    // Get the from and to currencies for the conversion
        Currency fromCurrency = currencyRepository.findByCode("USD");
        Currency toCurrency = currencyRepository.findByCode("EUR");

    // Get the exchange rate for the from and to currencies
        ExchangeRate exchangeRate = ExchangeRate.builder()
                .fromCurrency(fromCurrency)
                .toCurrency(toCurrency)
                .rate(new BigDecimal(0.8d))
                .build();
        exchangeRateRepository.save(exchangeRate);

        Conversion conversion = new Conversion();
        conversion.setExchangeRate(exchangeRate);
        conversion.setAmount(new BigDecimal("100.0"));
        conversion.setResult(conversion.getAmount().multiply(exchangeRate.getRate()));
        conversion.setConversionDateTime(LocalDateTime.now());

        conversionRepository.save(conversion);
        ConversionHistory conversionHistory = new ConversionHistory();
        conversionHistory.setUser(user);
        conversionHistory.getConversion().add(conversion);
        conversion.setConversionHistory(conversionHistory);
        conversionHistoryRepository.save(conversionHistory);



    }
}
