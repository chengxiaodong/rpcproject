package njit.rpc;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Ҫ������Ľӿ�
		IHelloService helloService=new HelloServiceImpl();
		RpcServerProxy rpcServerProxy = new RpcServerProxy();
		//�����ӿ�
		rpcServerProxy.publish(helloService, 8080);
	}

}
