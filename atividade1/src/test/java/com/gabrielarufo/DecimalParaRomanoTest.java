package com.gabrielarufo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DecimalParaRomanoTest {

    @Test
    void converteDecimalParaRomano() throws Exception {
        DecimalParaRomano decimalParaRomano = new DecimalParaRomano();
        decimalParaRomano.setDecimal(19);
        assertEquals("XIX",decimalParaRomano.converterEmRomanos());

        decimalParaRomano.setDecimal(3549);
        assertEquals("MMMDXLIX", decimalParaRomano.converterEmRomanos());
    }

    @Test
    @DisplayName("Conversão de Decimal para Romano falha com o número 0.")
    void numeroComZero() {
        DecimalParaRomano decimalParaRomano = new DecimalParaRomano();
        final Exception exception = assertThrows(Exception.class, () -> {
            decimalParaRomano.setDecimal(0);
            decimalParaRomano.converterEmRomanos();
        });

        assertEquals("O valor inserido deve ser maior que 0.", exception.getMessage());
    }

    @Test
    void numeroGrande() {
        DecimalParaRomano decimalParaRomano = new DecimalParaRomano();
        Exception exception = assertThrows(Exception.class, () -> {
            decimalParaRomano.setDecimal(4000);
            decimalParaRomano.converterEmRomanos();
        });

        assertEquals("O valor máximo para conversão decimal é 3999.", exception.getMessage());
    }
}
