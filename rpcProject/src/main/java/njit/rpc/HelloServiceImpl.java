package njit.rpc;



public class HelloServiceImpl implements IHelloService{

	public String sayHello(String arg) {
		System.out.println("server÷¥––sayHello∑Ω∑®°£°£");
		return "hellow word:"+arg;
	}



}
