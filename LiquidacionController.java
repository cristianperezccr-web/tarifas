
package com.impuestos.tarifas.controller;

import com.impuestos.tarifas.model.liquidacion.Liquidacion;
import com.impuestos.tarifas.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiquidacionController {
    @Autowired
    private TarifaService tarifaService;
    
    @GetMapping("/liquidacion")
    public Liquidacion calcular(@RequestParam String estudiante,
                                @RequestParam int creditos,
                                @RequestParam(defaultValue = "false") boolean becado,
                                @RequestParam(defaultValue = "false") boolean enMora) {
        return tarifaService.calcularLiquidacion(estudiante, creditos, becado, enMora);

    }
}