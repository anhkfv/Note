package hust.dce.app.datanote;

import java.util.List;

import lombok.Data;

@Data
public class DataQuery {

	private String id;

	private String password;

	List<Note> notes;

}
