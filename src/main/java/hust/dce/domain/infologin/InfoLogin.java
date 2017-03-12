package hust.dce.domain.infologin;

public interface InfoLogin {

	InfoDto findData(String userName);
	
	InfoDto findLogin (String userName, String passWord );
	
	InfoDto findUserId (String userId);

	void insertData(InfoDto dto);

	void updateData(InfoDto dto);

	void deleteData(InfoDto dto);

}
