package kvstore;

public class SequenceServer{
	
	static int counter = 0;
	
	static synchronized int getNext(){
		counter++;
		return counter;
	}
}
