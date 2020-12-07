package modelo;

import modelo.Tutor;

public abstract class Usuario {
    private String correo;
    private String DNI;
    private String nombre;
    private String apellido;
    private Eventos[] actividad;
    private Tutor[] tutor;

    public Usuario(String cor, String dni, String nombr, String apell){
        correo = cor;
        DNI = dni;
        nombre = nombr;
        apellido = apell;
    }
}
