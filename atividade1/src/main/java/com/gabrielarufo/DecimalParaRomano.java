package com.gabrielarufo;

import java.util.Collections;
import static java.lang.String.join;

public class DecimalParaRomano {
    private int decimal;
    private static final int[] DECIMAL = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMANO = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public DecimalParaRomano(){}

    public int getDecimal() {
        return decimal;
    }

    public void setDecimal(int decimal) throws Exception {
        if (decimal <= 0 ) {
            throw new Exception("O valor inserido deve ser maior que 0.");
        } else if (decimal >= 4000) {
            throw new Exception("O valor máximo para conversão decimal é 3999.");
        }
        this.decimal = decimal;
    }

    public String converterEmRomanos() {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < DECIMAL.length; i++) {
            int parteInteira = decimal / DECIMAL[i];
            decimal -= DECIMAL[i] * parteInteira;
            resultado.append(join("", Collections.nCopies(parteInteira, ROMANO[i])));
        }
        return resultado.toString();
    }
}