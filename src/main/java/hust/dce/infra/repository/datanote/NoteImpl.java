package hust.dce.infra.repository.datanote;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import hust.dce.domain.datanote.NoteData;
import hust.dce.domain.datanote.NoteDto;
import hust.dce.domain.infologin.InfoDto;
import hust.dce.infra.entity.datanote.HustNoteData;
import hust.dce.infra.entity.infologin.HustInfoLogin;

@Stateless
public class NoteImpl extends DataConnection implements NoteData {

	private static final String FIND_DATA_FOM_USERID;

	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM HustNoteData  t ");
		queryBuilder.append(" WHERE t.userId = :userId ");
		FIND_DATA_FOM_USERID = queryBuilder.toString();
	}

	@Override
	public List<NoteDto> findData() {
		String query = "SELECT t FROM HustNoteData  t ";
		List<HustNoteData> results = this.em.createQuery(query).getResultList();

		return results.stream().map(x -> {
			return convert(x);
		}).collect(Collectors.toList());
	}

	@Override
	public void insertData(NoteDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateData(NoteDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteData(NoteDto dto) {
		// TODO Auto-generated method stub

	}

	public NoteDto convert(HustNoteData entity) {

		NoteDto dto = new NoteDto();
		dto.setUserId(entity.getUserId());
		return dto;
	}

	@Override
	public List<NoteDto> findData(String userId) {
		TypedQuery<HustNoteData> query = this.em.createQuery(FIND_DATA_FOM_USERID, HustNoteData.class);
		List<HustNoteData> entity = query.setParameter("userId", userId).getResultList();
		if (entity.size() != 0) {
			return entity.stream().map(x -> {
				return convertEntity(x);
			}).collect(Collectors.toList());
		}
		return null;
	}

	private NoteDto convertEntity(HustNoteData entity) {
		NoteDto dto = new NoteDto();

		dto.setVersion(entity.getVersion());

		dto.setUserId(entity.getUserId());

		dto.setYearData(entity.getYearData());

		dto.setCode(entity.getCode());

		dto.setSummary(entity.getSummary());

		dto.setDetail(entity.getDetail());

		dto.setImage(entity.getImage());

		return dto;
	}
}
