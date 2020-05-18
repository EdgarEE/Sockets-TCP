package socketsTCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class serverTCP {
	public static void main(String argv[]) throws Exception
	{
		String fraseCliente;
		String frase,frase2;
		ServerSocket welcomeSocket = new ServerSocket(6785);
		int n=0,cantidad1=0,cantidad2=0,resultado=0;
		String operando="";
		
		System.out.println( "ECO-Server iniciando.. " );
		
		while(true)
		{
			Socket connectionSocket = welcomeSocket.accept();
			
			InputStream is = connectionSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader inFromClient = new BufferedReader(isr);
			
			OutputStream os = connectionSocket.getOutputStream();
			DataOutputStream outToClient = new DataOutputStream(os);
			
			fraseCliente = inFromClient.readLine();
			StringTokenizer st = new StringTokenizer(fraseCliente);
			
			while (st.hasMoreTokens()) {
				if(n==0) {
					String token=st.nextToken();
					cantidad1 = Integer.parseInt(token);
					n=n+1;
				}
				if(n==1) {
				operando=st.nextToken();
				n=n+1;
				}
				if(n==2) {
					String token2=st.nextToken();
					cantidad2 = Integer.parseInt(token2);
					n=n+1;
					}
				}
			n=0;
		        if(operando.equals("+")) {
		        	resultado=cantidad1+cantidad2;
		        }
		        else  if(operando.equals("-")) {
		        	resultado=cantidad1-cantidad2;
		        }
		        else  if(operando.equals("*")) {
		        	resultado=cantidad1*cantidad2;
		        }
		        else  if(operando.equals("/")) {
		        	resultado=cantidad1/cantidad2;
		        }
		        else  if(operando.equals("^")) {
		        	resultado=(int) Math.pow(cantidad1,cantidad2);
		        }
		        frase2 = Integer.toString(resultado);
		        frase=frase2+ '\n';
		        System.out.println("Mensaje recibido: " + fraseCliente);
			outToClient.writeBytes( frase );
		}
	}

}
