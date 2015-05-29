package princetonPlainsboro;

class Acte {
    private Code code;
    private int coef;
    private TypeActe type;
    private String observations;
    
    public Acte(Code code, int coef, TypeActe type, String observations) {
        this.code = code;
        this.coef = coef;
        this.type=type;
        this.observations=observations;
        }
    
    public String toString() {
        String s = code.toString() + ", coefficient : " + coef + '\n';
        if(observations!=null){
            s += "\tObservations : "+ observations;
        }
        return s;
        }
    
    public double cout() {
        return code.calculerCout(coef);
        }
    }
