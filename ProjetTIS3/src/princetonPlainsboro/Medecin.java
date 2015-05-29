package princetonPlainsboro;

class Medecin {

    private String nom;
    private String prenom;
    private Specialite specialite;
    private String telephone;
    private String motDePasse;

    public Medecin(String nom, String prenom, Specialite specialite, String telephone, String motDePasse) {

        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public String toString() {
        return "Dr " + prenom + " " + nom + ", " + specialite.toString() + "\n tel : " + telephone;
    }

    public boolean equals(Object o) {
        if (o instanceof Medecin) {
            Medecin p = (Medecin) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @param specialite the specialite to set
     */
    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @param motDePasse the motDePasse to set
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
