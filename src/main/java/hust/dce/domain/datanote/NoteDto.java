package hust.dce.domain.datanote;

import java.util.Date;

import lombok.Data;

@Data
public class NoteDto {
	
	private long version;

	private String userId;

	private Date yearData;

	private String code;

	private String summary;

	private String detail;

	private String image;
}
