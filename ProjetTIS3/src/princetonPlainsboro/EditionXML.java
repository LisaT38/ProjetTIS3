/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro;

import java.io.*;
import java.util.List;
import java.util.Vector;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 *
 * @author Lisa
 */
public class EditionXML {

    static Element racine;
    static org.jdom.Document document;

    public void ajouterFicheDeSoins(FicheDeSoins f) throws JDOMException, IOException {

        SAXBuilder sxb = new SAXBuilder();

        //initialisation du document et de l element racine
        document = sxb.build(new File("src/donnees/dossier.xml"));
        racine = document.getRootElement(); // racine = noeud dossier

        //ajout d'un noeud ficheDeSoins à la racine
        Element fiche = new Element("ficheDeSoins");
        racine.addContent(fiche);

        //ajout de la date
        Element date = new Element("date");
        date.setText(f.getDate().toString());
        fiche.addContent(date);

        //ajout des informations du médecin
        Element medecin = new Element("medecin");
        fiche.addContent(medecin);

        Element nom = new Element("nom");
        nom.setText(f.getMedecin().getNom());
        medecin.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(f.getMedecin().getPrenom());
        medecin.addContent(prenom);

        Element specialite = new Element("specialite");
        specialite.setText(f.getMedecin().getSpecialite().toString());
        medecin.addContent(specialite);

        Element telephone = new Element("telephone");
        telephone.setText(f.getMedecin().getTelephone());
        medecin.addContent(telephone);

        //ajout des informations du patient
        Element patient = new Element("patient");
        fiche.addContent(patient);

        nom.setText(f.getPatient().getNom());
        patient.addContent(nom);

        prenom.setText(f.getPatient().getPrenom());
        patient.addContent(prenom);

        Element naissance = new Element("naissance");
        naissance.setText(f.getPatient().getDateNaissance().toString());
        patient.addContent(naissance);

        telephone.setText(f.getPatient().getTelephone());
        patient.addContent(telephone);

        Element adresse = new Element("adresse");
        adresse.setText(f.getPatient().getAdresse());
        patient.addContent(adresse);

        Element numeroSecu = new Element("numeroSecu");
        numeroSecu.setText(f.getPatient().getNumeroSecu());
        patient.addContent(numeroSecu);

        //ajout des actes
        Vector<Acte> actes = f.getActes();
        for (int j = 0; j < actes.size(); j++) {

            Element acte = new Element("acte");
            fiche.addContent(acte);

            Element code = new Element("code");
            code.setText(actes.elementAt(j).getCode().getLibelle());
            acte.addContent(code);

            Element coef = new Element("coef");
            coef.setText(String.valueOf(actes.elementAt(j).getCoef()));
            acte.addContent(coef);

            Element observations = new Element("observations");
            observations.setText(f.getActes().elementAt(j).getObservations());
            acte.addContent(observations);

        }

        XMLOutputter sortie = new XMLOutputter();
        sortie.output(document, new FileOutputStream("src/donnees/dossier.xml"));

    }

    public void ajouterPatient(Patient p) throws JDOMException, IOException {

        SAXBuilder sxb = new SAXBuilder();

        //initialisation du document et de l element racine
        document = sxb.build(new File("src/donnees/listePatients.xml"));
        racine = document.getRootElement(); // racine = noeud listePatients

        //ajout d'un noeud patient à la racine
        Element patient = new Element("patient");
        racine.addContent(patient);

        Element nom = new Element("nom");
        nom.setText(p.getNom());
        patient.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(p.getPrenom());
        patient.addContent(prenom);

        Element naissance = new Element("naissance");
        naissance.setText(p.getDateNaissance().toString());
        patient.addContent(naissance);

        Element telephone = new Element("telephone");
        telephone.setText(p.getTelephone());
        patient.addContent(telephone);

        Element adresse = new Element("adresse");
        adresse.setText(p.getAdresse());
        patient.addContent(adresse);

        Element numeroSecu = new Element("numeroSecu");
        numeroSecu.setText(p.getNumeroSecu());
        patient.addContent(numeroSecu);

        XMLOutputter sortie = new XMLOutputter();
        sortie.output(document, new FileOutputStream("src/donnees/listePatients.xml"));
    }

    public void ajouterMedecin(Medecin m) throws JDOMException, IOException {

        SAXBuilder sxb = new SAXBuilder();

        //initialisation du document et de l element racine
        document = sxb.build(new File("src/donnees/listeMedecins.xml"));
        racine = document.getRootElement(); // racine = noeud listeMedecins

        //ajout d'un noeud medecin a la racine
        Element medecin = new Element("medecin");
        racine.addContent(medecin);

        Element nom = new Element("nom");
        nom.setText(m.getNom());
        medecin.addContent(nom);

        Element prenom = new Element("prenom");
        prenom.setText(m.getPrenom());
        medecin.addContent(prenom);

        Element specialite = new Element("specialite");
        specialite.setText(m.getSpecialite().toString());
        medecin.addContent(specialite);

        Element telephone = new Element("telephone");
        telephone.setText(m.getTelephone());
        medecin.addContent(telephone);

        XMLOutputter sortie = new XMLOutputter();
        sortie.output(document, new FileOutputStream("src/donnees/listeMedecins.xml"));

    }

}
