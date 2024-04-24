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
	public Thread ClientThread;
	public Socket socket;
	public String ServerData;
	public ArrayList <String> playerParams;
	BufferedReader br;
	BufferedWriter bw;
	private int lock = 0;
	private Object Mlock;
	public ClientSide(GamePanel gp, Socket socket, Object Mlock) {
		this.gp=gp;
		this.socket=socket;
		this.Mlock = Mlock;
		ClientThread = new Thread();
		ClientThread.start();
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void StartClient() {
	    ClientThread = new Thread(this);
	    ClientThread.start(); // Start the client thread
	    System.out.println("Client started...");
	}
	@Override
	public void run() {
	    System.out.println("Client thread: ");
	    while (gp.Multiplayer) {
	            try {
//	            	synchronized (Mlock) {
//	                    Mlock.wait();
//	                }
	                if (lock == 1) {
	                    // Client logic to send data packet
	                    SendClientData(GetClientData());
	                    lock = 0; // Set lock to 0 after sending data
	                }
	                if(lock==0){
	                	ServerData = GetServerData();
		                gp.serverProjection.receiveServerData(ServerData);
		                if(ServerData!=null) {
		                	String[] data = ServerData.split("-");
		                	if(data.length==7) {
		                		lock = Integer.parseInt(data[6]);
			                	System.out.println("Client lock: " + lock);
		                	}
		                }
	                }
//	                synchronized (Mlock) {
//	                    Mlock.notify();
//	                }s
	            } catch (IOException e) {
	                e.printStackTrace();
	            } 
//	            catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	    }
	    System.out.println("Lost connection to the client");
	}

	//Change String to bytes later on for faster data transfer between client/s and server 
	public void SendClientData(String ClientInfo) {
		System.out.println("Sending packet to the server...");
		try {
			bw.write(ClientInfo);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Packet sent");
		
		
		
	}
	public String GetServerData() throws IOException {
		String dataline =null;
		if(br.ready()) {
			System.out.println("Reading server datapacket");
			System.out.println("Acquired server data: ");
			dataline = br.readLine();
			System.out.println(dataline);
			System.out.println("Server datapacket acquired");
		}
		return dataline;
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
	public String GetClientData() {
        StringBuilder sb = new StringBuilder();
        sb.append("serverprojection").append("-");
        sb.append(gp.player.velocity).append("-");
        sb.append(gp.player.PlayerAngle).append("-");
        sb.append(gp.player.life).append("-");
        sb.append(gp.player.fuel).append("-");
        sb.append(gp.player.Choice).append("-");
        sb.append(lock);
        return sb.toString();
    }
}
