/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.CreditScolaire;
import com.lateu.projet.afriland.entities.Poubelle;
import com.lateu.projet.afriland.entities.Utilisateur;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceCreditScolaire;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import com.lateu.projet.afriland.projection.projectionCredit;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.FileUploadEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author lateu
 */
@ManagedBean
@RequestScoped
public class CreditScolairebean implements Serializable {

    @ManagedProperty(value = "#{ServiceCreditScolaire}")
    private ServiceCreditScolaire serviceCreditScolaire;
    @ManagedProperty(value = "#{ServiceUtilisateur}")
    private ServiceUtilisateur serviceUtilisateur;
    @ManagedProperty(value = "#{ServiceAgence}")
    private ServiceAgence serviceAgence;
   // @ManagedProperty(value = "#{ServicePoubelle}")
   // private ServicePoubelle  servicePouPelle;
    private CreditScolaire creditScolaireSelect = new CreditScolaire();
    public List<CreditScolaire> creditScolaires;
    private String username;
    private String motifSuppression;
    private String codeAgenece;

    public CreditScolairebean() {
    }
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();
    // String name ="lateu";

    public void delete() {
  
        if (name != null) {

            if (motifSuppression.length()==0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " le motif de suppression est obligatoire", ""));
            } else {
                Poubelle pb = new Poubelle();
                pb.setMotif(motifSuppression);
                pb.setInfoSuppr("compte=" + creditScolaireSelect.getCompte() + "  client=" + creditScolaireSelect.getClient());
                Utilisateur usr=serviceUtilisateur.findByUsername(name);
                 serviceUtilisateur.creerPoubelle(pb,usr);
                serviceCreditScolaire.DeleteCredit(creditScolaireSelect.getId());
                System.out.println("====="+creditScolaireSelect.getId());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "dossier supprimé avec succés", ""));
            }
        }
    }

    public void create() throws DataAccessException {

        if (name != null) {

            Utilisateur e = serviceUtilisateur.findByUsername(name);
            Agence a = serviceAgence.findAgenceByUsername(name);
            creditScolaireSelect.setUtilisateur(e);
            //creditScolaireSelect.setDateRecption(new Date());
            //   creditScolaireSelect.setCode(buildCodeAp(new Date(), creditScolaireSelect.getCode()));
            creditScolaireSelect.setValider("en cours...");
            // creditScolaireSelect.setArchiver(0);
            if (creditScolaireSelect.getDateRecption() == null) {
                creditScolaireSelect.setDateRecption(new Date());
                serviceCreditScolaire.create(creditScolaireSelect, e.getUsername(), a);
                System.out.println("===" + creditScolaireSelect);
            } else {
                //  System.out.println("================2=============");
                serviceCreditScolaire.create(creditScolaireSelect, e.getUsername(), a);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " la demande a été sauvegardé", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " vous n'etes pas connecté SVP!", ""));
        }

    }

    /**
     * on vide ou on rejete un dossier de credit scolaire
     *
     * @throws DataAccessException
     */
    public void decision() throws DataAccessException {
//        //        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // String name = user.getUsername();
        if (name != null) {


            // System.out.println("==" + creditScolaireSelect);
            /**
             * on charge la demande consernée prealablement enregistrée
             */
            CreditScolaire c = serviceCreditScolaire.findById(creditScolaireSelect.getId());
            /**
             * il s'agit d'une validation de la demande
             */
            if (creditScolaireSelect.getValider().equals("ok") == true) {

                /**
                 * si le montant accordée est superieure à la demande
                 */
                creditScolaireSelect.setCommission(0.01);
                if (c.getMontantDemande() < creditScolaireSelect.getMontantAccordé()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "le montant accordé est supérieur à la demande", ""));
                } else {
                    /**
                     * si la commission est nulle
                     */
                    if (creditScolaireSelect.getMontantAccordé() == 0) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "on ne peut valider un dossier avec un montant accordé=0", ""));
                    } else {
                        /**
                         * commission non nulle
                         */
                        c.setValider(creditScolaireSelect.getValider());
                        c.setMontantAccordé(creditScolaireSelect.getMontantAccordé());
                        c.setValider(creditScolaireSelect.getValider());
                        c.setCommission(creditScolaireSelect.getCommission());
                        c.setValideur(name);
                        c.setContratAssurance(creditScolaireSelect.getContratAssurance());
                        if (creditScolaireSelect.getDateDecisionFinale() == null) {
                            //creditScolaireSelect.setDateDecisionFinale(new Date());
                            c.setDateDecisionFinale(new Date());
                            serviceCreditScolaire.upDateCreditS(c);
                        } else {
                            c.setDateDecisionFinale(creditScolaireSelect.getDateDecisionFinale());
                            serviceCreditScolaire.upDateCreditS(c);
                        }
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "la demande de Mr " + c.getClient() + " a été validée", ""));
                    }

                }


                /**
                 * rejet d'une demande
                 */
            } else if (creditScolaireSelect.getValider().equals("rejeté") == true) {
                System.out.println("==" + creditScolaireSelect.getValider());
                serviceCreditScolaire.upDateCreditS(c);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "la demande de Mr" + c.getClient() + "a été rejeté", ""));
            } else {
                /**
                 * formulaire mal renseigné
                 */
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "le formulaire est mal rempli svp!", ""));
                System.out.println(" mauvais choix");
            }




        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, " vous n'etes pas connecté SVP!", ""));

        }
    }

    public String buildCodeAp(Date d, String chaine) {
        String s;
        SimpleDateFormat tmp = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
        s = tmp.format(dt);
        s = s.substring(8, 10) + "" + chaine;
        return s;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ServiceCreditScolaire getServiceCreditScolaire() {
        return serviceCreditScolaire;
    }

    public void setServiceCreditScolaire(ServiceCreditScolaire serviceCreditScolaire) {
        this.serviceCreditScolaire = serviceCreditScolaire;
    }

    public CreditScolaire getCreditScolaireSelect() {
        return creditScolaireSelect;
    }

    public void setCreditScolaireSelect(CreditScolaire creditScolaireSelect) {
        this.creditScolaireSelect = creditScolaireSelect;
    }

    public List<CreditScolaire> getCreditScolaires() throws DataAccessException {
        if (name != null) {
           // String codeAgence = serviceAgence.findAgenceByUsername(name).getCode();
            return creditScolaires = serviceCreditScolaire.findAll();
        } else {
            return null;
        }
    }

    public void setCreditScolaires(List<CreditScolaire> creditScolaires) {
        this.creditScolaires = creditScolaires;
    }

    public ServiceUtilisateur getServiceUtilisateur() {
        return serviceUtilisateur;
    }

    public void setServiceUtilisateur(ServiceUtilisateur serviceUtilisateur) {
        this.serviceUtilisateur = serviceUtilisateur;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public JasperPrint getJasperPrint2() {
        return jasperPrint2;
    }

    public void setJasperPrint2(JasperPrint jasperPrint2) {
        this.jasperPrint2 = jasperPrint2;
    }
    JasperPrint jasperPrint, jasperPrint2;

    public void init() throws JRException, DataAccessException {
//        String s;
//        SimpleDateFormat tmp = new SimpleDateFormat("dd-MM-yyyy");
//        Date dt = new Date();
//        s = tmp.format(dt);
//        s = s.substring(8, 10);
//
//        String newCode = s + codeAgenece;
        List<projectionCredit> l = new ArrayList<projectionCredit>();
        l.add(serviceCreditScolaire.rapport(serviceCreditScolaire.findByAgence(codeAgenece)));
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(l);
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/statistique.jasper");
        jasperPrint = JasperFillManager.fillReport(path, new HashMap(), beanCollectionDataSource);
    }

    public void pdf() throws JRException, IOException, DataAccessException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("content-disposition", "attachment;filename=statistique.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    }

    public void init2() throws JRException, DataAccessException {
//        String s;
//        SimpleDateFormat tmp = new SimpleDateFormat("dd-MM-yyyy");
//        Date dt = new Date();
//        s = tmp.format(dt);
//        s = s.substring(8, 10);
//
//        String newCode = s + codeAgenece;
        List<CreditScolaire> all = serviceCreditScolaire.findByAgence(codeAgenece);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(all);
        //  String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/ToutesLesDemandes.jasper");
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/allCredit.jasper");
        jasperPrint2 = JasperFillManager.fillReport(path, new HashMap(), beanCollectionDataSource);
    }

    public void pdfAll() throws JRException, IOException, DataAccessException {
        init2();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("content-disposition", "attachment;filename=ListeDemandesCreditScolaire.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint2, servletOutputStream);
    }

    public void handleFileUpload(FileUploadEvent event) throws DataAccessException {

        //    System.out.println("=====================================.............====================");
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (event.getFile() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le fichier est vide", ""));
        }

        InputStream file;
        HSSFWorkbook workbook = null;
        try {
            file = event.getFile().getInputstream();
            workbook = new HSSFWorkbook(file);
        } catch (IOException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la lecture du fichier" + e, ""));
        }

        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println("Ligne numero: " + row.getRowNum());
            if (row.getRowNum() == 0) {
                continue;
            }


            Iterator<Cell> cellIterator = row.cellIterator();
            creditScolaireSelect = new CreditScolaire();

            String code = null;
            String nomClient = null;
            int montantDemande = 0;
            String compte = null;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println("colonne numero: " + cell.getColumnIndex() + "  " + cell);
                switch (cell.getColumnIndex()) {
                    case 0:
//                        String matricule = cell.getStringCellValue();
//                        if (matricule.toCharArray().length == 0) {
//                            mat_etud = "XXXX" + row.getRowNum();
//                            etudiant.setMatricule("XXXX" + row.getRowNum());
//                        } else {
//                            mat_etud = matricule;
//                            etudiant.setMatricule(cell.getStringCellValue());

                        creditScolaireSelect.setClient(cell.getStringCellValue());

                        break;

                    case 1:
//                        String nom = cell.getStringCellValue();
//                        if (nom.equals("")) {
//                            etudiant.setNom("");
//                            //continue;
//                        } else {
//                            etudiant.setNom(cell.getStringCellValue());
//                        }
                        // cell.getStringCellValue().;
                        creditScolaireSelect.setMontantDemande(1000);
                        break;
                    case 2:
//                        String date_naiss = null;
//                        if ((cell != null)) {
//                            switch (cell.getCellType()) {
//                                case Cell.CELL_TYPE_STRING:
//                                    date_naiss = cell.getStringCellValue();
//                                    if (date_naiss.equals("")) {
//                                        etudiant.setDate_naiss("");
//                                        //continue;
//                                    } else {
//                                        etudiant.setDate_naiss(cell.getStringCellValue());
//                                    }
                        // creditScolaireSelect.setCode(cell.getStringCellValue());

                        break;
                    case 3:

                        creditScolaireSelect.setCompte(cell.getStringCellValue());
                        break;
                    default:
                }

            }
            creditScolaireSelect.setDateRecption(new Date());
//            creditScolaireSelect.setCode(buildCodeAp(new Date(), creditScolaireSelect.getCode()));
            creditScolaireSelect.setValider("en cours...");
            // creditScolaireSelect.setArchiver(0);
              serviceCreditScolaire.create(creditScolaireSelect, name,serviceAgence.findAgenceByUsername(name));
        }

    }

    public String getMotifSuppression() {
        return motifSuppression;
    }

    public void setMotifSuppression(String motifSuppression) {
        this.motifSuppression = motifSuppression;
    }

    public String getCodeAgenece() {
        return codeAgenece;
    }

    public void setCodeAgenece(String codeAgenece) {
        this.codeAgenece = codeAgenece;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceAgence getServiceAgence() {
        return serviceAgence;
    }

    public void setServiceAgence(ServiceAgence serviceAgence) {
        this.serviceAgence = serviceAgence;
    }
    
    
    
    
    
}
