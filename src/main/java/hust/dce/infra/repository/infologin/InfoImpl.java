package hust.dce.infra.repository.infologin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import hust.dce.domain.infologin.InfoDto;
import hust.dce.domain.infologin.InfoLogin;
import hust.dce.infra.entity.infologin.HustInfoLogin;
import hust.dce.infra.repository.datanote.DataConnection;

@Stateless
public class InfoImpl extends DataConnection implements InfoLogin {

	private static final String FIND_DATA_USER;

	private static final String FIND_DATA_LOGIN;

	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM HustInfoLogin  t ");
		queryBuilder.append(" WHERE t.userName = :userName ");
		FIND_DATA_USER = queryBuilder.toString();

		queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM HustInfoLogin  t ");
		queryBuilder.append(" WHERE t.userName = :userName ");
		queryBuilder.append(" AND t.password = :password ");
		FIND_DATA_LOGIN = queryBuilder.toString();

	}

	@Override
	public InfoDto findData(String userName) {
		TypedQuery<HustInfoLogin> query = this.em.createQuery(FIND_DATA_USER, HustInfoLogin.class);
		List<HustInfoLogin> entity = query.setParameter("userName", userName).getResultList();
		if (entity.size() != 0) {
			return convert(entity.get(0));
		}
		return null;
	}

	@Override
	public void insertData(InfoDto dto) {
		this.em.persist(convertEntity(dto));
	}

	@Override
	public void updateData(InfoDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteData(InfoDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public InfoDto findLogin(String userName, String passWord) {
		TypedQuery<HustInfoLogin> query = this.em.createQuery(FIND_DATA_LOGIN, HustInfoLogin.class);
		List<HustInfoLogin> entity = query.setParameter("userName", userName).setParameter("password", passWord)
				.getResultList();
		if (entity.size() != 0) {
			return convert(entity.get(0));
		}
		return null;
	}

	@Override
	public InfoDto findUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	private InfoDto convert(HustInfoLogin entity) {
		InfoDto dto = new InfoDto();
		dto.setVersion(entity.getVersion());
		dto.setUserName(entity.getUserName());
		dto.setUserId(entity.getUserId());
		dto.setStatus(entity.getStatus());
		dto.setPassword(entity.getPassword());
		dto.setAccessCode(entity.getAccessCode());
		return dto;
	}

	private HustInfoLogin convertEntity(InfoDto dto) {
		HustInfoLogin entity = new HustInfoLogin();
		entity.setVersion(dto.getVersion());
		entity.setUserName(dto.getUserName());
		entity.setUserId(dto.getUserId());
		entity.setStatus(dto.getStatus());
		entity.setAccessCode(dto.getAccessCode());
		entity.setPassword(dto.getPassword());
		return entity;
	}

}
