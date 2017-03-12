package hust.dce.app.infologin;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import hust.dce.app.common.HashGenerator;
import hust.dce.app.common.UserInfoLogin;
import hust.dce.domain.infologin.InfoDto;
import hust.dce.domain.infologin.InfoLogin;

@Stateless
public class InfoProcessor {
 
	@Inject
	private InfoLogin infoLogin;
	
	@Inject
	private HashGenerator generator;
	
	@Inject 
	private UserInfoLogin userInfoLogin;

	public InfoResult handle(InfoQuery query) {

		InfoDto dto = infoLogin.findData(query.getId());
		
		InfoResult result = new InfoResult();
		
		if(dto != null){
			result.setNameNotice("Tên đăng kí đã có người sử dụng");
			result.setStatus(false);
		}
		else{
			InfoDto infoDto = new InfoDto();
			infoDto.setPassword(generator.generatorSha256(query.getPassword()+query.getId()));
			infoDto.setUserName(query.getId());
			infoDto.setUserId(generator.generatorMd2(""+new Date().getTime()+query.getId()));
			infoDto.setVersion(1L);
			infoDto.setAccessCode(""+new Date().getTime());
			infoDto.setStatus("1");
			infoLogin.insertData(infoDto);
			result.setNameNotice("Đăng kí thành công ");
			result.setAccessCode(infoDto.getAccessCode());
			result.setUserName(infoDto.getUserName());
			result.setLinkTopPage("/Note/data/index.xhtml");
			result.setStatus(true);
			
			userInfoLogin.setUserId(infoDto.getUserId());
		}
		return result;
	}
	
	public InfoResult checkLogin(InfoQuery query) {

		InfoDto dto = infoLogin.findLogin(query.getId(),generator.generatorSha256(query.getPassword()+query.getId()));
		
		InfoResult result = new InfoResult();
		
		if(dto == null){
			result.setNameNotice("Không đúng, nhập lại");
			result.setStatus(false);
		}
		else{
			result.setNameNotice("Đăng nhập thành công ");
			result.setAccessCode(dto.getAccessCode());
			result.setUserName(dto.getUserName());
			result.setLinkTopPage("/Note/data/index.xhtml");
			result.setStatus(true);
			userInfoLogin.setUserId(dto.getUserId());
		}
		return result;
	}


}
