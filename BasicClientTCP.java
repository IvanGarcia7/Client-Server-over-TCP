import java.io.*;
import java.net.*;
public class BasicClientTCP 
{
    public static void main(String[] args) throws IOException 
    {
    	String clave = null;
    	String mensaje;
    	
        try 
        {
        	
        	//Se crea el socket del cliente con el servidor.
        	//El primer campo corresponde a la dirección IP del servidor
        	// y el segundo a su puerto.
        	
        	Socket echoSocket = new Socket("127.0.0.1", 12345);

        	
        	/*
    		 * Se definen los flujos de datos, uso BufferedReader para
    		 * leer datos, se crea en el bufferReader un nuevo InputStreamReader.
    		 * 
    		 * Se usa PrintWriter inicializado a un nuevo InputStreamReader para
    		 * el envío de datos.
    		 */
        	
        	PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),true);
        	BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        	BufferedReader inTeclado = new BufferedReader(new InputStreamReader(System.in));
        	System.out.println("Mensaje recibido del servidor: " + in.readLine());
        	
        	//Se lee la clave con el método .readLine() y posteriormente
        	//se comprueba que no sea 0, para leer el mensaje y enviar ambos
        	//datos al servidor con el método out.print() y out.println().
        	
        	System.out.print("Clave: ");
        	clave = inTeclado.readLine();
        	while(!clave.equals("0")) {
		        System.out.print("Mensaje a enviar: ");
		        mensaje = inTeclado.readLine();
		        out.print(clave+"\r\n");
		        out.println(mensaje);
		        System.out.print("Respuesta servidor: ");
		        System.out.println(in.readLine());
	        	System.out.print("Clave: ");
	        	clave = inTeclado.readLine();
        	}
        	
        	out.println(clave+"\r\n");
        	System.out.println(in.readLine());
        	
        	//Se cierran los flujos de datos y posteriormente el socket
        	//del cliente.
        	
            out.close();
            in.close();      
            
            echoSocket.close();

        }catch (Exception e) {
            System.err.println("Error");
            System.exit(1);
        }         
    }
}
