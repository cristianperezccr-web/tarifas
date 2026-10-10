
package com.impuestos.tarifas.runner;

import com.impuestos.tarifas.model.liquidacion.Liquidacion;
import com.impuestos.tarifas.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class simuladorRunner implements CommandLineRunner {
    @Autowired
    private TarifaService tarifaService;   // sin usar "new"

    @Value("${institucion.nombre}")
    private String institucion;

    @Override
    public void run(String... args) {
        System.out.println("==============================================");
        System.out.println("  " + institucion.toUpperCase());
        System.out.println("  SIMULADOR DE TARIFAS Y LIQUIDACION");
        System.out.println("==============================================");

        imprimir(tarifaService.calcularLiquidacion("Ana Gomez", 18, false, false));
        imprimir(tarifaService.calcularLiquidacion("Luis Perez", 20, true, false));
        imprimir(tarifaService.calcularLiquidacion("Maria Rojas", 15, true, true));
    }

    private void imprimir(Liquidacion l) {
        System.out.println("\nEstudiante : " + l.estudiante());
        System.out.println("Creditos   : " + l.creditos());
        System.out.println("Subtotal   : $" + l.subtotal());
        System.out.println("Descuento  : -$" + l.descuento());
        System.out.println("Recargo    : +$" + l.recargo());
        System.out.println("IVA        : +$" + l.iva());
        System.out.println("TOTAL      : $" + l.total());
        System.out.println("----------------------------------------------");
    }
}

