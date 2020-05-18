package socketsTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class clientTCP {

	public static void main(String argv[]) throws Exception
	{
		String frase;
		String fraseModificada;
		Socket clientSocket = new Socket("localhost", 6785);
		
		System.out.print( "Mensaje: " );
		
		InputStreamReader isr1 = new InputStreamReader( System.in );
		BufferedReader inFromUser = new BufferedReader( isr1 );
		
			frase = inFromUser.readLine();
			
		OutputStream os = clientSocket.getOutputStream();
		DataOutputStream outToServer = new DataOutputStream( os );
		
			outToServer.writeBytes(frase + '\n');
		
		InputStreamReader isr2 = new InputStreamReader( clientSocket.getInputStream() );
		BufferedReader inFromServer = new BufferedReader( isr2 );
		
			fraseModificada = inFromServer.readLine();
		
		System.out.println("Mensaje del Servidor: " + fraseModificada);
		
		clientSocket.close();
	}
}
