package hust.dce.app.getdata;

import java.util.List;

import hust.dce.app.datanote.Note;
import lombok.Data;

@Data
public class GetDataResult {
	List<Note> notes;
}
