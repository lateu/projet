/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.metierImpl;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.dao.CreditScolairedao;
import com.lateu.projet.afriland.dao.Userdao;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.CreditScolaire;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceCreditScolaire;
import com.lateu.projet.afriland.projection.projectionCredit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lateu
 */
@Transactional
public class ServiceCreditScolaireImpl implements ServiceCreditScolaire {

    private CreditScolairedao scolairedao;
    private Userdao userdao;

    public CreditScolairedao getScolairedao() {
        return scolairedao;
    }

    public void setScolairedao(CreditScolairedao scolairedao) {
        this.scolairedao = scolairedao;
    }

    @Override
    public void create(CreditScolaire cs, String usr, Agence a) throws DataAccessException {
        Utilisateur u = userdao.findbyUsername(usr);
        cs.setUtilisateur(u);
        cs.setAgence(a);
        scolairedao.create(cs);

    }

    @Override
    public List<CreditScolaire> findAll() throws DataAccessException {
        return scolairedao.findAll();
    }

    public Userdao getUserdao() {
        return userdao;
    }

    public void setUserdao(Userdao userdao) {
        this.userdao = userdao;
    }

    @Override
    public int DocNumber(List<CreditScolaire> liste) throws DataAccessException {

        return liste.size();
    }

    @Override
    public int VolumeDemande(List<CreditScolaire> liste) throws DataAccessException {
        int r = 0;

        for (CreditScolaire creditScolaire : liste) {
            r += creditScolaire.getMontantDemande();
        }
        return r;
    }

    @Override
    public int VolumeAccorde(List<CreditScolaire> liste) throws DataAccessException {
        int tmp = 0;

        for (CreditScolaire creditScolaire : liste) {
            tmp += creditScolaire.getMontantAccordé();
        }
        return tmp;
    }

    @Override
    public int ReclamationNumber(List<CreditScolaire> liste) throws DataAccessException {
        int cpt = 0;
        for (CreditScolaire creditScolaire : liste) {
            String s =null; //creditScolaire.getReclammation();
            if (s != null) {
                cpt++;
            }

            // System.out.println("=================="+s);
        }
        return cpt;

    }

    @Override
    public int Assurence(List<CreditScolaire> liste) throws DataAccessException {
        int t = 0;
        for (CreditScolaire creditScolaire : liste) {

            t += creditScolaire.getContratAssurance();

        }
        return t;
    }

    @Override
    public int TraiterAtemps(List<CreditScolaire> liste) throws DataAccessException {
        int r = 0;
        for (CreditScolaire creditScolaire : liste) {
            if (creditScolaire.getDateDecisionFinale() == null) {
            } else {
                if (((creditScolaire.getDateDecisionFinale().getTime() - creditScolaire.getDateRecption().getTime()) / 86400000) < 2) {
                    r++;
                }
            }
        }
        return r;
    }

    @Override
    public projectionCredit rapport(List<CreditScolaire> liste) {
        try {
            projectionCredit nouveau = new projectionCredit();
            int n = DocNumber(liste);
            int tt = TraiterAtemps(liste);
            int ac = VolumeAccorde(liste);
            int dm = VolumeDemande(liste);
            int as = Assurence(liste);
            nouveau.setAccord(ac);
            nouveau.setDemande(dm);
            nouveau.setNombre(n);
            nouveau.setTemps(tt);
            nouveau.setAssurance(as);
            return nouveau;
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceCreditScolaireImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void upDateCreditS(CreditScolaire cs) throws DataAccessException {
        scolairedao.update(cs);
    }

    @Override
    public CreditScolaire findClient(String a, String b) throws DataAccessException {

        return scolairedao.findByNameCode(a, b);
    }

//    @Override
//    public Long LastIndex(List<CreditScolaire> creditScolaires) {
//        int i = creditScolaires.size();
//        if (i == 0) {
//            return 1L;
//
//        } else {
//            return creditScolaires.get(i - 1).getId() + 1;
//        }
//
    //   }
    @Override
    public int nbreDocValider(List<CreditScolaire> liste) throws DataAccessException {
        int c = 0;
        for (CreditScolaire creditScolaire : liste) {
            if (creditScolaire.getValider().equals("ok")) {
                c++;
            }
        }
        return c;
    }

    @Override
    public int nbreDocEncours(List<CreditScolaire> liste) throws DataAccessException {
        int res = 0;
        for (CreditScolaire creditScolaire : liste) {
            if (creditScolaire.getValider().equals("en cours...")) {
                res++;
            }
        }
        return res;
    }

    @Override
    public void DeleteCredit(Long id) {
        try {
            scolairedao.delete(scolairedao.findById(id));
        } catch (DataAccessException ex) {
            Logger.getLogger(ServiceCreditScolaireImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CreditScolaire findById(Long id) throws DataAccessException {

        return scolairedao.findById(id);
    }

    @Override
    public List<CreditScolaire> findByAgence(String code) throws DataAccessException {
        return scolairedao.findByAgence(code);
    }
}
