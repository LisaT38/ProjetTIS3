package princetonPlainsboro;

// Cette enumeration fait intervenir des valeurs qui possedent des
// attributs ('libelle' et 'cout') qui sont initialises par un appel du
// constructeur (arguments entre parentheses apres le nom de chaque valeur).
// Par exemple, la valeur Code.FP a un attribut 'libelle' contenant la chaine
// de caracteres "forfait pediatrique" et un attribut 'cout' ayant la valeur 5.0

enum Code {
    // valeurs de l'enum : (a jour 2015)
    CS("consultation au cabinet", 23.0,TypeActe.diagnostique),
    CSC("consultation cardiologie", 45.73,TypeActe.diagnostique),
    FP("forfait pediatrique", 5.0,TypeActe.therapeutique),
    KC("actes de chirurgie et de specialite", 2.75,TypeActe.therapeutique),//anciennement 2.09
    KE("actes d'echographie, de doppler", 1.89,TypeActe.diagnostique),
    K("autres actes de specialite", 1.92,TypeActe.diagnostique),
    KFA("forfait A", 30.49,TypeActe.therapeutique),
    KFB("forfait B", 60.98,TypeActe.therapeutique),
    ORT("orthodontie", 2.15,TypeActe.therapeutique),
    PRO("prothese dentaire", 2.15,TypeActe.therapeutique);
                             
    // attributs de l'enum :
    private String libelle;
    private double cout;
    private TypeActe type;
    
    // constructeur :
    private Code(String libelle, double cout, TypeActe type) {
        this.libelle = libelle;
        this.cout = cout;
        this.type=type;
        }
    
    // m�thodes :
    public String toString() {
        return super.toString() + "("+ type.toString() +")"+" : " + libelle + ", cout=" + cout + " euros";
        }
    
    // calcule le prix pour un coefficient donne :
    public double calculerCout(int coefficient) {
        return coefficient * cout;
        }

    public String getLibelle() {
        return libelle;
    }
    }
