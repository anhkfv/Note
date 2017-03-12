package hust.dce.infra.entity.infologin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hust_info_login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HustInfoLogin {

	@Version
	private long version;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String password;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "access_code")
	private String accessCode;
	
}
