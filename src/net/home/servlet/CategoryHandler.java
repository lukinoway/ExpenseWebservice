package net.home.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import konto.data.DBUtil.CategoryDBUtil;
import konto.data.DBUtil.ICategory;
import konto.data.model.Category;

@Path("categoryhandler")
public class CategoryHandler {
    
    @POST
    @Path("/addcategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCategory(String input) {
	try {
	    ICategory catUtil = new CategoryDBUtil();
	    Category category = buildCategory(input);
	    catUtil.createCategory(category);
	    
	    return "created new category; ID=" + category.getTypeId();
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while adding new konto:\n" + e.toString();
	}
    }
    
    @POST
    @Path("/updatecategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateCategory(String input) {
	try {
	    ICategory catUtil = new CategoryDBUtil();
	    Category category = buildCategory(input);
	    catUtil.updateCategory(category);
	    
	    return "updated category";
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while adding new konto:\n" + e.toString();
	}
    }
    
    @POST
    @Path("/deletecategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCategory(String input) {
	try {
	    ICategory catUtil = new CategoryDBUtil();
	    Category category = buildCategory(input);
	    catUtil.deleteCategory(category);
	    
	    return "deleted category; ID=" + category.getTypeId();
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while adding new konto:\n" + e.toString();
	}
    }
    
    
    private Category buildCategory(String in) throws Exception{
	ObjectMapper mapper = new ObjectMapper();
	return mapper.readValue(in, Category.class);
    }

}
