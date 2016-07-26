package konto.data.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import konto.data.model.Category;

public class CategoryDBUtil extends DBCommunicator implements ICategory {

    private static final long serialVersionUID = 1L;
    private ResultSet resSet = null;
    private PreparedStatement pStmt = null;

    public CategoryDBUtil() {
	super();
    }

    @Override
    public void createCategory(Category category) {
	try {
	    String pSql = "insert into db_transaktion_type(type_text) values(?)";
	    pStmt = connect.prepareStatement((pSql), Statement.RETURN_GENERATED_KEYS);
	    pStmt.setString(1, category.getTypeText());
	    pStmt.executeUpdate();

	    resSet = pStmt.getGeneratedKeys();
	    resSet.next();

	    category.setTypeId(resSet.getInt(1));
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

    }
    
    public void updateCategory(Category category) {
	try {
	    String pSql = "update db_transaktion_type set type_text = ? where type_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setString(1, category.getTypeText());
	    pStmt.setInt(2, category.getTypeId());
	    pStmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}
    }

    @Override
    public void deleteCategory(Category category) {
	try {
	    String pSql = "delete from db_transaktion_type where type_id = ?";
	    pStmt = connect.prepareStatement(pSql);
	    pStmt.setInt(1, category.getTypeId());
	    pStmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

    }

    @Override
    public ArrayList<Category> getAllCategories() {
	ArrayList<Category> categoryList = new ArrayList<Category>();
	try {
	    String pSql = "select type_id, type_text from db_transaktion_type order by type_text";
	    pStmt = connect.prepareStatement(pSql);
	    resSet = pStmt.executeQuery();

	    while (resSet.next()) {
		categoryList.add(new Category(resSet.getInt(1), resSet.getString(2)));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    close();
	}

	return categoryList;
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
