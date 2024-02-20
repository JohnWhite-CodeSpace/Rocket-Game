package LanSupport;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import Game.GamePanel;

public class ClientSide implements Runnable{
	
	public GamePanel gp;
	Thread ClientThread;
	public Socket socket;
	public ArrayList <String> ServerData;
	BufferedReader br;
	BufferedWriter bw;
	public ClientSide(GamePanel gp, Socket socket) {
		this.gp=gp;
		this.socket=socket;
		ClientThread = new Thread();
		ClientThread.start();
		ServerData = new ArrayList<String>();
	}
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(gp.Multiplayer==true && ClientThread!=null) {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	//CHnage String to bytes later on for faster data transfer between client/s and server 
	public void SendClientPlayerPosition(ArrayList<String> ClientInfo) {
		try {
			for(int i=0; i<= ClientInfo.size(); i++) {
				bw.write(ClientInfo.get(i));
				bw.newLine();
				bw.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public void GetServerPlayerPosition() throws IOException {
		while(br.ready()) {
			ServerData.add(br.readLine());
		}
	}
}
