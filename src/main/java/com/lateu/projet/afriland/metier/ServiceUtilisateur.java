/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metier;

import com.lateu.projet.afriland.entities.Poubelle;
import com.lateu.projet.afriland.entities.Service;
import com.lateu.projet.afriland.entities.UserAutority;
import com.lateu.projet.afriland.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author lateu
 */
public interface ServiceUtilisateur {

    public Utilisateur findByUsername(String usename);

    public Utilisateur findById(Long id);

    public UserAutority redirection(String usename);

    public void create(Utilisateur utilisateur, String autority, String nomService);

    public void update(Utilisateur u, String autority, String nomService);

    public void ChangePassword(Utilisateur u);

    public List<Utilisateur> findAll();

    public List<Utilisateur> findMe(String username, String password);

    public List<Utilisateur> findUserByAgence(String codeAgence);

    public List<Utilisateur> TestUsername(String username);

    public List<Poubelle> findAllPoubelle();

    public UserAutority findByUserId(Long id);

    public void delete(Long id);

    public void deleteMonRole(String username);

    public void deleteCreditScolaire(Long id, Poubelle p, String username);

    public void creerPoubelle(Poubelle pb, Utilisateur u);

    public List<Utilisateur> findUserByService(String nom);

    public void InitPassord(String username);

    public void createPoubelle(Poubelle p, Utilisateur u);
}
