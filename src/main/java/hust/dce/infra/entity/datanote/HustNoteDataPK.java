package hust.dce.infra.entity.datanote;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author thanhnx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HustNoteDataPK implements Serializable {

	/**
	 * key primary 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	private Date yearData;

	private String code;

}
