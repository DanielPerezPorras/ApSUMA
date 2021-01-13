package modelo;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Correo implements Runnable{
    // Socket para las comunicaciones
    private static SSLSocket socket = null;
    // Streams para el envío y recepción
    private static InputStream in = null;
    private static OutputStream out = null;

    // Asignamos el usuario y el correo para usar la cuenta de gmail
    private static final String usuario = "fsedenoguerrero@gmail.com";
    private static final String clave = "vasneauiehbcnpwe";

    private static void conectar(){
        try {
            SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
            // Conectamos el socket al servidor de correo de gmail en el puerto 465
            socket = (SSLSocket) f.createSocket("smtp.gmail.com", 465);
            in = socket.getInputStream();
            out = socket.getOutputStream();

        } catch (IOException e) {
            System.out.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    private static void desconectar(){
        try {
            // Cerramos los streams y el socket
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error al cerrar la conexión con el servidor: " + e.getMessage());
        }
    }

    private static void enviar(String mensaje){
        // Mostramos por consola el mensaje a enviar
        try {
            // Añadimos al final del mensaje recibido los caracteres \r\n para indicar la terminación de línea
            String cadena = mensaje + "\r\n";
            // Convertimos la cadena a array de bytes para escribirla en el socket de salida
            byte[] arrb = cadena.getBytes();
            // Enviamos los datos
            out.write(arrb);

            // Usamos flush para forzar que el envio se haga en este momento
            out.flush();
        } catch (IOException e) {
            System.out.println("Error al enviar: " + e.getMessage());
        }
    }

    private static void recibir(){
        byte [] buffer = new byte[5000];
        try {
            // Leemos el mensaje del inputstream
            in.read(buffer);

        } catch (IOException e) {
            System.out.println("Error al recibir: " + e.getMessage());
        }
        // Convertimos el mensaje recibido a String ...
        String recv = new String(buffer,0,buffer.length);
        // ... eliminamos el \r\n final ...
        recv = recv.substring(0,recv.lastIndexOf('\r'));
        // Comprobamos que el codigo recibido sea correcto
        int codigo = Integer.parseInt(recv.split("[- ]+")[0]);

        if(codigo >= 400){
            // Reiniciamos la conexón si no es un código válido
            enviar("RSET");
            // Cerramos la conexión
            enviar("QUIT");
            // Desconectamos del servidor
            desconectar();
        }
    }

    public static void mandarCorreo(String correo, String asunto, String cuerpo) throws IOException {
        // Flujo de lectura de teclado:
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        // Nos conectamos al servidor
        conectar();

        // ESQUEMA DEL PROTOCOLO SMTP

        // Recibimos el saludo inicial del servidor
        recibir();

        // Enviamos el EHLO y recibimos su respuesta
        enviar("EHLO smtp.google.com");
        recibir();
        //Enviamos AUTH LOGIN para indicar que usaremos este tipo de autorización y recibimos su respuesta

        enviar("AUTH LOGIN");
        // Enviamos el usuario y la contraseña codificadas en Base64 y recibimos ambas respuestas
        String user = Base64.getEncoder().encodeToString(usuario.getBytes());
        String pass = Base64.getEncoder().encodeToString(clave.getBytes());
        enviar(user);
        recibir();
        enviar(pass);
        recibir();

        // Enviamos origen del mensaje (entre <>)y su recibimos su respuesta
        enviar("MAIL FROM: <" + "fsedenoguerrero@gmail.com" + ">" );
        recibir();

        // Enviamos los destinos del mensaje (entre <>) y recibimos su respuesta
        List<String> destinos = new ArrayList<String>();
        enviar("RCPT TO: <" + correo + ">" );
        recibir();

        // Ahora enviamos el correo: cabeceras + cuerpo
        // Enviamos el DATA y recibimos la respuesta
        enviar("DATA ");
        recibir();

        // Cabeceras:
        // Enviar la cabecera From: (no hay que recibir respuesta)
        enviar("From: APS_UMA <" + usuario + ">");

        // Enviar las cabeceras To: (no hay que recibir respuesta)
        enviar("To: <" + correo + ">");

        // enviar la cabecera "Subject:" con el asunto (no hay que recibir respuesta)
        enviar("Subject: " + asunto);

        // Enviamos una línea en blanco para separar las cabeceras del cuerpo
        enviar("");

        // Ahora enviamos los datos del cuerpo
        enviar(cuerpo);
        // Enviamos una linea en blanco y luego un punto para indicar el fin de los datos
        enviar("");
        enviar(".");
        recibir();


        // Enviamos QUIT para acabar y recibimos la respuesta
        enviar("QUIT");
        recibir();

        // Nos desconectamos del servidor
        desconectar();

    }

    private void notificar() {
        BD bd = new BD();
        List<Object[]> lista = bd.Select("SELECT ue.correo,e.nombre FROM UsuarioEvento ue join Evento e on (ue.nombre = e.nombre)  WHERE notificado=0 and DATEDIFF(e.fecha,CURDATE()) <=1;");
        for (Object[] ob : lista) {
            String correo = ob[0].toString();
            String nombre = ob[1].toString();
            System.out.println(correo+nombre);
            try {
                Correo.mandarCorreo(correo,"Aviso sobre " + nombre,"El evento " + nombre +  " comenzará pronto, ¡No te lo pierdas!");
                bd.Update("UPDATE UsuarioEvento SET notificado=1 WHERE correo='" + correo + "' and nombre = '" + nombre + "';");
            } catch (Exception e) {
                System.out.println("errorsito");
            }

        }
    }

    @Override
    public void run() {
        this.notificar();
    }
}
