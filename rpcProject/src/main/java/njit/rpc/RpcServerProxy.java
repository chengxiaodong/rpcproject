package njit.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServerProxy {
	private ExecutorService pool=Executors.newFixedThreadPool(20);
	//��������ָ���˿�
	public void publish(Object service,int port) {
		System.out.println("����server������������");
		ServerSocket server=null;
		try {
		server=new ServerSocket(port);
		while(true) {
			Socket socket = server.accept();
		
			pool.execute(new ProcessorHandler(socket,service));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
