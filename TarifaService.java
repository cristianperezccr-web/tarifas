
package com.impuestos.tarifas.service;

import com.impuestos.tarifas.model.liquidacion.Liquidacion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TarifaService {
    
    @Value ("${tarifa.valor-credito}")
    private BigDecimal valorCredito;
    
        @Value("${tarifa.iva}")
    private BigDecimal porcentajeIva;

    @Value("${tarifa.descuento-beca}")
    private BigDecimal porcentajeBeca;

    @Value("${tarifa.recargo-mora}")
    private BigDecimal porcentajeMora;
    
public Liquidacion calcularLiquidacion(String estudiante, int creditos,
                                       boolean becado, boolean enMora) {
    if (creditos <= 0) {
        throw new IllegalArgumentException("Los creditos deben ser mayores a 0");
    }

    BigDecimal subtotal = valorCredito.multiply(BigDecimal.valueOf(creditos));
        BigDecimal descuento = becado
                ? subtotal.multiply(porcentajeBeca)
                : BigDecimal.ZERO;

        BigDecimal baseConDescuento = subtotal.subtract(descuento);

        BigDecimal recargo = enMora
                ? baseConDescuento.multiply(porcentajeMora)
                : BigDecimal.ZERO;

        BigDecimal baseGravable = baseConDescuento.add(recargo);
        BigDecimal iva = baseGravable.multiply(porcentajeIva);
        BigDecimal total = baseGravable.add(iva);

        return new Liquidacion(estudiante, creditos,
                redondear(subtotal), redondear(descuento),
                redondear(recargo), redondear(iva), redondear(total));
    }

    private BigDecimal redondear(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
        }
}
