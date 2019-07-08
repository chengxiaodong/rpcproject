package njit.rpc;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RpcClientProxy rpcClientProxy = new RpcClientProxy();
		IHelloService helloService = rpcClientProxy.clientProxy(IHelloService.class, "localhost", 8080);
		for(int i=0;i<10000;i++) {
			
			System.out.println(helloService.sayHello("nihao"+i));
		}
	}

}
