import java.io.*;
import java.net.*;

class BasicServerTCP {

    public static void main(String[] args) throws IOException
    {
    	
    	/*
    	 * Se declaran las variables que se van a usar a lo largo
    	 * de toda la clase.(Socket del servidor,cliente y buffers).
    	 */
    	
    	ServerSocket server = null;
		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		//Se define el puerto el cual se le pasa como parametro.
		
		int port = Integer.parseInt(args[0]);
		
        
        try{
        	
        	/*
        	 * Se crea el Socket del servidor, dentro de esta tupla
        	 * el primer valor corresponde al puerto pasado como argumento,
        	 * y el segundo valor es el número máximo de clientes que pueden
        	 * esperar en la cola de espera.
        	 * 
        	 * Si no se indica este parámetro, se toma el número máximo,
        	 * correspondiente a 50.
        	 * 
        	 */
        	
        	server = new ServerSocket(port,1);
        	System.out.println("Server esperando al puerto cliente "+port);
        }catch (IOException e){
        	System.out.println("No se pudo escuchar el puerto "+port);
        	System.exit(-1);
        }
        
        
            while (true){
            	
            	try{
            		
            		/*
            		 * El método accept saca una petición de la cola
            		 * de pendientes (se bloquea si no hay) y crea un
            		 * Socket conectado al cliente.
            		 */
            		
            		
    				client = server.accept();
    				System.out.println("Conectado a "+client.getInetAddress()+ ", Esperando la respuesta...");
    			}catch(IOException e){
    				System.out.println("Fallo al conectarse el cliente: "+port);
    				System.exit(-1);
    			}
            	
            	
            	
            	try {
            		
            		/*
            		 * Se definen los flujos de datos, uso BufferedReader para
            		 * leer datos, se crea en el bufferReader un nuevo InputStreamReader.
            		 * 
            		 * Se usa PrintWriter inicializado a un nuevo InputStreamReader para
            		 * el envío de datos.
            		 */
            		
    				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    				out = new PrintWriter(client.getOutputStream(),true);
    				out.println("Bienvenido al servidor de cifrado");
    			}catch (IOException e){}
                
            	int continua = 0;
            	
            	try {
            		
            		//Se define un time-out de 40 segundos,en este caso en el
            		//método se expresa en microsegundos, para que pasado ese
            		//tiempo si no se recibe interacción por parte del usuario
            		//se cierre la conexión con él.
            		
            		client.setSoTimeout(40000);
            		
            		//Leo la clave enviada por el cliente a través del método
            		//.readLine(), el cual recibe líneas completas y devuelve un String.
            		
            		String clave=in.readLine();
            		Integer num = Integer.parseInt(clave);
            	
            		//Mientras no se reciba un cero se sigue con la conexión
            		
            		while(!num.equals(0) && continua == 0) {
            			
            			try {
            			
            			//Cada vez que se recibe interacción se resetea el timer
            				
            			client.setSoTimeout(40000);
            			
            			//Se lee el texto que envió el cliente al servidor
            			
            			String texto = in.readLine();
            			System.out.println("Clave enviada por el cliente "+num);
            			System.out.println("Texto enviado por el cliente "+texto);
            			
            			//Se llama al método de cifrado.
            			
            			String cifrado = cifradoCesar(num,texto);
            			System.out.println("Texto cifrado se envia al cliente "+cifrado);
            			
            			//Con el método .out() se evía el texto al cliente.
            			//Posteriormente se vuelve a leer una nueva clave.
            			
            			out.println(cifrado);
            			clave=in.readLine();
            			num=Integer.parseInt(clave);
            			}catch(Exception ex) {
                    		System.out.println("Tiempo excedido, cerrando sesión con el cliente");
                    		
                    		//Si se excede el tiempo se cierran los flujos de datos
                    		//y se cierra la conexión con el cliente.
                    		
                    		in.close();
                    		out.close();
                    		client.close();
                    		continua=1; //Condición para salir del while
                    	}	
            		}
            	
            	
            		//Si se recibe la clave 0 se cierra el flujo de datos y posteriormente
            		//se cierra la conexión con el cliente.
            		
            		out.println("OK");
            		in.close();
            		out.close(); 
            		client.close();
            		
            		
            	}catch(Exception ex) {
            		System.out.println("Tiempo excedido, cerrando sesión con el cliente");
            		in.close();
            		out.close();
            		client.close();
            		continua = 1;
            	}	
            	
            }   	
        
    }
            	
            	
    //Método utilizado para el cifrado, el cifrado César consiste en 
    //reemplazar cada letra por otra que se encuentra n posiciones más
    //adelante(valor expresado por la clave).
    
    
            	
    public static String cifradoCesar(int codigo,String texto) {
        int numLetras = 'Z' - 'A' +1;
        codigo = codigo % numLetras;
        StringBuilder res = new StringBuilder();
        for(int i=0;i<texto.length();i++) {
        	
            //cifrado de las letras minúsculas
        	
        	 if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                 if ((texto.charAt(i) + codigo) > 'z') {
                     res.append((char) (texto.charAt(i) + codigo - 26));
                 } else {
                     res.append((char) (texto.charAt(i) + codigo));
                 }
                 
             //cifrado de las letras mayusculas
                 
             } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                 if ((texto.charAt(i) + codigo) > 'Z') {
                     res.append((char) (texto.charAt(i) + codigo - 26));
                 } else {
                     res.append((char) (texto.charAt(i) + codigo));
                 }
             }
        }
        return res.toString();
    }
}
            	
            	
            	
            	
            	
            	
            	
            	
            
    
    


