package konto.data.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import konto.data.model.Konto;
import konto.data.model.LoginUser;

public class KontoDBUtil extends DBCommunicator implements IKonto {

    private static final long serialVersionUID = 1L;
    private ResultSet resSet = null;
    private PreparedStatement pStmt = null;

    public KontoDBUtil() {
	super();
    }

    @Override
    public void createKonto(Konto konto) {
	try {
	    String pSql = "insert into db_konto(konto_nr, konto_desc_text, owner, visible, konto_transfer_info, konto_bank_url) values(?, ?, ?, ?, ?, ?)";
	    pStmt = connect.prepareStatement((pSql), Statement.RETURN_GENERATED_KEYS);
	    pStmt.setString(1, konto.getKontoNr());
	    pStmt.setString(2, konto.getKontoName());
	    pStmt.setInt(3, konto.getUserId());
	    pStmt.setBoolean(4, konto.isVisible());
	    pStmt.setString(5, konto.getKontoTransferInfo());
	    pStmt.setString(6, konto.getBankURL());
	    pStmt.executeUpdate();

	    resSet = pStmt.getGeneratedKeys();
	    resSet.next();

	    konto.setKontoId(resSet.getInt(1));
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

    }

    @Override
    public void updateKonto(Konto konto) {
	try {
	    String pSql = "update db_konto set konto_nr = ?, konto_desc_text = ?, visible = ?, konto_transfer_info = ?, konto_bank_url = ?  where konto_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setString(1, konto.getKontoNr());
	    pStmt.setString(2, konto.getKontoName());
	    pStmt.setBoolean(3, konto.isVisible());
	    pStmt.setString(4, konto.getKontoTransferInfo());
	    pStmt.setString(5, konto.getBankURL());
	    pStmt.setInt(6, konto.getKontoId());
	    pStmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

    }

    @Override
    public void deleteKonto(Konto konto) {
	try {
	    String pSql = "delete from db_konto where konto_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, konto.getKontoId());
	    pStmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

    }

    @Override
    public ArrayList<Konto> getKontoForUser(LoginUser user) {
	ArrayList<Konto> kontoList = new ArrayList<Konto>();
	try {
	    String pSql = "select konto_id, konto_nr, konto_desc_text, owner, visible from db_konto where owner = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, user.getUserId());
	    resSet = pStmt.executeQuery();

	    
	    while (resSet.next()) {
		kontoList.add(new Konto(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getInt(4), resSet.getBoolean(5)));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

	return kontoList;
    }
    
    @Override
    public ArrayList<Konto> getVisibleKontosForUser(LoginUser user) {
	ArrayList<Konto> kontoList = new ArrayList<Konto>();
	try {
	    String pSql = "select konto_id, konto_nr, konto_desc_text, owner, visible from db_konto where owner = ? and visible = true";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, user.getUserId());
	    resSet = pStmt.executeQuery();

	    
	    while (resSet.next()) {
		kontoList.add(new Konto(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getInt(4), resSet.getBoolean(5)));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

	return kontoList;
    }
    
    @Override
    public ArrayList<Konto> getVisibleKontos(LoginUser user) {
	ArrayList<Konto> kontoList = new ArrayList<Konto>();
	try {
	    String pSql = "select konto_id, konto_nr, user_name || ' - ' || konto_desc_text, owner, visible "
		    	+ "  from db_user "
		    	+ "  join db_konto on owner = user_id "
		    	+ " where visible = true and user_id <> ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, user.getUserId());
	    resSet = pStmt.executeQuery();

	    while (resSet.next()) {
		kontoList.add(new Konto(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getInt(4), resSet.getBoolean(5)));
	    }


	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

	return kontoList;
    }
    
    
    @Override
    public int getUserIdforKonto(int kontoId) {
	int userId = 0;
	try {
	    String pSql = "select owner from db_konto where konto_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, kontoId);
	    resSet = pStmt.executeQuery();
	    
	    resSet.next();
	    userId = resSet.getInt(1);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}
	return userId;
    }
    
    @Override
    public String getTransferInformationforKonto(int kontoId) {
	String transferInfo = null;
	try {
	    String pSql = "select konto_transfer_info from db_konto where konto_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, kontoId);
	    resSet = pStmt.executeQuery();
	    
	    resSet.next();
	    transferInfo = resSet.getString(1);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}
	return transferInfo;
    }
    
    public String getBankURL(int kontoId) {
	String bankURL = null;
	try {
	    String pSql = "select konto_bank_url from db_konto where konto_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, kontoId);
	    resSet = pStmt.executeQuery();
	    
	    resSet.next();
	    bankURL = resSet.getString(1);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}
	return bankURL;
    }
    
    public HashMap<Integer, String> getUserNameforVisibleKonto() {
	HashMap<Integer, String> userNameMap = new HashMap<Integer, String>();
	
	try {
	    String pSql = "select konto_id, user_name || ' - ' || konto_desc_text"
		    	+ "  from db_user "
		    	+ "  join db_konto on owner = user_id "
		    	+ " where visible = true";
	    pStmt = connect.prepareStatement(pSql);
	    resSet = pStmt.executeQuery();

	    while (resSet.next()) {
		userNameMap.put(resSet.getInt(1), resSet.getString(2));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}
	return userNameMap;
    }


    // Close everything
    public void close() {
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
