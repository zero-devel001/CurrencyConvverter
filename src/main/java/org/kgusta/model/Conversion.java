package org.kgusta.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exchange_rate_id")
    private ExchangeRate exchangeRate;
    private BigDecimal amount;
    private BigDecimal result;
    private LocalDateTime conversionDateTime;
    public BigDecimal getResult() {
        return  amount.multiply(exchangeRate.getRate());
    }
    @ManyToOne
    @JoinColumn(name = "conversion_history_id")
    private ConversionHistory conversionHistory;


}
