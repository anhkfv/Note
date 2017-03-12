package hust.dce.app.common;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Data;

@SessionScoped
@Named("UserInfoLogin")
@Data
public class UserInfoLogin implements Serializable {

	private String userId;

}