Especificación del Cliente:
  
 La dirección IP y puerto del servidor al que debe conectarse el cliente se le pasará
como argumento en la línea de comando. Por ejemplo:
java Cliente 192.168.1.2 12345

 Tras conectarse al servidor, recibirá del mismo un mensaje indicando que inició la
conexión al servicio.

 Una vez conectado el cliente, deberá pedir de forma continua pares de líneas de texto
al usuario (la primera es un número y la segunda un texto). La clave envíela al servidor
con print(clave+”\r\n”) tras leerlo y use println para enviar el texto.

 Tras cada envío, el cliente deberá esperar la respuesta del servidor (que contendrá una
cadena con el texto codificado. Por ejemplo, si el cliente envía 2 y hola el servidor
responderá jqnc).

 Se supone que los datos introducidos por el usuario son correctos y el programa no
necesita comprobar ninguna clave (siempre serán números naturales) ni texto (no
contendrán tildes ni ñ).

 Cuando el usuario quiera terminar escribirá por teclado como clave el valor 0. Note que
cuando el usuario meta como clave un 0, no debe leer el texto.

 Cuando el cliente detecte que el usuario desea terminar enviará al servidor la clave 0
(usando println), esperará la respuesta del servidor (OK) y cerrará la conexión.

 Durante toda la ejecución el cliente debe informar al usuario (escribiendo por pantalla)
su estado (por ejemplo: Conectado a 192.168.1.2:12345, Esperando la
respuesta...).

 Si el cliente envía datos y se encuentra la conexión cerrada, cierra de forma ordenada
el cliente.

Especificación del Servidor (clase Servidor.java):

 El puerto por el que recibirá peticiones será pasado como argumento en la línea de
comando. Por ejemplo: java Servidor 12345

 Una vez establecida la conexión con un cliente, enviará al cliente la cadena:
“Bienvenido al servicio de cifrado” (sin comillas).

 Luego esperará a recibir pares de datos, clave y texto a cifrar, (dos líneas de texto, lea
cada una con readLine) desde el socket conectado.

 La primera línea la convertirá a número y lo usará como clave para cifrar la segunda
línea de texto (en el anexo se muestra información de cómo hacer el cifrado).

 El servidor cuando reciba como clave 0, enviará al cliente la cadena OK y cerrará la
conexión.

 Una vez cerrada la conexión, el servidor volverá a esperar una nueva petición de
conexión y servicio.

 Al igual que el cliente, el servidor deberá informar por la salida estándar (pantalla) de
su estado en cada momento.

 El servidor sólo puede tener un cliente en espera (la cola de clientes pendientes
debe ser 1).

 Si el servidor no recibe datos de un cliente conectado en 40 segundos (use el método
setSoTimeout() antes de la recepción), cierra el socket conectado. 
