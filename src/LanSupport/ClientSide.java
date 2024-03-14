package LanSupport;
import java.net.Socket;
import java.net.UnknownHostException;
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
	public ArrayList <String> playerParams;
	BufferedReader br;
	BufferedWriter bw;
	public ClientSide(GamePanel gp, Socket socket) {
		try {
			socket = new Socket("localhost",20);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.gp=gp;
		this.socket=socket;
		ClientThread = new Thread();
		ClientThread.start();
		ServerData = new ArrayList<String>();
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void StartClient() {
		ClientThread = new Thread();
		ClientThread.start();
	}
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(gp.Multiplayer==true && ClientThread!=null) {
			try {
				//SendClientPlayerData();
				GetClientData();
				GetServerPlayerData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CloseConnection();
	}
	//Change String to bytes later on for faster data transfer between client/s and server 
	public void SendClientPlayerData(ArrayList<String> ClientInfo) {
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
	public void GetServerPlayerData() throws IOException {
		while(br.ready()) {
			ServerData.add(br.readLine());
		}
	}
	public void CloseConnection() {
		try {
			br.close();
			bw.close();
			socket.close();
			ClientThread = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void GetClientData() {
		playerParams = new ArrayList<String>();
		playerParams.add(gp.player.ammoType);
		playerParams.add(gp.player.Weapon);
		playerParams.add(gp.player.direction);
		playerParams.add(Double.toString(gp.player.worldx));
		playerParams.add(Double.toString(gp.player.worldy));
		playerParams.add(Double.toString(gp.player.PlayerAngle));
		playerParams.add(Double.toString(gp.player.life));
		playerParams.add(Double.toString(gp.player.fuel));
		playerParams.add(Double.toString(gp.player.Choice));
		SendClientPlayerData(playerParams);
	}
}
