package modelo;

public class Tutor extends Usuario {
    private String correoUMA;
    private Evento[] propuesto;
    private Usuario[] usuario;

    public Tutor(String cUMA, String correo, String DNI, String nombre, String apellido){
        super(correo, DNI, nombre, apellido);
        correoUMA = cUMA;
    }
}
