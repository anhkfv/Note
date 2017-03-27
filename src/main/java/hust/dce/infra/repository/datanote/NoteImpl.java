package hust.dce.infra.repository.datanote;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import hust.dce.domain.datanote.NoteData;
import hust.dce.domain.datanote.NoteDto;
import hust.dce.infra.entity.datanote.HustNoteData;

@Stateless
public class NoteImpl extends DataConnection implements NoteData {

	private static final String FIND_DATA_FOM_USERID;
	private static final String FIND_ALL_DATA;
	private static final String FIND_DATA_PK;
	private static final String UPDATE;

	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM HustNoteData  t ");
		queryBuilder.append(" WHERE t.userId = :userId ");
		FIND_DATA_FOM_USERID = queryBuilder.toString();

		queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM HustNoteData  t ");
		queryBuilder.append(" WHERE t.userId = :userId ");
		queryBuilder.append(" AND t.yearData = :yearData ");
		queryBuilder.append(" AND t.code = :code ");
		FIND_DATA_PK = queryBuilder.toString();

		queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM HustNoteData  t ");
		FIND_ALL_DATA = queryBuilder.toString();

		queryBuilder = new StringBuilder();
		queryBuilder.append(" UPDATE HustNoteData  t ");
		queryBuilder.append(" SET t.summary = :summary");
		queryBuilder.append(" AND t.detail = :detail");
		queryBuilder.append(" AND t.image = :image");
		UPDATE = queryBuilder.toString();
	}

	@Override
	public List<NoteDto> findData() {
		List<HustNoteData> results = this.em.createQuery(FIND_ALL_DATA).getResultList();

		return results.stream().map(x -> {
			return convert(x);
		}).collect(Collectors.toList());
	}

	@Override
	public void insertData(NoteDto dto) {
		this.em.persist(convertDto(dto));
		this.em.flush();
	}

	@Override
	public void updateData(NoteDto dto) {
		this.em.merge(convertDto(dto));
		this.em.flush();
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

	private HustNoteData convertDto(NoteDto dto) {
		HustNoteData hustNoteData = new HustNoteData();
		hustNoteData.setVersion(dto.getVersion());

		hustNoteData.setUserId(dto.getUserId());

		hustNoteData.setYearData(dto.getYearData());

		hustNoteData.setCode(dto.getCode());

		hustNoteData.setSummary(dto.getSummary());

		hustNoteData.setDetail(dto.getDetail());

		hustNoteData.setImage(dto.getImage());
		return hustNoteData;
	}

	@Override
	public List<NoteDto> findDataWithPk(String userId, Date date, String code) {
		List<HustNoteData> results = this.em.createQuery(FIND_DATA_PK).setParameter("userId", userId)
				.setParameter("yearData", date).setParameter("code", code).getResultList();

		return results.stream().map(x -> {
			return convert(x);
		}).collect(Collectors.toList());
	}
}
