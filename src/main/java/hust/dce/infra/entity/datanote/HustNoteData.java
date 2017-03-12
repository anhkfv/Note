package hust.dce.infra.entity.datanote;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thanhnx
 */
@Entity
@Table(name = "hust_note_data")
@Data
@IdClass(HustNoteDataPK.class)
@NoArgsConstructor
@AllArgsConstructor
public class HustNoteData implements Serializable {

	private static final long serialVersionUID = 1L;

    @Version
    private long version;
	
	@Id
	@Column(name = "user_id")
	private String userId;

	@Id
	@Column(name = "year_data")
	private Date yearData;
	
	@Id
	@Column(name = "code")
	private String code;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "detail")
	private String detail;
	
	@Column(name = "image")
	private String image;

}
