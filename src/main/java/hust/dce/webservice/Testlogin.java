package hust.dce.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hust.dce.app.datanote.DataProcessor;
import hust.dce.app.datanote.DataQuery;
import hust.dce.app.datanote.DataResult;
import hust.dce.app.datanote.Note;
import hust.dce.app.getdata.GetDataProcessor;
import hust.dce.app.getdata.GetDataQuery;
import hust.dce.app.infologin.InfoProcessor;
import hust.dce.app.infologin.InfoQuery;
import hust.dce.app.infologin.InfoResult;

@Path("/login")
@Stateless
public class Testlogin {

	@Inject
	InfoProcessor processor;
	@Inject
	DataProcessor dataProcessor;
	@Inject
	GetDataProcessor getDataProcessor;

	// Kiem tra dang nhap
	@POST
	@Path("/login")
	@Produces({ "application/json" })
	public InfoResult login(InfoQuery query) {

		return processor.handle(query);

	}

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public DataResult insertData(DataQuery query) {

		return dataProcessor.handle(query);

	}

	@POST
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public InfoResult checkInfo(InfoQuery query) {

		return processor.checkLogin(query);

	}

	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getInfo(GetDataQuery query) {

		return getDataProcessor.handle(query).getNotes();

	}

}
