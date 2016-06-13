package net.home.servlet;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

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
	    return transaktion.toString();
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

    private Transaktion buildTransaktion(String in) {
	try {

	    JSONObject elem = new JSONObject(in);
	    String trText = elem.getString("transaktionsText");
	    Double trBetrag = elem.getDouble("transaktionsBetrag");
	    int trKonto = elem.getInt("kontoId");
	    int trType = elem.getInt("typeId");
	    // int trId = elem.getInt("transaktionsId");
	    String trDateStr = elem.getString("transaktionsDate");
	    // Date trDate = new Date(trDateStr);

	    // build transaktion
	    return new Transaktion(new Date(), trBetrag, trText, trKonto, trType);

	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

}
