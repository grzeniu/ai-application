package pl.edu.wat.ai.app.integration.echangerate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class JmsMessage implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private Integer id;
    private String fromCurrency;
    private String toCurrency;
}
