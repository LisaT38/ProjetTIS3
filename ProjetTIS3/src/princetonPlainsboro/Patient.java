package princetonPlainsboro;

class Patient {

    private String nom;
    private String prenom;
    private String numeroTel;
    private Date dateNaissance;
    private String numeroSecu;
    private String adresse;

    public Patient(String nom, String prenom, String numeroTel, Date dateNaissance, String numeroSecu, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTel = numeroTel;
        this.dateNaissance = dateNaissance;
        this.numeroSecu = numeroSecu;
        this.adresse = adresse;
    }

    public String toString() {
        return prenom + " " + nom + " ne(e) le " + dateNaissance + "\n\t tel : " + numeroTel + "\n\t adresse : " + adresse + "\n\t numero secu : " + numeroSecu;
    }

    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return numeroSecu.equals(p.getNumeroSecu());
        } else {
            return false;
        }
    }

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
     * @param numeroTel the numeroTel to set
     */
    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @param numeroSecu the numeroSecu to set
     */
    public void setNumeroSecu(String numeroSecu) {
        this.numeroSecu = numeroSecu;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroSecu() {
        return numeroSecu;
    }

}
