package hust.dce.app.datanote;

import java.util.List;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import hust.dce.app.common.UserInfoLogin;
import hust.dce.domain.datanote.NoteData;
import hust.dce.domain.datanote.NoteDto;

@Stateless
public class DataProcessor {
	@Inject
	private NoteData monthData;
	@Inject
	private UserInfoLogin userContext;

	public DataResult handle(DataQuery query) {
		DataResult result = new DataResult();
		InitialContext ic;
		try {
			ic = new InitialContext();
			SessionContext sc =(SessionContext)   ic.lookup("java:comp/EJBContext");
			System.out.println(sc);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userContext.getUserId() == null) {
             return result;
		}
		List<NoteDto> dto = monthData.findData(userContext.getUserId());

		if (dto.size() != 0) {
			result.setDetail(dto.get(0).getDetail());
		}

		return result;
	}

}
