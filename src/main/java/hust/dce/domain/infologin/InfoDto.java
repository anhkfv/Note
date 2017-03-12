package hust.dce.domain.infologin;

import lombok.Data;

@Data
public class InfoDto {

	private long version;

	private String userId;

	private String password;

	private String userName;

	private String status;
	
	private String accessCode;
}
