//package hust.dce.webservice;
//
//import java.io.Serializable;
//
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//@SessionScoped
//@Named("user")
//public class User implements Serializable  {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	private int name;
//	
//	
//	
//	public int getName() {
//		return 2;
//	}
//	public void setName(int name) {
//		this.name = name;
//	}
//	@Inject
//	private UserInfo info;
//	public void setU(int name)
//	{
//		info.setUsename(name);
//	
//	}
//	public int getU()
//	{
//		return info.getUsename();
//	}
//	
//
//}
//
//
//class UserInfo  implements Serializable{
//	
//	private static final long serialVersionUID = 1L;
//	private int usename;
//	private String password;
//
//	public int getUsename() {
//		return usename;
//	}
//
//	public void setUsename(int usename) {
//		this.usename = usename;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	
//}