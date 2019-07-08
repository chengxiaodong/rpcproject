package njit.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import javax.swing.plaf.SliderUI;

public class ProcessorHandler implements Runnable{
	private Socket socket;
	private Object service;
	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	public ProcessorHandler(Socket socket, Object service) {
		this.socket = socket;
		this.service = service;
	}



	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			Thread.currentThread().sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ObjectInputStream objectInputStream=null;
		ObjectOutputStream outputStream=null;
		try {
		 objectInputStream = new ObjectInputStream(socket.getInputStream());
		RpcRequest request = (RpcRequest)objectInputStream.readObject();
		Object result=invoke(request);
		
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		outputStream.writeObject(result);
		outputStream.flush();
		objectInputStream.close();
		outputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(objectInputStream!=null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public Object invoke(RpcRequest request) throws NoSuchMethodException, SecurityException {
		String methodName=request.getMethodName();
		Object[] args=request.getArgs();
		Class<?>[] clazz=new Class[args.length];
		for(int i=0,j=args.length;i<j;i++) {
			clazz[i]=args[i].getClass();
		}
		Method method = service.getClass().getMethod(methodName, clazz);
		try {
			Object result = method.invoke(service, args);
			return result;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
 	}
}








