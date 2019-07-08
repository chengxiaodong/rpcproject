package njit.rpc;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;



public class RemoteInvocationHandler implements InvocationHandler{
	private String host;
	private int port;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public RemoteInvocationHandler(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		RpcRequest rpcRequest=new RpcRequest(method.getName(), args);
		Socket socket=new Socket(host, port);
		ObjectOutputStream objectOutputStream = null;
		ObjectInputStream objectInputStream=null;
		try {
			 objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			 objectOutputStream.writeObject(rpcRequest);
			 objectOutputStream.flush();
			 objectInputStream=new ObjectInputStream(socket.getInputStream());
			 Object result = objectInputStream.readObject();
			 objectInputStream.close();
			 objectOutputStream.close();
			 return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(socket!=null) {
				socket.close();
			}
			if(objectInputStream!=null) {
				objectInputStream.close();
			}
			if(objectOutputStream!=null) {
				objectOutputStream.close();
			}
		}
		return null;
	}

	
}



























