/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro;

/**
 *
 * @author Lisa
 */
public class TestCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LectureXML test = new LectureXML("dossiers.xml");
        DossierMedical dm = test.getDossier();
        
        Patient p1 = new Patient("Bole", "Maggy", "0648665315", new Date(17, 6, 1998, null, null), "04659832016574", "163 rue du boulet 75 001 Paris");
        
        dm.afficherDossierPatient(p1);
    }
    
}
