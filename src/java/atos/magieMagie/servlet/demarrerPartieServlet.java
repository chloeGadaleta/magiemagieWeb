/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magieMagie.servlet;

import atos.magieMagie.Entity.Carte;
import atos.magieMagie.Entity.Joueur;
import atos.magieMagie.Entity.Partie;
import atos.magieMagie.service.CarteService;
import atos.magieMagie.service.JoueurService;
import atos.magieMagie.service.PartieService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "demarrerPartieServlet", urlPatterns = {"/demarrer-partie"})
public class demarrerPartieServlet extends HttpServlet {

    private PartieService service = new PartieService();
    private JoueurService joueurService = new JoueurService();
    private CarteService carteService = new CarteService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        //On recup la session id partie
        long idPartie =  (long) req.getSession().getAttribute("idPartie");
        
        //On démarre la partie
        // on recupere la session et on recup l'id de la partie
        service.demarrerPartie( idPartie );
        
        // Récup partie par son id pr afficher titre partie
        Partie partie = service.rechercherPartieParId(idPartie);
        req.setAttribute("partie", partie);
        
        // On affiche les joueurs de la partie et leur nbr d ecarte
        List<Object[]> joueursList = joueurService.listerJoueursEtNombreCartes(idPartie);
        req.setAttribute("listeJoueur", joueursList);
        
        //On recup le joueur qui a la main
        Long idJoueur = joueurService.reccupererIdJoueurQuiALaMain(idPartie);
        Joueur joueur = joueurService.rechercherJoueurParId(idJoueur);
        req.setAttribute("joueurMain", joueur);
        
        // On recup les cartes
        List<Carte> carte = carteService.listerCartesJoueur(idJoueur);
        req.setAttribute("listeCarte", carte);
        
        req.getRequestDispatcher("partie.jsp").forward(req, resp);
    }
}
