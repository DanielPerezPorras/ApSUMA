package modelo;

public class Tutor extends Usuario {
    private String correoUMA;
    private Evento[] propuesto;
    private Usuario[] usuario;
    private String contra;

    public Tutor(String cUMA, String correo, String DNI, String nombre, String apellido, String contr){
        super(correo, DNI, nombre, apellido, contr);
        correoUMA = cUMA;
    }
}
