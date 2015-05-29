/*
 * LectureXML.java
 *
 * Created on 5 janvier 2006, 18:26
 *
 * Lecture d'un document XML et transformation en instances Java
 */
package princetonPlainsboro;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Lecture d'un document XML et transformation en instances Java.
 *
 * @author promayon
 */
public class LectureXML {

    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";

    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LectureXML(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public DossierMedical getDossier() {
        DossierMedical dossierCourant = null;
        Date date = null;
        Medecin medecinCourant = null;
        Patient patientCourant = null;
        List<Acte> actes = new Vector<Acte>();
        String donneesCourantes = "";
        String nomCourant = "";
        String prenomCourant = "";
        String telephoneCourant = "";
        Specialite specialiteCourante = null;
        Date dateNaissanceCourante = null;
        String numeroSecuCourant = "";
        String adresseCourante = "";
        String observationsCourantes = "";
        TypeActe typeActeCourant = null;
        Code codeCourant = null;
        int coefCourant = 0;

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("dossiers")) {
                            dossierCourant = new DossierMedical();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (parser.getLocalName().equals("acte")) {
                            actes.add(new Acte(codeCourant, coefCourant, typeActeCourant, observationsCourantes));
                        }
                        if (parser.getLocalName().equals("code")) {
                            codeCourant = getCode(donneesCourantes);
                            if (codeCourant == null) {
                                throw new XMLStreamException("Impossible de trouver le code d'acte = " + donneesCourantes);
                            }
                        }
                        if (parser.getLocalName().equals("coef")) {
                            coefCourant = Integer.parseInt(donneesCourantes);
                        }
                        if (parser.getLocalName().equals("numSecu")) {
                            numeroSecuCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("adresse")) {
                            adresseCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("observations")) {
                            observationsCourantes = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("date")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.indexOf(' ')));
                            int heure = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf(' ') + 1, donneesCourantes.indexOf(':')));
                            int minute = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf(':') + 1, donneesCourantes.length()));

                            date = new Date(jour, mois, annee, heure, minute);
                        }
                        if (parser.getLocalName().equals("naissance")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            dateNaissanceCourante = new Date(jour, mois, annee, null, null);
                        }
                        if (parser.getLocalName().equals("ficheDeSoins")) {
                            FicheDeSoins f = new FicheDeSoins(patientCourant, medecinCourant, date);
                            // ajout des actes
                            for (int i = 0; i < actes.size(); i++) {
                                Acte a = (Acte) actes.get(i);
                                f.ajouterActe(a);
                            }
                            // effacer tous les actes de la liste
                            actes.clear();
                            // ajouter la fiche de soin au dossiers
                            dossierCourant.ajouterFiche(f);
                        }
                        if (parser.getLocalName().equals("medecin")) {
                            medecinCourant = new Medecin(nomCourant, prenomCourant, specialiteCourante, telephoneCourant, "*****");
                        }
                        if (parser.getLocalName().equals("telephone")) {
                            telephoneCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("patient")) {
                            patientCourant = new Patient(nomCourant, prenomCourant, telephoneCourant, dateNaissanceCourante, numeroSecuCourant, adresseCourante);
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("specialite")) {
                            specialiteCourante = getSpecialite(donneesCourantes);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }

        return dossierCourant;
    }

    private static Code getCode(String code) {
        if (code.equals("CS")) {
            return Code.CS;
        }
        if (code.equals("CSC")) {
            return Code.CSC;
        }
        if (code.equals("FP")) {
            return Code.FP;
        }
        if (code.equals("KC")) {
            return Code.KC;
        }
        if (code.equals("KE")) {
            return Code.KE;
        }
        if (code.equals("K")) {
            return Code.K;
        }
        if (code.equals("KFA")) {
            return Code.KFA;
        }
        if (code.equals("KFB")) {
            return Code.KFB;
        }
        if (code.equals("ORT")) {
            return Code.ORT;
        }
        if (code.equals("PRO")) {
            return Code.PRO;
        }
        // probleme : code inconnu
        return null;
    }
    
        private static Specialite getSpecialite(String specialite) {
        if (specialite.equals("Gynecologie")) {
            return Specialite.Gynecologie;
        }
        if (specialite.equals("ORL")) {
            return Specialite.ORL;
        }
        if (specialite.equals("Cardiologie")) {
            return Specialite.Cardiologie;
        }
        if (specialite.equals("Anesthesiologie")) {
            return Specialite.Anesthesiologie;
        }
        if (specialite.equals("Dermatologie")) {
            return Specialite.Dermatologie;
        }
        if (specialite.equals("Gerontologie")) {
            return Specialite.Gerontologie;
        }
        if (specialite.equals("Hematologie")) {
            return Specialite.Hematologie;
        }
        if (specialite.equals("Neurologie")) {
            return Specialite.Neurologie;
        }
        if (specialite.equals("Pediatrie")) {
            return Specialite.Pediatrie;
        }
        if (specialite.equals("Radiologie")) {
            return Specialite.Radiologie;
        }
        if (specialite.equals("Urologie")) {
            return Specialite.Urologie;
        }
        if (specialite.equals("Orthopedie")) {
            return Specialite.Orthopedie;
        }
        if (specialite.equals("Oncologie")) {
            return Specialite.Oncologie;
        }
        if (specialite.equals("Urgences")) {
            return Specialite.Urgences;
        }
        // probleme : code inconnu
        return Specialite.Autre;
    }
}
