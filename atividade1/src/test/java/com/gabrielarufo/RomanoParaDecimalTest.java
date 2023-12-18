package com.gabrielarufo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RomanoParaDecimalTest {

    @Test
    void caracteresInvalidos() {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        Exception exception = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("XIIP");
        });
        assertEquals("O valor possui caracteres inválidos.", exception.getMessage());
    }

    @Test
    void repeticoesExcedentes() {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        Exception exception = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("XXXXII");
        });
        assertEquals("Valor inválido. Repetições excedentes", exception.getMessage());
    }

    @Test
    @DisplayName("Regra do I")
    void regraDoI() {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        Exception exception = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMID");
        });
        assertEquals("O caracter 'I' está em posição inválida.", exception.getMessage());

        Exception exception2 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("IMD");
        });
        assertEquals("O caracter 'I' está em posição inválida.", exception2.getMessage());

        Exception exception3 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMIC");
        });
        assertEquals("O caracter 'I' está em posição inválida.", exception3.getMessage());

        Exception exception4 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMIL");
        });
        assertEquals("O caracter 'I' está em posição inválida.", exception4.getMessage());
    }

    @Test
    void regraDoX() {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        Exception exception = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("XM");
        });
        assertEquals("O caracter 'X' está em posição inválida.", exception.getMessage());

        Exception exception2 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMXD");
        });
        assertEquals("O caracter 'X' está em posição inválida.", exception2.getMessage());
    }

    @Test
    void regraDoL() {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        Exception exception = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("LM");
        });
        assertEquals("O caracter 'L' está em posição inválida.", exception.getMessage());

        Exception exception2 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMLD");
        });
        assertEquals("O caracter 'L' está em posição inválida.", exception2.getMessage());

        Exception exception3 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMLC");
        });
        assertEquals("O caracter 'L' está em posição inválida.", exception3.getMessage());
    }

    @Test
    @DisplayName("Caracteres repetidos antes de um valor maior")
    void repeteMenor() {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        Exception exception = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("MMCCD");
        });
        assertEquals("Caracteres repetidos antes de um valor maior.", exception.getMessage());

        Exception exception2 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("CCD");
        });
        assertEquals("Cracteres repetidos antes de um valor maior.", exception2.getMessage());

        Exception exception3 = assertThrows(Exception.class, () -> {
            romanoParaDecimal.setRomano("LIIX");
        });
        assertEquals("Caracteres repetidos antes de um valor maior.", exception3.getMessage());
    }

    @ParameterizedTest(name = "{0} => {1}")
    @CsvSource({
            "MMMDXLIX,  3549",
            "MCMLXXXII, 1982",
            "MMCCLIII,  2253",
            "CCCLII,    352",
            "XCIV,      94"
    })
    void valoresCorretos(String romano, int resultado) throws Exception {
        RomanoParaDecimal romanoParaDecimal = new RomanoParaDecimal();
        romanoParaDecimal.setRomano(romano);
        assertEquals(
                resultado, romanoParaDecimal.converterEmDecimal(),
                ()-> "Romano: "+romano+", equivale a "+resultado+" em decimal."
        );
    }
    
}
