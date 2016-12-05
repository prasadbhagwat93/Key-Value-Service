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
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> map = (new HashMap());
	//Map map = Collections.synchronizedMap(hashMap);
	//Map<String,String> map = Collections.synchronizedMap(hmap);
	//@SuppressWarnings("null")
	{
	try {
		kvset("a","b");
	} catch (TException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Override
	public Result kvset(String key, String value) throws org.apache.thrift.TException{
		Result res = new Result();
		//System.out.println("");
		try{
		map.put(key, value);
		res.value = "Value"; 
		ErrorCode e = ErrorCode.findByValue(0);
		res.error = e;
		res.errortext = "No error Found";
		System.out.println("success");
		return res;
		}
		catch(Exception ex){
			System.out.println(ex);
			ErrorCode e = ErrorCode.findByValue(2);
			res.value = "Value";
			res.errortext = "Some error text";
			System.out.println("error");
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
			System.out.println("success");
			res.value = (String) map.get(key);
		}
		else{
			res.error = ErrorCode.findByValue(1);
			res.errortext = "Key Not Found";
			System.out.println("error");
			System.out.println(res.errortext);
			res.value = "No Key Found";
		}
		
		return res;
	}
	@Override
	public Result kvdelete(String key) throws org.apache.thrift.TException{
		Result res = new Result();
		System.out.println("in ");
		if(map.remove(key) != null){
			res.error = ErrorCode.findByValue(0);
			res.errortext = "Sucessful";
			System.out.println("success");
		}
		else{
			res.error = ErrorCode.findByValue(1);
			res.errortext = "No Key Found";
			System.out.println("error");
			System.out.println(res.errortext);
		}
		res.value = "Value";
		return res;
	}
	
	
}

