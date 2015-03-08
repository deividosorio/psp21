/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.modelo;

import edu.uniandes.ecos.modelo.CalculatorP;

/**
 *
 * @author deividosorio
 */
public class BuscadorX {

    double ERROR_ACEPTABLE = 0.1;
    double VALOR_INICIAL_X = 1.5;

    double valP;
    double valdof;
    double valEsperado;
    double d = 0.5;
    int valNumSeg;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    public BuscadorX(double valP, double valdof, double valEsperado, int valNumSeg) {
        this.valP = valP;
        this.valdof = valdof;
        this.valEsperado = valEsperado;
        this.valNumSeg = valNumSeg;
    }

    // -----------------------------------------------------------------
    // Métodos públicos
    // -----------------------------------------------------------------
    /**
     * Método retorna el valor de P dado un x y un dof
     *
     * @return this.p tipo double
     */
    public double encontrarX() {
        double valI = calcularIntegral(this.valP, this.valdof, this.valNumSeg);
        double valNewI = 0;
        double difIntegrales;
        double valAjustadoX = this.VALOR_INICIAL_X;

        difIntegrales = Math.abs(valI - this.valEsperado);

        if (difIntegrales < this.ERROR_ACEPTABLE) {
            return difIntegrales;

        } else {

            while (Math.abs(difIntegrales) > this.ERROR_ACEPTABLE) {

                difIntegrales = valNewI - this.valEsperado;

                if (difIntegrales < this.ERROR_ACEPTABLE) {
                    d = ajuste(d, valAjustadoX);
                    valAjustadoX += d;
                } else if (difIntegrales > this.ERROR_ACEPTABLE) {
                    d = ajuste(d, valAjustadoX);
                    valAjustadoX -= d;
                }

                valNewI = calcularIntegral(valAjustadoX, this.valdof, this.valNumSeg);
                
                difIntegrales = Math.abs(valNewI - this.valEsperado);
            }
        }
        return valNewI;
    }

    /**
     * Metodo que permite realizar el calculo de la integral numerica.
     *
     * @param valX Valor de X
     * @param valdof Grados de libertad
     * @param valNumSeg Numero de segmentos
     * @return resultado de la integral según valor x, dof y numSeg double.
     */
    public double calcularIntegral(double valX, double valdof, int valNumSeg) {

        CalculatorP objIntegralInicial = new CalculatorP(valX, valdof, valNumSeg);
        double valIntegralInicial = objIntegralInicial.getP();

        valNumSeg *= 2;
        CalculatorP objNuevaIntegral = new CalculatorP(valX, valdof, valNumSeg);
        double valIntegralDefinitiva = objNuevaIntegral.getP();

        while ((Math.abs(valIntegralInicial - valIntegralDefinitiva)) > this.ERROR_ACEPTABLE) {

            valIntegralInicial = valIntegralDefinitiva;
            valNumSeg *= 2;

            objNuevaIntegral = new CalculatorP(valX, valdof, valNumSeg);
            valIntegralDefinitiva = objNuevaIntegral.getP();
        }

        return valIntegralDefinitiva;
    }

    /**
     * Metodo que permite ajustar el valor de d, si es necesario.
     *
     * @param d Valor a ajustar.
     * @param limiteSuperior Valor de X.
     * @return double con el valor de d.
     */
    private double ajuste(double d, double limiteSuperior) {
        if (limiteSuperior != 1.0) {
            d /= 2;
        }
        return d;
    }
}
