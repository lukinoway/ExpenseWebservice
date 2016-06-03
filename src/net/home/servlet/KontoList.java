package net.home.servlet;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import konto.data.DBUtil.IKonto;
import konto.data.DBUtil.IUser;
import konto.data.DBUtil.KontoDBUtil;
import konto.data.DBUtil.UserDBUtil;
import konto.data.model.Konto;
import konto.data.model.LoginUser;

@Path("konto")
public class KontoList {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{user}/pass/{pass}")
    public String getCategoryList(@PathParam("user") String username, @PathParam("pass") String pwd) {

	// check user
	LoginUser user = new LoginUser(username, pwd);
	IUser userUtil = new UserDBUtil();

	if (userUtil.validateLogin(user)) {
	    userUtil.loadUserId(user);
	    JSONObject data = new JSONObject();
	    IKonto util = new KontoDBUtil();
	    ArrayList<Konto> kontoList = util.getKontoForUser(user);
	    return data.put("konto", kontoList).toString();
	} else {
	    return "wrong user / password";
	}

    }
}
