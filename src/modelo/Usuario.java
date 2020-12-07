package modelo;

public abstract class Usuario {

    private String correo;
    private String DNI;
    private String nombre;
    private String apellido;
    private Eventos[] actividad;
    private Tutor[] tutor;

    // Constructor para crear un nuevo usuario
    public Usuario(String cor, String dni, String nombr, String apell){
        // Hacer sentencia SQL "INSERT..."
        correo = cor;
        DNI = dni;
        nombre = nombr;
        apellido = apell;
    }

    // Constructor para recuperar los datos de un usuario ya existente
    public Usuario(String cor) {
        // Hacer sentencia SQL "SELECT..."
        correo = cor;
    }

}
