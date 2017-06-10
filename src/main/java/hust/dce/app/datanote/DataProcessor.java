package hust.dce.app.datanote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import hust.dce.app.common.HashGenerator;
import hust.dce.app.common.UserInfoLogin;
import hust.dce.domain.datanote.NoteData;
import hust.dce.domain.datanote.NoteDto;
import hust.dce.domain.infologin.InfoDto;
import hust.dce.domain.infologin.InfoLogin;

@Stateless
public class DataProcessor {
	@Inject
	private NoteData monthData;
	@Inject
	private UserInfoLogin userContext;
	@Inject
	private InfoLogin infoLogin;
	@Inject
	private HashGenerator generator;
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public DataResult handle(DataQuery query) {
		DataResult result = new DataResult();
		InfoDto info = infoLogin.findLogin(query.getId(),
				generator.generatorSha256(query.getPassword() + query.getId()));
		List<Note> notes = query.getNotes();
		for (Note note : notes) {
			try {
				NoteDto dto = new NoteDto();
				dto.setSummary(note.getNameNote());
				dto.setDetail(note.getDetailNote());
				dto.setImage(note.getImageNote());
				List<NoteDto> data = monthData.findDataWithPk(info.getUserId(), df.parse(note.getDate()),
						String.valueOf(note.getId()));
				if (data.size() > 0) {
					dto.setVersion(data.get(0).getVersion()+1);
					dto.setCode(String.valueOf(note.getId()));
					dto.setYearData(df.parse(note.getDate()));
					dto.setUserId(info.getUserId());
					monthData.updateData(dto);
				} else {
					dto.setCode(String.valueOf(note.getId()));
					dto.setVersion(0);

					dto.setYearData(df.parse(note.getDate()));
					dto.setUserId(info.getUserId());
					monthData.insertData(dto);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// List<NoteDto> dto = monthData.findData(userContext.getUserId());
		result.setDetail("ok");
		return result;
	}

}
