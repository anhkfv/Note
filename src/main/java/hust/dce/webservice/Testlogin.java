package hust.dce.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

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

	private static final String SAVE_FOLDER = "c://tmp/";
	private final String UPLOADED_FILE_PATH = "d:\\";

	@Inject
	InfoProcessor processor;
	@Inject
	DataProcessor dataProcessor;
	@Inject
	GetDataProcessor getDataProcessor;

// register info
	@POST
	@Path("/login")
	@Produces({ "application/json" })
	public InfoResult login(InfoQuery query) {

		return processor.handle(query);

	}
//upload info
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public DataResult insertData(DataQuery query) {

		return dataProcessor.handle(query);

	}
// check info login
	@POST
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public InfoResult checkInfo(InfoQuery query) {

		return processor.checkLogin(query);

	}
// get information note
	@POST
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getInfo(GetDataQuery query) {

		return getDataProcessor.handle(query).getNotes();

	}

	@POST
	@Path("/upload")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		List<InputPart> inputUserId = uploadForm.get("userId");
		for (InputPart inputPart : inputUserId) {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				InputStream inputStream = inputPart.getBody(InputStream.class,null);

				byte [] bytes = IOUtils.toByteArray(inputStream);
				fileName = UPLOADED_FILE_PATH + new String(bytes);

				writeFile(bytes, fileName);
				
		}

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				// constructs upload file path
				fileName = UPLOADED_FILE_PATH + fileName;

				writeFile(bytes, fileName);

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();
	}

	// get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

}
