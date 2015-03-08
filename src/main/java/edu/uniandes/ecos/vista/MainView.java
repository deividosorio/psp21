/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.vista;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase que representa la vista para el ambiente web que será visualizada en un
 * explorador
 * @author deividosorio
 * @version 25/02/2015
 */
public class MainView {
    
        public static void showHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h1>PSP2.1 Programa para encontrar el valor de x para los "
                + "que la integración de la función t de 0 a x da un resultado "
                + "de p.</h1>");
        pw.println("<p>ECOS 2015 <br>Deivid Alexander Osorio Barrera</p>");
        pw.println("<p>Taller N.6</p>");
        
        pw.write("<p>Ingrese los siguientes valores:</p> \n");
        pw.write("<form action=\"calc\" method=\"post\"> \n"
                + "    <br>X        : <input type=\"text\" size=\"10\" name=\"valX\" value =\"1.0\"><br>\n"
                + "    <br>dof      : <input type=\"text\" size=\"10\" name=\"valdof\"><br>"
                + "    <br>esperado : <input type=\"text\" size=\"10\" name=\"valEsperado\"><br>"
                + "    <br>num_seg  : <input type=\"text\" size=\"10\" name=\"valNumSeg\" value =\"10\"><br>"
                + "    <br><input type=\"submit\" value=\"Obtener X\"> </form>"
                + "<p>Se toma como valor constante E = 0.00001</p>");
        pw.write("</html>");

    }

    public static void showResults(HttpServletRequest req, HttpServletResponse resp, 
            double valorX) throws IOException {   
        
        DecimalFormat formatter = new DecimalFormat("#0.0000");
        resp.getWriter().println("<br><hr>");
        resp.getWriter().println("<h2>Resultados</h2>");
        resp.getWriter().println("<b>Valor X:</b>   " + formatter.format(valorX) + "<br>");
        resp.getWriter().println("<hr>");
        resp.getWriter().println("<h2>Test Realizados</h2>");
        resp.getWriter().println("<b>Test1: <br></b>Valor P= 0.20 - Valor dof = 6  - Valor Esperado = 0.55338 <br><br>"
                + "<b>Test2: <br></b>Valor P= 0.45 - Valor dof = 15  - Valor Esperado = 1.75305 <br><br>"
                + "<b>Test3: <br></b>Valor P= 0.495 - Valor dof = 4  - Valor Esperado = 4.60409 <br><br>");
        
    }    
}
