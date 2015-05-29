package princetonPlainsboro;

class Date implements Comparable {

    private int jour;
    private int mois;
    private int annee;
    private Integer heure; //int ne peut pas etre null -> probleme avec les dates de naissance
    private Integer minute;

    public Date(int jour, int mois, int annee, Integer heure, Integer minute) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
    }

    public String toString() {
        String s = jour + "/" + mois + "/" + annee;
        if(heure!=null && minute!=null){
            s+=", "+heure+"h"+minute;
        }
        return s;
    }

    public boolean equals(Object o) {
        if (o instanceof Date) {
            Date d = (Date) o;
            return (annee == d.annee) && (mois == d.mois) && (jour == d.jour);
        } else {
            return false;
        }
    }

    // precondition : 'o' est une instance de 'Date' :
    public int compareTo(Object o) {
        Date d = (Date) o;
        if (annee != d.annee) {
            return annee - d.annee;
        }
        // ici on a forcement annee == d.annee :
        if (mois != d.mois) {
            return mois - d.mois;
        }
        // ici on a forcement annee == d.annee et mois == d.mois :
        return jour - d.jour;
    }

}
