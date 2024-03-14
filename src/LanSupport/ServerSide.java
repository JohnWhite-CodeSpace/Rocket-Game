package LanSupport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

import Game.GamePanel;

public class ServerSide implements Runnable{
	public GamePanel gp;
	Thread ServerThread;
	public ServerSocket Ssocket;
	public Socket socket;
	public ArrayList <String> ClientData;
	public ArrayList <String> Client2Data;
	public ArrayList <String> Client3Data;
	public ArrayList <String> Client4Data;
	public Map<Integer, ArrayList<String>> ClientsData;
	BufferedReader br;
	BufferedWriter bw;
	public int key=0;
	public ServerSide(GamePanel gp, ServerSocket Ssocket, Socket socket) {
		this.gp=gp;
		this.Ssocket = Ssocket;
		this.socket=socket;
		try {
			socket = Ssocket.accept();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void StartServer() {
		ServerThread = new Thread();
		ServerThread.start();
	}
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(socket.isConnected()) {
			try {
				//SendClientPlayerData();
				for(int i=0; i<key; i++) {
					GetClientsData(i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void GetClientsData(int key) throws IOException {
		while(br.ready()) {
			ArrayList<String> Client = new ArrayList<String>();
			Client.add(br.readLine());
			ClientsData.put(key, Client);
		}
	}
	public void SenHostData(ArrayList<String> ServerData) {
		try {
			for(int i=0; i<= ServerData.size(); i++) {
				bw.write(ServerData.get(i));
				bw.newLine();
				bw.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
