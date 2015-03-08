/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.vista;

import edu.uniandes.ecos.modelo.BuscadorX;

/**
 * Clase que representa la vista por consola del aplicativo con los test definidos
 * @author deividosorio
 * @version 25/02/2015
 */
public class MainConsole {

    public static void main(String[] args) {

        BuscadorX test1 = new BuscadorX(0.20, 6, 0.55338, 10);
        BuscadorX test2 = new BuscadorX(0.45, 15, 1.75305, 10);
        BuscadorX test3 = new BuscadorX(0.495, 4, 4.60409, 10);
        
        System.out.println("Valor X con test1, P: 0.20 - dof: 6 - Valor esperado: 0.55338 - Valor X: " +
                test1.encontrarX());
                
//        System.out.println("Valor X con test2, P: 0.45 - dof: 15 - Valor esperado: 1.75305 - Valor X: " +
//                test2.encontrarX());
//        
//        System.out.println("Valor X con test3, P: 0.495 - dof: 4 - Valor esperado: 4.60409 - Valor X: " +
//                test3.encontrarX());
    }
}
