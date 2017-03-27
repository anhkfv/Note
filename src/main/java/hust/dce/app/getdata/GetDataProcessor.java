package hust.dce.app.getdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import hust.dce.app.common.HashGenerator;
import hust.dce.app.datanote.Note;
import hust.dce.domain.datanote.NoteData;
import hust.dce.domain.datanote.NoteDto;
import hust.dce.domain.infologin.InfoDto;
import hust.dce.domain.infologin.InfoLogin;

@Stateless
public class GetDataProcessor {
	@Inject
	private NoteData monthData;
	@Inject
	private InfoLogin infoLogin;
	@Inject
	private HashGenerator generator;
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public GetDataResult handle(GetDataQuery query) {
		GetDataResult result = new GetDataResult();
		List<Note> notes = new ArrayList<>();
		InfoDto info = infoLogin.findLogin(query.getId(),
				generator.generatorSha256(query.getPassword() + query.getId()));
		List<NoteDto> datas = monthData.findData(info.getUserId());
		for (NoteDto note : datas) {
			Note dto = new Note();
			dto.setId(Integer.parseInt(note.getCode()));
			dto.setNameNote(note.getSummary());
			dto.setDetailNote(note.getDetail());
			dto.setImageNote(note.getImage());
			dto.setDate(df.format(note.getYearData()));
			notes.add(dto);
		}
		result.setNotes(notes);
		return result;
	}

}
