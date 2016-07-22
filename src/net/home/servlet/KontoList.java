package net.home.servlet;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import konto.data.DBUtil.IKonto;
import konto.data.DBUtil.IUser;
import konto.data.DBUtil.KontoDBUtil;
import konto.data.DBUtil.UserDBUtil;
import konto.data.Util.BuildResponse;
import konto.data.model.Konto;
import konto.data.model.LoginUser;

@Path("kontolist")
public class KontoList {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{user}")
    public Response getKontoList(@PathParam("user") String username) {

	// check user
	LoginUser user = new LoginUser(username, "");
	IUser userUtil = new UserDBUtil();
	userUtil.loadUserId(user);
	
	// send error if user couldn't be found
	if(user.getUserId() == -1) {
	    return BuildResponse.buildErrorReposne("unknown user:" + user.getUserName());
	}
	
	JSONObject data = new JSONObject();
	IKonto util = new KontoDBUtil();
	ArrayList<Konto> kontoList = util.getKontoForUser(user);
	
	return BuildResponse.buildOKResponse(data.put("konto", kontoList).toString());

    }
}
