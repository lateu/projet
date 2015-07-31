/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import com.douwe.generic.dao.DataAccessException;
import com.lateu.projet.afriland.entities.Agence;
import com.lateu.projet.afriland.entities.Anomalie;
import com.lateu.projet.afriland.entities.Archivage;
import com.lateu.projet.afriland.entities.Control;
import com.lateu.projet.afriland.entities.DossierCreationCompte;
import com.lateu.projet.afriland.metier.ServiceAgence;
import com.lateu.projet.afriland.metier.ServiceArchivage;
import com.lateu.projet.afriland.metier.ServiceControl;
import com.lateu.projet.afriland.metier.ServiceDossier;
import com.lateu.projet.afriland.metier.ServiceUtilisateur;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
public class DossierCreatinbean {

    @ManagedProperty(value = "#{ServiceDossier}")
    private ServiceDossier serviceDossier;
    @ManagedProperty(value = "#{ServiceUtilisateur}")
    private ServiceUtilisateur serviceUtilisateur;
    @ManagedProperty(value = "#{ServiceControl}")
    private ServiceControl serviceControl;
    @ManagedProperty(value = "#{ServiceAgence}")
    private ServiceAgence serviceAgence;
    @ManagedProperty(value = "#{ServiceArchivage}")
    private ServiceArchivage serviceArchivage;
    Archivage archivageselect = new Archivage();
    private String codeAgence;
    private Anomalie anomalieSelect = new Anomalie();
    private DossierCreationCompte dossierCreatinCompteSelect = new DossierCreationCompte();
    private List<DossierCreationCompte> comptes;
    private List<DossierCreationCompte> traces;
    private List<DossierCreationCompte> accueil;
    private List<Anomalie> anomalies;
    private List<Control> controls;
    private String motif;
    private Date debut;
    private Date fin;
    private String compteFin;
    private int nbCirculation;

    public DossierCreatinbean() {
    }
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user.getUsername();
    String role;

    public void saveDossier() {

        // dossierCreatinCompteSelect.setDateMiseEnCirculation(new Date());


        if (dossierCreatinCompteSelect.getDateCreation() == null) {
            dossierCreatinCompteSelect.setDateCreation(new Date());
        }

        Agence a = serviceAgence.findAgenceByUsername(name);
        // dossierCreatinCompteSelect.setDateCreation(new Date());

        if (dossierCreatinCompteSelect.getClient().length() == 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " dossier  vide", ""));
        } else {
            serviceDossier.create(dossierCreatinCompteSelect, a);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " dossier ajouté", ""));

        }

    }

    public void createAnomalie() {
        if (name != null) {
            anomalieSelect.setMotif(motif);
            anomalieSelect.setPersonneInit(name);
            anomalieSelect.setPersonneCor("Inconnu");
            anomalieSelect.setDateOp(new Date());
            anomalieSelect.setDateCo(null);
            anomalieSelect.setSolution(".");
            serviceDossier.createAnomalie(anomalieSelect, dossierCreatinCompteSelect.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " anomalie Enregistrée", ""));


        }
    }

    public void annulerAnomalie() {
        if (name != null) {
            if(motif.length()!=0){
                anomalieSelect.setSolution(motif);
            serviceDossier.corrigerAnomalie(anomalieSelect, name);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " anomalie corrigée", ""));
            }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "le motif est obligatoire", ""));
         
            }
        }
    }

    public void ok() {
        if (name != null) {
            Control c = new Control();
            serviceControl.Savecontrole(c, name, dossierCreatinCompteSelect.getId());
            // System.out.println("=======" + dossierCreatinCompteSelect);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", ""));

        }
    }

    public ServiceDossier getServiceDossier() {
        return serviceDossier;
    }

    public void setServiceDossier(ServiceDossier serviceDossier) {
        this.serviceDossier = serviceDossier;
    }

    public DossierCreationCompte getDossierCreatinCompteSelect() {
        return dossierCreatinCompteSelect;
    }

    public void setDossierCreatinCompteSelect(DossierCreationCompte dossierCreatinCompteSelect) {
        this.dossierCreatinCompteSelect = dossierCreatinCompteSelect;
    }

    public List<DossierCreationCompte> getComptes() {

        role = serviceUtilisateur.redirection(name).getAutorite();
           String codeAgence=serviceAgence.findAgenceByUsername(name).getCode();
        if (role != null) {
            if (role.equals("ROLE_CA") == true) {

                comptes = serviceDossier.findForCA(codeAgence);
                System.out.println("nombre de dossier CA"+comptes.size());
            } else if (role.equals("ROLE_JR") == true) {

                comptes = serviceDossier.findForCJ(codeAgence);

            } else if (role.equals("ROLE_INF") == true) {

                comptes = serviceDossier.findForINF(codeAgence);

            } else if (role.equals("ROLE_AR") == true) {
                comptes = serviceDossier.findAR(codeAgence);

            }


            return comptes;
        }
        return null;
    }

    public String AnomalieWarning() {
        List<Anomalie> an = serviceDossier.findAllAnomalie();
        for (Anomalie anomalie : an) {
            if (anomalie.getPersonneCor().equals("Inconnu") == true) {
                return "Warning";
            }
        }
        return null;

    }

    public String testAnomalie(Anomalie anomalie) {
        // List<Anomalie> an = serviceDossier.findAllAnomalie();

        if (anomalie.getPersonneCor().equals("Inconnu") == true) {
            return "signal";
        }

        return null;

    }

    public void setComptes(List<DossierCreationCompte> comptes) {
        this.comptes = comptes;
    }

    public Anomalie getAnomalieSelect() {
        return anomalieSelect;
    }

    public void setAnomalieSelect(Anomalie anomalieSelect) {
        this.anomalieSelect = anomalieSelect;
    }

    public List<Anomalie> getAnomalies() {
        if (!"".equals(name)) {
            // String codeAgence=serviceAgence.findAgenceByUsername(name).getCode();
            return anomalies = serviceDossier.findAllAnomalie();
        }
        return null;
    }

    public void setAnomalies(List<Anomalie> anomalies) {
        this.anomalies = anomalies;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceUtilisateur getServiceUtilisateur() {
        return serviceUtilisateur;
    }

    public void setServiceUtilisateur(ServiceUtilisateur serviceUtilisateur) {
        this.serviceUtilisateur = serviceUtilisateur;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ServiceControl getServiceControl() {
        return serviceControl;
    }

    public void setServiceControl(ServiceControl serviceControl) {
        this.serviceControl = serviceControl;
    }

    public ServiceAgence getServiceAgence() {
        return serviceAgence;
    }

    public void setServiceAgence(ServiceAgence serviceAgence) {
        this.serviceAgence = serviceAgence;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
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

    public List<DossierCreationCompte> getTraces() {
        return traces = serviceDossier.findAll();
    }

    public void setTraces(List<DossierCreationCompte> traces) {
        this.traces = traces;
    }

    public List<Control> getControls() {
        return controls = serviceControl.findControl();
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }

    public String getCompteFin() {
        return compteFin;
    }

    public void setCompteFin(String compteFin) {
        this.compteFin = compteFin;
    }

    public List<DossierCreationCompte> getAccueil() {
        return accueil = serviceDossier.findForAccueil();
    }

    public void setAccueil(List<DossierCreationCompte> accueil) {
        this.accueil = accueil;
    }

    public void mettreEnCirculation() {
        List<DossierCreationCompte> l = serviceDossier.findForAccueil();
        for (DossierCreationCompte d : l) {

            d.setPas1("ok");
            d.setPas2("En cours");
            serviceDossier.Circuler(d);
        }

    }

    public void update() {
        if (name != null) {
            Agence a = serviceAgence.findAgenceByUsername(name);
            dossierCreatinCompteSelect.setAgence(a);
            dossierCreatinCompteSelect.setPas5("En cours");
            dossierCreatinCompteSelect.setPas1("0");
            dossierCreatinCompteSelect.setPas2("0");
            dossierCreatinCompteSelect.setPas3("0");
            dossierCreatinCompteSelect.setPas4("0");
            serviceDossier.Update(dossierCreatinCompteSelect);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " dossier modifié", ""));

        }
    }

    
    
    
    
    public ServiceArchivage getServiceArchivage() {
        return serviceArchivage;
    }

    public void setServiceArchivage(ServiceArchivage serviceArchivage) {
        this.serviceArchivage = serviceArchivage;
    }

//    public void importationDossierExcel(FileUploadEvent event) throws DataAccessException {
//
//        // Agence a=serviceAgence.findAgenceByUsername(name);
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//int cpt=0;
//        if (event.getFile() == null) {
//            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le fichier est vide", ""));
//        }
//
//        InputStream file;
//        HSSFWorkbook workbook = null;
//        try {
//            file = event.getFile().getInputstream();
//            workbook = new HSSFWorkbook(file);
//        } catch (IOException e) {
//            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de la lecture du fichier" + e, ""));
//        }
//
//        HSSFSheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> rowIterator = sheet.iterator();
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            System.out.println("Ligne numero: " + row.getRowNum());
//            if (row.getRowNum() == 0) {
//                continue;
//            }
//
//
//            Iterator<Cell> cellIterator = row.cellIterator();
//            archivageselect = new Archivage();
//
//            String compte = null;
//            String client = null;
//            String type = null;
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                System.out.println("colonne numero: " + cell.getColumnIndex() + "  " + cell);
//                switch (cell.getColumnIndex()) {
//                    case 0:
//                        //  compte = cell.getStringCellValue();
//                        archivageselect.setCompte(cell.getStringCellValue());
//                        break;
//
//                    case 1:
//                        //  client = cell.getStringCellValue();
//                        archivageselect.setClient(cell.getStringCellValue());
//                        break;
//
//
//                    default:
//                }
//
//            }
//
//            archivageselect.setPosition("present");
//            archivageselect.setTypeDocument("Dossier-Compte");
//
//            serviceArchivage.create(archivageselect, serviceAgence.findAgenceByUsername(name));
//
//        }
//        
//              
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " "+cpt+" lignes enregistrées", ""));
//  
//    }

    public void handleFileUpload(FileUploadEvent event) throws DataAccessException, FileNotFoundException, IOException, ParseException {
        InputStream file = event.getFile().getInputstream();

        Scanner scanner = new Scanner(file);
        // Agence a = new Agence();
int cpt=0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            archivageselect = new Archivage();
            if (line.length() != 0) {
                //       int deb = 0, fin = 0;
                String agence = null;
                String compte = null;
                String cle = null;
                String date = null;
                String nom = null;
                String dt = null;
                int l = line.length();
                for (int i = 0; i < l; i++) {

                    agence = line.substring(0, 5);
                    compte = line.substring(6, 17);
                    cle = line.substring(18, 20);
                    nom = line.substring(21, l - 12);
                    date = line.substring(l - 11, l - 1);

                }
                archivageselect.setClient(nom);
                archivageselect.setPosition("present");
                archivageselect.setCompte(compte);
                archivageselect.setTypeDocument("Dossier-Compte");
                //Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                //doc.setDateCreation(d);
                //System.out.println("agence= " + agence + " compte= " + compte + " cle=" + cle + "nom= " + nom);

                serviceArchivage.create(archivageselect, serviceAgence.findByCode(agence));
                cpt++;

            }
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " "+cpt+" lignes enregistrées", ""));
        scanner.close();



    }

    
    
      public void pourControl(FileUploadEvent event) throws DataAccessException, FileNotFoundException, IOException, ParseException {
        InputStream file = event.getFile().getInputstream();

        Scanner scanner = new Scanner(file);
        // Agence a = new Agence();
     int cpt=0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            dossierCreatinCompteSelect=new DossierCreationCompte();
            if (line.length() != 0) {
                //       int deb = 0, fin = 0;
                String agence = null;
                String compte = null;
                String cle = null;
                String date = null;
                String nom = null;
                String dt = null;
                int l = line.length();
                for (int i = 0; i < l; i++) {

                    agence = line.substring(0, 5);
                    compte = line.substring(6, 17);
                    cle = line.substring(18, 20);
                    nom = line.substring(21, l - 12);
                    date = line.substring(l - 11, l - 1);

                }
                dossierCreatinCompteSelect.setClient(nom);
                dossierCreatinCompteSelect.setCle(cle);
                dossierCreatinCompteSelect.setCompte(compte);
              
                Date d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                  dossierCreatinCompteSelect.setDateCreation(d);
                //System.out.println("agence= " + agence + " compte= " + compte + " cle=" + cle + "nom= " + nom);
                  
                serviceDossier.create(dossierCreatinCompteSelect, serviceAgence.findByCode(agence));
               // serviceArchivage.create(dossierCreatinCompteSelect, serviceAgence.findByCode(agence));
                cpt++;

            }
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " "+cpt+" dossiers enregistrés", ""));
        scanner.close();



    }

    
    
    public Archivage getArchivageselect() {
        return archivageselect;
    }

    public void setArchivageselect(Archivage archivageselect) {
        this.archivageselect = archivageselect;
    }
    JasperPrint jasperPrint, jasperPrint2;

    public void init() throws JRException, DataAccessException {

        List<DossierCreationCompte> l;
        //l.add(serviceCreditScolaire.rapport(serviceCreditScolaire.findByAgence(codeAgenece)));
        l = serviceDossier.findByDossierPeriode(debut, fin);
        // System.out.println("=="+l);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(l);
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rapport/ComptePeriodique.jasper");
        jasperPrint = JasperFillManager.fillReport(path, new HashMap(), beanCollectionDataSource);
    }

    public void pdf() throws JRException, IOException, DataAccessException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("content-disposition", "attachment;filename=ListeCompte.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    }

    public int getNbCirculation() {
        return nbCirculation=accueil.size();
    }

    public void setNbCirculation(int nbCirculation) {
        this.nbCirculation = nbCirculation;
    }
    
    
    
    
     public void rappelJuridique(){
        
       List<DossierCreationCompte> l = serviceDossier.findForAccueil();
       
       int cpt=0;
       Long id;
         for (DossierCreationCompte dossierCredit : l) {
             id=dossierCredit.getId();
             System.out.println("=================...rappel......====================="+id);
             serviceDossier.deletedossierJuridiqu(id);
             cpt++;
         }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " " + cpt + "  annulations", ""));

     }
    
    
}
