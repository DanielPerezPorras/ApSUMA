package modelo;

public class Tutor extends Usuario {
    private String correoUMA;
    private Evento[] propuesto;
    private Usuario[] usuario;

    public Tutor(String cUMA){
        super();
        correoUMA = cUMA;
    }
}
