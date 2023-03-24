package com.example.primeiroappuva;

public class Operacoes {

        private final double n1;
        private final double n2;

        public Operacoes(double n1, double n2){
            this.n1 = n1;
            this.n2 = n2;
        }

        public String somar(){
            return String.format("%s + %s = %s", fmt(n1), fmt(n2), fmt(n1 + n2));
        }
        public String subtrair(){
            return String.format("%s - %s = %s", fmt(n1), fmt(n2), fmt(n1 - n2));
        }
        public String multiplicar(){
            return String.format("%s × %s = %s", fmt(n1), fmt(n2), fmt(n1 * n2));
        }
        public String dividir(){
            if (n2 <= 0){
                return "Impossível";
            }
            return String.format("%s ÷ %s = %s", fmt(n1), fmt(n2), fmt(n1 / n2));
        }

        public String fmt(double d){

            if (d == Math.floor(d)) {
                return String.format("%.0f", d);
            } else {
                return Double.toString(d);
            }

        }

}