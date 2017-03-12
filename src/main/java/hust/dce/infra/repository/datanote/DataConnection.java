package hust.dce.infra.repository.datanote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Data;
@Data
public class DataConnection {
	@PersistenceContext(unitName = "NOTEDATA")
	public EntityManager em;
}
