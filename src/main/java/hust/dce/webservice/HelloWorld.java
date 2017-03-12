package hust.dce.webservice;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named("helloWorld")
@SessionScoped
@ManagedBean
public class HelloWorld implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text = "Hello World!";

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}