package princetonPlainsboro;

class Test {

    public static void main(String[] args) {
        //Lis le doc XML et en extrait le dm associé
        LectureXML test = new LectureXML("dossiers.xml");
        DossierMedical dm = test.getDossier();
        dm.afficher();

        System.out.println("\n********\n");

        Patient p1 = new Patient("Bole", "Pat", "0648665315", new Date(17, 6, 1998, null, null), "04659832016574", "163 rue du boulet 75 001 Paris");
        System.out.println("> cout de " + p1.toString() + " : " + dm.coutPatient(p1));

        System.out.println("\n********\n");

        String spe = "Cardiologue";
        System.out.println("> cout de la specialite '" + spe + "' : " + dm.coutSpecialite(spe));

        System.out.println("\n********\n");

        Medecin m1 = new Medecin("Deblouze", "Agathe", Specialite.Cardiologie, "0612545632", "mdp");
        dm.afficherListePatients(m1);

        System.out.println("\n********\n");

        Date d1 = new Date(1, 11, 2005, 9, 54);
        Date d2 = new Date(5, 1, 2006, 14, 32);
        System.out.println("> nombre de fiches entre " + d1 + " et " + d2 + " : " + dm.nombreFichesIntervalle(d1, d2));

        System.out.println("\n********\n");

        System.out.println();
        System.out.println("Dossier trie selon les dates :");
        dm.trierDates();

        System.out.println("\n********\n");

        System.out.println();
        System.out.println("Dossier trie selon les couts :");
        dm.trier(new ComparaisonFichesCouts());
    }
}
