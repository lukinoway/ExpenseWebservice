package net.home.servlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import konto.data.Util.BuildResponse;

@Path("")
public class LoginHandler {
    
    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response performLogin() {
	return BuildResponse.buildOKResponse("login could be made");
    }

}
