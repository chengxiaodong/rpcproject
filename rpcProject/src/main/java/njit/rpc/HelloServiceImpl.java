package njit.rpc;



public class HelloServiceImpl implements IHelloService{

	public String sayHello(String arg) {
		System.out.println("serverִ��sayHello��������");
		return "hellow word:"+arg;
	}



}
