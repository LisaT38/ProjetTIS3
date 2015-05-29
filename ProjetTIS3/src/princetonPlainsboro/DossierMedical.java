package princetonPlainsboro;

import java.util.List;
import java.util.Vector;

class DossierMedical {

    private List<FicheDeSoins> fiches;     // contient des objets de classe 'FicheDeSoins'

    public DossierMedical() {
        fiches = new Vector<FicheDeSoins>();  // liste vide
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.add(fiche);
    }

    public void afficher() {
        System.out.println("Dossier medical informatise :");
        System.out.println("-----------------------------");
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            f.afficher();
            // pour separer les fiches de soins :
            System.out.println("--------------------------------------");
        }
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public void afficherListePatients(Medecin m) {
        System.out.println("> liste des patients du " + m.toString() + " :");
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    System.out.println(" - " + p.getNom() + " " + p.getPrenom() + "\t N° secu :" + p.getNumeroSecu());
                    liste.add(p);
                }
            }
        }
    }

    public void afficherTousPatients() {
        System.out.println("> liste des patients :");
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Patient p = f.getPatient();
            if (!liste.contains(p)) {
                System.out.println(" - " + p.getNom() + " " + p.getPrenom() + "\t N° secu :" + p.getNumeroSecu());
                liste.add(p);
            }
        }
    }

    public void afficherListeMedecins(Patient p) {
        System.out.println("> liste des medecins de " + p.getNom() + " " + p.getPrenom() + " :");
        Vector<Medecin> liste = new Vector<Medecin>();
        // 'liste' contient tous les medecins deja affiches
        // --> ceci permet de ne pas reafficher un medecin deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                Medecin m = f.getMedecin();
                if (!liste.contains(p)) {
                    System.out.println(" - " + m.toString());
                    liste.add(m);
                }
            }
        }
    }

    public void afficherTousMedecins() {
        System.out.println("> liste des medecins :");
        Vector<Medecin> liste = new Vector<Medecin>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Medecin p = f.getMedecin();
            if (!liste.contains(p)) {
                System.out.println(" - " + p.toString());
                liste.add(p);
            }
        }
    }

    public void afficherDossierPatient(Patient p) {
        System.out.println("> Dossier patient :");
        System.out.println("##############################################################");
        System.out.println(p.toString());
        System.out.println("##############################################################");
        System.out.println("Actes médicaux :");
        afficherActesPatient(p);
        
        System.out.println("COUT TOTAL DU PATIENT : " + coutPatient(p));
    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public Vector<FicheDeSoins> fichesIntervalle(Date d1, Date d2) {
        Vector<FicheDeSoins> fichesIntervalle = new Vector<FicheDeSoins>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                fichesIntervalle.add(f);
            }
        }
        return fichesIntervalle;
    }

    public void afficherFichesIntervalle(Date d1, Date d2) {
        System.out.println("> liste des fiches datant du " + d1.toString() + " au " + d2.toString());
        Vector<FicheDeSoins> fichesIntervalle = fichesIntervalle(d1, d2);
        for (FicheDeSoins f : fichesIntervalle) {
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                f.afficher();
            }
        }
    }

    public Vector<FicheDeSoins> fichesPatient(Patient p) {
        Vector<FicheDeSoins> fichesPatient = new Vector<FicheDeSoins>();
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                fichesPatient.add(f);
            }
        }
        return fichesPatient;
    }

    public void afficherFichesPatient(Patient p) {
        Vector<FicheDeSoins> fichesPatient = fichesPatient(p);
        for (FicheDeSoins f : fichesPatient) {
            f.afficher();
        }
    }

    public Vector<FicheDeSoins> trierFiches(Vector<FicheDeSoins> fichesDesordre) {
        Vector<FicheDeSoins> fichesTriees = new Vector<FicheDeSoins>();
        while (!fichesDesordre.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = fichesDesordre.get(imin);
            for (int i = 1; i < fichesDesordre.size(); i++) {
                FicheDeSoins f2 = fichesDesordre.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            fichesTriees.add(f1);
            //on la supprime de la liste :
            fichesDesordre.remove(imin);
        }
        return fichesTriees;
    }

    public void afficherActesPatient(Patient p) {
        Vector<FicheDeSoins> fichesPatient = fichesPatient(p);
        fichesPatient = trierFiches(fichesPatient);
        for (FicheDeSoins f : fichesPatient) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("Medecin : Dr "+f.getMedecin().getNom()+ " "+f.getMedecin().getPrenom() + "\nDate : "+f.getDate().toString());
            f.afficherActes();
            System.out.println("--------------------------------------------------------------");
        }
    }

    public void trierDates() {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }

    // tri generique :
    public void trier(ComparaisonFiches c) {
        Vector<FicheDeSoins> copieFiches = new Vector<FicheDeSoins>(fiches);

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            f1.afficher();
            System.out.println("------------------------");
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
    }
}
