package konto.data.Util;

import javax.ws.rs.core.Response;

public class BuildResponse {
    
    public static Response buildOKResponse(Object object) {
	return Response.ok().entity(object)
		.build();
    }
    
    public static Response buildErrorReposne(String text) {
	return Response.serverError().entity(text)
		.build();
    }

}
