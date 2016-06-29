package konto.data.Util;

import java.util.Base64;
import java.util.StringTokenizer;

import konto.data.DBUtil.IUser;
import konto.data.DBUtil.UserDBUtil;
import konto.data.model.LoginUser;

/**
 * Main Class to  Basic Handle Auth
 * @author lpichle
 *
 */
public class AuthenticationService {

    public boolean authenticate(String authCred) {
	
	if(authCred == null) {
	    return false;
	}
	
	String userAndPass = null;
	try {
	    String encodedPass = authCred.replaceFirst("Basic" + " ", "");
	    byte[] decodebytes = Base64.getDecoder().decode(encodedPass);
	    userAndPass = new String(decodebytes, "UTF-8");
	    
	} catch(Exception e) {
	    e.printStackTrace();
	    return false;
	}
	final StringTokenizer tokenizer = new StringTokenizer(userAndPass, ":");
	final String username = tokenizer.nextToken();
	final String password = tokenizer.nextToken();
	
	IUser userUtil = new UserDBUtil();
	return userUtil.validateLogin(new LoginUser(username, password));
	
    }
}
