package com.gabrielarufo;

import java.util.HashMap;
import java.util.Map;

public class RomanoParaDecimal {

    private String romano;
    private Map<Character, Integer> romanoParaDecimal = new HashMap<>();

    public RomanoParaDecimal(){
        this.romanoParaDecimal.put('I', 1);
        this.romanoParaDecimal.put('V', 5);
        this.romanoParaDecimal.put('X', 10);
        this.romanoParaDecimal.put('L', 50);
        this.romanoParaDecimal.put('C', 100);
        this.romanoParaDecimal.put('D', 500);
        this.romanoParaDecimal.put('M', 1000);
    }

    public String getRomano() {
        return romano;
    }

    public void setRomano(String romano) throws Exception {
        String vMaiusc = romano.toUpperCase();

        if (repeticoesExcedentes(vMaiusc))
            throw new Exception("Valor inválido. Repetições excedentes");

        if (caracteresInvalidos(vMaiusc))
            throw new Exception("O valor possui caracteres inválidos.");

        if (regraDoI(vMaiusc))
            throw new Exception("O caractere 'I' está em uma posição inválida.");

        if (regraDoX(vMaiusc))
            throw new Exception("O caractere 'X' está em uma posição inválida.");

        if (regraDoL(vMaiusc))
            throw new Exception("O caracter 'L' está em uma posição inválida.");

        if (repeteMenor(vMaiusc))
            throw new Exception("Caracteres repetidos antes de um valor superior identificados.");

        this.romano = vMaiusc;
    }

    private boolean repeticoesExcedentes(String romano) {

        char prevSymbol = ' ';
        int repeatCount = 0;

        for (char currentSymbol : romano.toCharArray()) {
            if (currentSymbol == prevSymbol) {
                repeatCount++;
                if (repeatCount > 3) {
                    return true; // Repetição maior que 3, número inválido.
                }
            } else {
                repeatCount = 1; // Reinicia a contagem para um novo símbolo
            }
            prevSymbol = currentSymbol;
        }
        return false; // Se chegou até aqui, o número romano é válido.
    }

    private boolean caracteresInvalidos(String romano){
        for (char letra: romano.toCharArray()) {
            if (letra != 'M' && letra != 'D' && letra != 'C' &&
                    letra != 'L' && letra != 'X' && letra != 'V' && letra != 'I'){
                return true;
            }
        }
        return false;
    }

    private boolean regraDoI(String romano){
        char laux = ' ';
        for (char letra: romano.toCharArray()) {
            if (letra == 'I'){
                laux = letra;
            }
            if (laux == 'I' && (letra == 'M' || letra == 'D' || letra == 'C' || letra == 'L' )){
                return true;
            }
        }
        return false;
    }

    private boolean regraDoX(String romano){
        char laux = ' ';
        for (char letra: romano.toCharArray()) {
            if (letra == 'X'){
                laux = letra;
            }
            if (laux == 'X' && (letra == 'M' || letra == 'D' )){
                return true;
            }
        }
        return false;
    }

    private boolean regraDoL(String romano){
        char laux = ' ';
        for (char letra: romano.toCharArray()) {
            if (letra == 'L'){
                laux = letra;
            }
            if (laux == 'L' && (letra == 'M' || letra == 'D' || letra == 'C' )){
                return true;
            }
        }
        return false;
    }

    private boolean repeteMenor(String romano){
        int tam = romano.length();
        int repete = 0;

        for (int i = 1; i < tam; i++){
            char anterior = romano.charAt(i - 1);
            char atual = romano.charAt(i);
            if (repete > 0 && (this.romanoParaDecimal.get(anterior) < this.romanoParaDecimal.get(atual))){
                return true;
            }
            if (anterior == atual) {
                repete++;
            }
            else {
                repete = 0;
            }
        }
        return false;
    }

    public Integer converterEmDecimal() {
        int decimal = 0;
        int prevValue = 0;

        for (int i = this.romano.length() - 1; i >= 0; i--) {
            char currentSymbol = this.romano.charAt(i);
            int currentValue = this.romanoParaDecimal.get(currentSymbol);

            if (currentValue < prevValue) {
                decimal -= currentValue;
            } else {
                decimal += currentValue;
            }
            prevValue = currentValue;
        }
        return decimal;
    }
    
}
