package org.kgusta.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_currency_id")
    private Currency fromCurrency;
    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    private Currency toCurrency;
    private BigDecimal rate;
}
