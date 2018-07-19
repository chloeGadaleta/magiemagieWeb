/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.servlet;

import atos.magieMagie.Entity.Joueur;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "PasserTourServlet", urlPatterns = {"/passer-tour"})
public class PasserTourServlet extends HttpServlet {

    private PartieService service = new PartieService();
    private JoueurService joueurService = new JoueurService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Reccupération de la session de la partie
        long idPartie = (long) req.getSession().getAttribute("idPartie");
        long idJoueur = (long) req.getSession().getAttribute("idJoueur");
        
        req.getAttribute("joueurmain");
        
        service.passerSonTour(idPartie, idJoueur);
        
        req.getRequestDispatcher("partie.jsp").forward(req, resp);
    }
}
