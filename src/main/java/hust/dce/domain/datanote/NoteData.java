package hust.dce.domain.datanote;

import java.util.Date;
import java.util.List;

public interface NoteData {

	List<NoteDto> findData();

	List<NoteDto> findData(String userId);

	void insertData(NoteDto dto);

	void updateData(NoteDto dto);

	void deleteData(NoteDto dto);
	
	List<NoteDto> findDataWithPk(String userId, Date date ,String code);

}
