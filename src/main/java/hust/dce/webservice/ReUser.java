package hust.dce.webservice;

public class ReUser {
	private String key1, key2;
	public ReUser( String key1,String key2) {
      this.key1=key1;
      this.key2=key2;
	}

	public ReUser() {
	     
		}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

}
