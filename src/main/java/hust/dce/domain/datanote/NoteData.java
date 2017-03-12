package hust.dce.domain.datanote;

import java.util.List;

public interface NoteData {

	List<NoteDto> findData();

	List<NoteDto> findData(String userId);

	void insertData(NoteDto dto);

	void updateData(NoteDto dto);

	void deleteData(NoteDto dto);

}
