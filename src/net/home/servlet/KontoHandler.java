package net.home.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import konto.data.DBUtil.IKonto;
import konto.data.DBUtil.KontoDBUtil;
import konto.data.model.Konto;

@Path("kontohandler")
public class KontoHandler {
    
    @POST
    @Path("/addkonto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addKonto(String input) {
	try {
	    System.out.println("input <" + input + ">");
	    Konto konto = buildKonto(input);
	    
	    // now add the konto to DB
	    IKonto util = new KontoDBUtil();
	    util.createKonto(konto);
	    
	    return "created new konto; ID=" + konto.getKontoId();
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while adding new konto:\n" + e.toString();
	}
    }
    
    @POST
    @Path("/updatekonto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateKonto(String input) {
	try {
	    System.out.println("input <" + input + ">");
	    Konto konto = buildKonto(input);
	    
	    // now add the konto to DB
	    IKonto util = new KontoDBUtil();
	    util.updateKonto(konto);
	    
	    return "updated konto; ID=" + konto.getKontoId();
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while update of konto:\n" + e.toString();
	}
    }
    
    private Konto buildKonto(String in) {
	JSONObject elem = new JSONObject(in);
	String knr = elem.getString("kontoNr");
	String kname = elem.getString("kontoName");
	boolean visible = elem.getBoolean("visible");
	String kontoTransferInfo = elem.getString("kontoTransferInfo");
	String bankURL = elem.getString("bankURL");
	Integer user = elem.getInt("userId");
	return new Konto(knr, kname, user, visible, kontoTransferInfo, bankURL);
	
    }

}
