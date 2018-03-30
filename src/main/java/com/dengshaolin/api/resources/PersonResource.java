package com.dengshaolin.api.resources;

import java.util.List;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;

import com.dengshaolin.api.model.ListPersonReq;
import com.dengshaolin.api.model.ListPersonRsp;
import com.dengshaolin.db.PersonDao;
import com.dengshaolin.db.model.Person;
import com.google.gson.Gson;

public class PersonResource extends ServerResource {

	@Autowired
	PersonDao personDao;

	@Get
	public Representation list(JSONObject jsonObject) {
		System.out.println(getRequest().getResourceRef().getQueryAsForm().toString());
		Gson gson = new Gson();
		ListPersonReq req = gson.fromJson(gson.toJson(jsonObject), ListPersonReq.class);
		System.out.println("Req: " + req);
		List<Person> persons = personDao.list();
		ListPersonRsp rsp = new ListPersonRsp();
		rsp.setPersons(persons);
		
		return new JsonRepresentation(gson.toJson(rsp));
	}

}
