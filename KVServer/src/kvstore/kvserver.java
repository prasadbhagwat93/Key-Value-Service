package kvstore;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import kvstore.KVStore.Iface;

public class kvserver extends Thread{
	
	public static Kvimplement kvimp ;
	public static KVStore.Processor process;
	public static int port;

public static void main(String [] args) {
	
	if(true){
		
		port = 9090;
		// process = new KVStore.Processor((Iface) kvimp);
		
		Thread procThread=null;
	    try {
	    	kvimp = new Kvimplement();
	    	process = new KVStore.Processor(kvimp);
	    	
	    	procThread = new Thread(){
	    		public void run(){
	    			startServer(process);
	    		}
	    	};
	    	//startServer.setDaemon(true);
	    	//new Thread(startServer).start();
	    	 	procThread.start();
	    	    procThread.join();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println("Failed to start server, check command maybe?");
	    }
	}
	else{
		System.out.println("Failed to start server, check command maybe?");
	}
   
}
public static void startServer(KVStore.Processor processor){
	 try {
         TServerTransport serverTransport = new TServerSocket(9090);
         TServer server = new TThreadPoolServer(new
           TThreadPoolServer.Args(serverTransport).processor(processor));
         System.out.println("Starting the multi thread server...");
         server.serve();
     } catch (Exception e) {
         e.printStackTrace();
     }
}
}


