package net.home.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import konto.data.DBUtil.CategoryDBUtil;
import konto.data.DBUtil.ICategory;
import konto.data.Util.BuildResponse;
import konto.data.model.Category;

@Provider
@Path("categoryhandler")
public class CategoryHandler {
    
    @POST
    @Path("/addcategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCategory(String input) {
	try {
	    System.out.println("input <" + input + ">");
	    ICategory catUtil = new CategoryDBUtil();
	    Category category = buildCategory(input);
	    catUtil.createCategory(category);
	    
	    return BuildResponse.buildOKResponse("created new category; ID=" + category.getTypeId());
	} catch(Exception e) {
	    e.printStackTrace();
	    return BuildResponse.buildErrorReposne("error while adding new konto:\n" + e.toString());
	}
    }
    
    @POST
    @Path("/updatecategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(String input) {
	try {
	    System.out.println("input <" + input + ">");
	    ICategory catUtil = new CategoryDBUtil();
	    Category category = buildCategory(input);
	    catUtil.updateCategory(category);
	    
	    return BuildResponse.buildOKResponse("updated category");
	} catch(Exception e) {
	    e.printStackTrace();
	    return BuildResponse.buildErrorReposne("error while updating konto:\n" + e.toString());
	}
    }
    
    @POST
    @Path("/deletecategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategory(String input) {
	try {
	    System.out.println("input <" + input + ">");
	    ICategory catUtil = new CategoryDBUtil();
	    Category category = buildCategory(input);
	    catUtil.deleteCategory(category);
	    
	    return BuildResponse.buildOKResponse("deleted category; ID=" + category.getTypeId());
	} catch(Exception e) {
	    e.printStackTrace();
	    return BuildResponse.buildErrorReposne("error while deleting konto:\n" + e.toString());
	}
    }
    
    
    private Category buildCategory(String in) throws Exception{
	ObjectMapper mapper = new ObjectMapper();
	return mapper.readValue(in, Category.class);
    }

}
