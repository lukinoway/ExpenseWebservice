package net.home.servlet;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import konto.data.DBUtil.CategoryDBUtil;
import konto.data.DBUtil.ICategory;
import konto.data.model.Category;

@Path("categorylist")
public class CategoryList {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategoryList() {
	
	JSONObject data = new JSONObject();
	ICategory util = new CategoryDBUtil();
	ArrayList<Category> categoryList =  util.getAllCategories();
	return data.put("category", categoryList).toString();
    }

}
