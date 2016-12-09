package kvstore;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class kvclient{
	static KVStore.Client  client;
	 public static void main(String [] args) {
		 try{
			TTransport transport;
			//String[] token = args[1].split(":");
		    transport = new TSocket("localhost",9090); //Conecting to port
		    transport.open();
		    TProtocol protocol = new  TBinaryProtocol(transport); 
		    client = new  KVStore.Client(protocol);
		    String request = "";
		   /* RandomRequestGenerator r = new RandomRequestGenerator("Thread one ");
		    r.start();
		    RandomRequestGenerator r1 = new RandomRequestGenerator("Thread two");
		    r1.start();*/
		    Thread t1 = new Thread( new RandomRequestGenerator("Th1111"));
		    t1.start();
		    Thread t2 = new Thread( new RandomRequestGenerator("Th2222"));
		    t2.start();
		    //Thread.sleep(6000);
		    System.out.println("ALL THREADS HAVE STARTED");
		    transport.close();
		    t1.join();
			t2.join();
			ConsistencyValidator validator = new ConsistencyValidator();
			validator.call();
		 }
		 catch(Exception e){
			 	e.printStackTrace();
		 }
	 }
	 /*
	 public static void execute(String operation) throws TException{
		 Result res = null;
		 switch (operation) { 		//select operation
		 
		 case "-set" :
			  res = client.kvset("a","b");
			 System.out.println("Exitcode "+res.error.getValue());
			 //System.exit(res.error.getValue());
			 break;
		 case "-get" :
			 System.out.println(" ---> ");
			 res = client.kvget("a");
			 if(res.error.getValue() == 0){
				 System.out.println("Value is "+ res.value);
				 System.out.println("Exitcode "+res.error.getValue());
				// System.exit(res.error.getValue());
			 }
			 else{
				 System.err.println(res.errortext);
				 System.out.println("Exitcode "+res.error.getValue());
				 //System.exit(res.error.getValue());
			 }
			 
			 break;
		 case "-delete" :
			 res = client.kvdelete("b");
			 if(res.error.getValue() == 0){
				 System.out.println("Exitcode "+res.error.getValue());
				 System.exit(res.error.getValue());
			 }
			 else{
				 System.err.println(res.errortext);
				 System.out.println("Exitcode "+res.error.getValue());
				 System.exit(res.error.getValue());
			 }
			 break;
			 
		default: 
			System.out.println("Some error in command");
		 
		 }
		 
	 }*/
	
}
