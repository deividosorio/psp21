package edu.uniandes.ecos.controlador;

import edu.uniandes.ecos.modelo.BuscadorX;
import edu.uniandes.ecos.vista.MainView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/*/**
 * Clase que representa el controlador de la aplicación 
 * que comunica la interface con el mundo
 * @author deividosorio
 * @version 02/03/2015
 */
public class App extends HttpServlet{
    
    public static void main(String[] args) {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /**
     * Metodo que recibe información de las clases y retorna el valor de P
     *
     * @param req de Input
     * @param resp de inpunt
     * @throws IOException para validación de error de IO
     */
    public void consoleInput(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        double valP = Double.parseDouble(req.getParameter("valP"));
        double valdof = Double.parseDouble(req.getParameter("valdof"));
        double valEsperado = Double.parseDouble(req.getParameter("valdoEsperado"));
        int valNumSeg = Integer.parseInt(req.getParameter("valNumSeg"));

        BuscadorX buscador = new BuscadorX(valP, valdof, valEsperado, valNumSeg);
        double valX = buscador.encontrarX();   
        MainView.showResults(req, resp, valX);
    }
    
        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        MainView.showHome(req, resp);
        MainView.showResults(req, resp, 0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            MainView.showHome(req, resp);
            consoleInput(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
