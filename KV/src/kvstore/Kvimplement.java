package kvstore;
import org.apache.http.util.Args;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import kvstore.KVStore.Iface;

import java.util.*;
public class Kvimplement implements KVStore.Iface{
	
	public HashMap<String, String> map = new HashMap<String, String>();
	//@SuppressWarnings("null")
	@Override
	public Result kvset(String key, String value) throws org.apache.thrift.TException{
		Result res = new Result();
		System.out.println("in set");
		try{
		map.put(key, value);
		res.value = "Value"; 
		ErrorCode e = ErrorCode.findByValue(0);
		res.error = e;
		res.errortext = "No error Found";
		return res;
		}
		catch(Exception ex){
			System.out.println(ex);
			ErrorCode e = ErrorCode.findByValue(2);
			res.value = "Value";
			res.errortext = "Some error text";
			}
		return res;
	}
	@Override
	public Result kvget(String key) throws org.apache.thrift.TException{
		Result res = new Result();
		if(map.get(key)!=null){
			res.error = ErrorCode.findByValue(0);
			System.out.println(map.get(key));
			res.errortext = "KeyFound";
		}
		else{
			res.error = ErrorCode.findByValue(1);
			res.errortext = "Key Not Found";
		}
		res.value = "Value";
		
		System.out.println("in get");
		System.out.println(map.get(key));
		return res;
	}
	@Override
	public Result kvdelete(String key) throws org.apache.thrift.TException{
		Result res = new Result();
		if(map.remove(key) != null){
			res.error = ErrorCode.findByValue(0);
			res.errortext = "Sucessful";
		}
		else{
			res.error = ErrorCode.findByValue(1);
			res.errortext = "No Key Found";
		}
		res.value = "Value";
		return res;
	}
	
	
}

