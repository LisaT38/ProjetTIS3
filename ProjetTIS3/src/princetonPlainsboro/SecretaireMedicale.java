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
public class SecretaireMedicale {

    private String identifiant;
    private String motDePasse;
    private String nom;
    private String prenom;

    public SecretaireMedicale(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        identifiant = nom.toLowerCase() + prenom.substring(0, 1).toLowerCase() + "_sec";
        motDePasse = prenom.toLowerCase();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
