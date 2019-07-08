package njit.rpc;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//要被代理的接口
		IHelloService helloService=new HelloServiceImpl();
		RpcServerProxy rpcServerProxy = new RpcServerProxy();
		//发布接口
		rpcServerProxy.publish(helloService, 8080);
	}

}
