package hust.dce.app.datanote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
	
	private String nameNote;
	
	private String detailNote;
	
	private String imageNote;
	
	private String date;
	
	private int id;

	
}
