package njit.rpc;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String methodName;
	public RpcRequest(String methodName, Object[] args) {
	
		this.methodName = methodName;
		this.args = args;
	}
	@Override
	public String toString() {
		return "RequestProtocal [className=" +", methodName=" + methodName + ", args="
				+ Arrays.toString(args) + "]";
	}
	private Object[] args;

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
}
