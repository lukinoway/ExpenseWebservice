package net.home.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import konto.data.DBUtil.ITransaktion;
import konto.data.DBUtil.TransaktionDBUtil;
import konto.data.model.Transaktion;

@Path("transaktionhandler")
public class TransaktionHandler {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addtransaktion")
    public String addTransaktion(String input) {
	System.out.println("input <" + input + ">");
	try {
	    ITransaktion transaktionUtil = new TransaktionDBUtil();
	    Transaktion transaktion = buildTransaktion(input);
	    transaktionUtil.createTransaktion(transaktion);
	    return "added transaktion; ID="+transaktion.getTransaktionsId();
	} catch (Exception e) {
	    e.printStackTrace();
	    return "Error while creation of transaktion:\n" + e.toString();
	}
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updatetransaktion")
    public String updateTransaktion(String input) {
	System.out.println("input <" + input + ">");
	try {
	    ITransaktion transaktionUtil = new TransaktionDBUtil();
	    Transaktion transaktion = buildTransaktion(input);
	    transaktionUtil.updateTransaktion(transaktion);
	    return transaktion.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	    return "Error while update of transaktion:\n" + e.toString();
	}
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletetransaktion")
    public String deleteTransaktion(String input) {
	System.out.println("input <" + input + ">");
	try {
	    ITransaktion transaktionUtil = new TransaktionDBUtil();
	    Transaktion transaktion = buildTransaktion(input);
	    transaktionUtil.deleteTransaktion(transaktion);
	    return "delete Transaktion: " + transaktion.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	    return "Error while update of transaktion:\n" + e.toString();
	}
    }

    private Transaktion buildTransaktion(String in) throws Exception {
	ObjectMapper mapper = new ObjectMapper();
	return mapper.readValue(in, Transaktion.class);
    }

}
