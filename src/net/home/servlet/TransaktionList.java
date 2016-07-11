package net.home.servlet;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import konto.data.DBUtil.ITransaktion;
import konto.data.DBUtil.IUser;
import konto.data.DBUtil.TransaktionDBUtil;
import konto.data.DBUtil.UserDBUtil;
import konto.data.Util.BuildResponse;
import konto.data.model.LoginUser;
import konto.data.model.Transaktion;

@Path("transaktionlist")
public class TransaktionList {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all/user/{user}/pass/{pass}")
    public Response getAllTransaktions(@PathParam("user") String username,
	    			     @PathParam("pass") String pwd) {
	// check user
	LoginUser user = new LoginUser(username, pwd);
	IUser userUtil = new UserDBUtil();
	
	if (userUtil.validateLogin(user)) {
	    userUtil.loadUserId(user);
	    JSONObject data = new JSONObject();
	    ITransaktion transaktionUtil = new TransaktionDBUtil();
	    ArrayList<Transaktion> transaktionList = transaktionUtil.getAllTransaktionsForUser(user);
	    return BuildResponse.buildOKResponse(data.put("transaktion", transaktionList).toString());
	} else {
	    return BuildResponse.buildErrorReposne("wrong user / password");
	}
	
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/last10/user/{user}/pass/{pass}")
    public Response getLast10Transaktions(@PathParam("user") String username,
	    			     @PathParam("pass") String pwd) {
	// check user
	LoginUser user = new LoginUser(username, pwd);
	IUser userUtil = new UserDBUtil();
	
	if (userUtil.validateLogin(user)) {
	    userUtil.loadUserId(user);
	    JSONObject data = new JSONObject();
	    ITransaktion transaktionUtil = new TransaktionDBUtil();
	    ArrayList<Transaktion> transaktionList = transaktionUtil.getLast10TransaktionsForUser(user);
	    return BuildResponse.buildOKResponse(data.put("transaktion", transaktionList).toString());
	} else {
	    return BuildResponse.buildErrorReposne("wrong user / password");
	}
	
    }

}
