
package com.impuestos.tarifas.model;

import java.math.BigDecimal;


public class liquidacion {
    public record Liquidacion(
            String estudiante,
        int creditos,
        BigDecimal subtotal,
        BigDecimal descuento,
        BigDecimal recargo,
        BigDecimal iva,
        BigDecimal total
            ){}
}
