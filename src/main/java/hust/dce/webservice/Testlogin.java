package hust.dce.webservice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hust.dce.app.datanote.DataProcessor;
import hust.dce.app.datanote.DataQuery;
import hust.dce.app.datanote.DataResult;
import hust.dce.app.infologin.InfoProcessor;
import hust.dce.app.infologin.InfoQuery;
import hust.dce.app.infologin.InfoResult;

@Path("/Login")
@Stateless
public class Testlogin {

	@Inject
	InfoProcessor processor;
	@Inject
	DataProcessor dataProcessor;

	// Kiem tra dang nhap
	@POST
	@Path("/Login")
	@Produces({"application/json"})
	public InfoResult Login(InfoQuery query) {

		return processor.handle(query);

	}
	
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public DataResult getInfo(DataQuery query) {

		return dataProcessor.handle(query);

	}
	
	@POST
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public InfoResult checkInfo(InfoQuery query) {

		return processor.checkLogin(query);

	}

}
