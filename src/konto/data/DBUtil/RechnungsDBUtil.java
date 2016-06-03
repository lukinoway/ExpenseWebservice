package konto.data.DBUtil;

import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import konto.data.model.Rechnung;

public class RechnungsDBUtil extends DBCommunicator implements IRechnung {

    private static final long serialVersionUID = 1L;
    private ResultSet resSet = null;
    private PreparedStatement pStmt = null;

    public RechnungsDBUtil() {
	super();
    }

    @Override
    public void insertRechnung(Rechnung rechnung) {
	try {
	    String pSql = "insert into db_transkation_rechnung(transaktion_id, rechnung_data, rechnung_data_extension) "
		    	+ "values(?,?,?)";
	    pStmt = connect.prepareStatement(pSql, Statement.RETURN_GENERATED_KEYS);
	    //pStmt.setInt(1, rechnung.get);
	    
	} catch(Exception e) {
	    
	} finally {
	    close();
	}
	
    }

    @Override
    public void updateRechnung(Rechnung rechnung) {
	try {
	    
	} catch(Exception e) {
	    
	} finally {
	    close();
	}
	
    }

    @Override
    public void deleteRechnung(int billId) {
	try {
	    
	} catch(Exception e) {
	    
	} finally {
	    close();
	}
	
    }

    @Override
    public ByteArrayOutputStream donwloadRechnung(int billId) {
	try {
	    
	} catch(Exception e) {
	    
	} finally {
	    close();
	}
	return null;
    }
    
    // Close everything
    protected void close() {
	try {
	    if (resSet != null) {
		resSet.close();
	    }
	    if (pStmt != null) {
		pStmt.close();
	    }
	    super.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
