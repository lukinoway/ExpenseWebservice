package net.home.servlet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
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
	    
	    IKonto util = new KontoDBUtil();
	    util.updateKonto(konto);
	    
	    return "updated konto; ID=" + konto.getKontoId();
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while update of konto:\n" + e.toString();
	}
    }
    
    @POST
    @Path("/deletekonto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteKonto(String input) {
	try {
	    System.out.println("input <" + input + ">");
	    Konto konto = buildKonto(input);
	    
	    IKonto util = new KontoDBUtil();
	    util.deleteKonto(konto);
	    
	    return "deleted konto; ID=" + konto.getKontoId();
	} catch(Exception e) {
	    e.printStackTrace();
	    return "error while delete of konto:\n" + e.toString();
	}
    }
    
    private Konto buildKonto(String in) throws Exception {
	ObjectMapper mapper = new ObjectMapper();
	return mapper.readValue(in, Konto.class);
    }

}
