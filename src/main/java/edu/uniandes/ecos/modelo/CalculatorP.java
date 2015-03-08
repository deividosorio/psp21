/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.modelo;

/**
 * Description: Clase encargada de representar los cálculos para obtener el
 * valor de P
 *
 * @author deividosorio
 * @version 02/03/2015
 */
public class CalculatorP {
    // -----------------------------------------------------------------
    // Variables
    // -----------------------------------------------------------------
    double valX;
    double valdof;
    double valW;
    int NUM_SEG;
        
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    public CalculatorP(double valX, double valdof, int numSeg) {
        this.valX = valX;
        this.valdof = valdof;
        this.NUM_SEG = numSeg;
        this.valW = this.valX / this.NUM_SEG;
    }

    // -----------------------------------------------------------------
    // Métodos públicos
    // -----------------------------------------------------------------
    /**
     * Método retorna el valor de P dado un x y un dof
     *
     * @return this.p tipo double
     */
    public double getP() {
        double p = 0;
        //recorre los valores de i = 0 hasta i = num_seg, identifica
        //si un valor es par o impar para hacer la operación * 2 o * 4
        //si es x == 0 o igual a Num_SEG se debe hacer la operación * 1
        for (int i = 0; i <= this.NUM_SEG; i++) {
            if (i == 0) {
                p += (this.valW / 3 * getFX(0));
            } else if (i == this.NUM_SEG) {
                p += (this.valW / 3) * getFX(this.valW * i);
            } else {
                if (i % 2 == 0) {
                    p += (this.valW / 3) * (2 * getFX(this.valW * i));
                } else {
                    p += (this.valW / 3) * (4 * getFX(this.valW * i));
                }
            }
        }
        return p;
    }

    // -----------------------------------------------------------------
    // Métodos privados
    // -----------------------------------------------------------------
    /**
     * Método retorna el càlculo de la función F(x)
     *
     * @return f(x) tipo double
     */
    private double getFX(double _x) {
        double fxNum = 0;
        double fxDen = 0;
        double fX2 = 0;
        double fX1 = 0;
        fxNum = 1 + (Math.pow(_x, 2) / this.valdof);
        fX1 = Math.pow(fxNum, -((this.valdof + 1) / 2));

        fxNum = getFactorial((this.valdof + 1) / 2);
        fxDen = Math.pow((this.valdof * Math.PI), (0.5)) * (getFactorial((this.valdof) / 2));
        fX2 = fxNum / fxDen;

        return fX1 * fX2;
    }

    /**
     * Método retorna del factorial de un nùmero
     *
     * @return factorial(x) tipo double
     */
    private double getFactorial(double numero) {
        return getGamma(numero - 1);
    }

    /**
     * Método retorna el càlculo de Gamma
     *
     * @return gamma(x) tipo double
     */
    private double getGamma(double numero) {
        if (numero == 0) {
            return 1;
        } else if (numero == 0.5) {
            return 0.5 * Math.sqrt(Math.PI);
        } else {
            return numero * getGamma(numero - 1);
        }
    }

}
