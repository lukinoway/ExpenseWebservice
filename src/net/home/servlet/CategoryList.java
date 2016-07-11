package net.home.servlet;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import konto.data.DBUtil.CategoryDBUtil;
import konto.data.DBUtil.ICategory;
import konto.data.Util.BuildResponse;
import konto.data.model.Category;

@Path("categorylist")
public class CategoryList {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryList() {
	
	JSONObject data = new JSONObject();
	ICategory util = new CategoryDBUtil();
	ArrayList<Category> categoryList =  util.getAllCategories();
	return BuildResponse.buildOKResponse(data.put("category", categoryList).toString());
    }

}
