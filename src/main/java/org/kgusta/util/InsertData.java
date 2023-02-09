package org.kgusta.util;

import org.kgusta.model.*;
import org.kgusta.repository.*;

import java.math.BigDecimal;
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
        User user = userRepository.findByUsername("user1");
        user.getConversionHistory().getConversion().stream().forEach(System.out::println);

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
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepository();
        Currency dollar = currencyRepository.findByCode("USD");
        Currency euro = currencyRepository.findByCode("EUR");

        ExchangeRate exchangeRate = ExchangeRate.builder()
                .fromCurrency(dollar)
                .toCurrency(euro)
                .rate(BigDecimal.valueOf(0.8))
                .build();

        exchangeRateRepository.save(exchangeRate);
        User user = userRepository.findByUsername("user1");

        Conversion conversion = Conversion.builder()
                .exchangeRate(exchangeRate)
                .amount(BigDecimal.valueOf(1000))
                .build();
        conversionRepository.save(conversion);
        ConversionHistory conversionHistory = ConversionHistory.builder()
                .user(user)
                .build();

        user.setConversionHistory(conversionHistory);
        conversionHistory.getConversion().add(conversion);
        conversionHistoryRepository.save(conversionHistory);


    }
}
