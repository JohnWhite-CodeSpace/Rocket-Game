package LanSupport;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Entity.Entity;
import Game.GamePanel;

public class ServerSide implements Runnable{
	public GamePanel gp;
	public Thread ServerThread;
	public ServerSocket Ssocket;
	public Socket socket;
	public String ClientData;
	public ArrayList <String> Client2Data;
	public ArrayList <String> Client3Data;
	public ArrayList <String> Client4Data;
	public ArrayList <String> HostData;
	public Map<Integer, ArrayList<String>> ClientsData;
	BufferedReader br;
	BufferedWriter bw;
	public int key=0;
	private int lock = 1;
	private Object Mlock;
	
	public ServerSide(GamePanel gp, ServerSocket Ssocket, Object Mlock) {
		ClientsData = new HashMap<>();
		this.gp=gp;
		this.Ssocket = Ssocket;
		this.Mlock = Mlock;
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
		ServerThread = new Thread(this);
		ServerThread.start();
	}
	@Override
    public void run() {
        System.out.println("Server thread: ");
        while (socket.isConnected()) {
        	try {
//        		synchronized (Mlock) {
//                    Mlock.wait();
//                }
                if (lock == 1) {
                    SendHostData(GetHostData());
                    lock = 0;
                } 
                Thread.sleep(5);
                if(lock==0){
                	ClientData = GetClientData();
                    gp.clientProjection.receiveClientData(ClientData);
                    if(ClientData!=null) {
                    	String[] data = ClientData.split("-");
                    	if(data.length==7) {
                    		lock = Integer.parseInt(data[6]);
    	                	System.out.println("Server lock: " + lock);
                    	}
                    }
                }
                Thread.sleep(10);
//                synchronized (Mlock) {
//                    Mlock.notify();
//                }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
//        	catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        	catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
        }
        System.out.println("Server stopped");
    }
	public String GetClientData() throws IOException {
		String dataline = null;
		if(br.ready()) {
			System.out.println("Reading client datapacket");
			System.out.println("Acquired client data: ");
			dataline = br.readLine();
			System.out.println(dataline);
			System.out.println("Client datapacket acquired");
			
		}
		return dataline;
	}
//	public String GetClientsData(int key) throws IOException {
//		System.out.println("Reading client datapacket");
//		String dataline;
//		System.out.println("Acquired client data: ");
//		dataline = br.readLine();
//		System.out.println(dataline);
//		System.out.println("Client datapacket acquired");
//		return dataline;
//	}
	public void SendHostData(String ServerData) {
		System.out.println("Sending packet to the client...");
		try {
			bw.write(ServerData);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Packet sent");
	}
	public void CloseServer() {
		try {
			br.close();
			bw.close();
			socket.close();
			ServerThread = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String GetHostData() {
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
