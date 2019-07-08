package njit.rpc;

import java.lang.reflect.Proxy;

public class RpcClientProxy {
	
	public<T> T clientProxy(Class<T> interfaceClas ,String host,int port) {
		return (T) Proxy.newProxyInstance(interfaceClas.getClassLoader(), new Class<?>[]{interfaceClas},new RemoteInvocationHandler(host,port) );
	}
}
