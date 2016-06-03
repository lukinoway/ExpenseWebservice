package konto.data.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import konto.data.model.LoginUser;
import konto.data.model.Transaktion;

/**
 * Author: lukinoway Function Interface which should be used for Transaktion
 * Operation
 */
public interface ITransaktion {

    public void createTransaktion(Transaktion transaktion);

    public void updateTransaktion(Transaktion transaktion);

    public void deleteTransaktion(Transaktion transaktion);
   
    public ArrayList<Transaktion> getAllTransaktionsForUser(LoginUser user);
    
    public ArrayList<Transaktion> getTransaktionsForKontoCategory(int kontoId, int categoryId);
    
    public ArrayList<Transaktion> getTransaktionsForDateKontoCategory(Date begin, Date end, int kontoId, int categoryId);
    
    public ArrayList<Transaktion> getTransaktionsForMonthKontoCategory(Date monthYear, int kontoId, int categoryId);
    
    public ArrayList<Transaktion> getTransaktionsForYearKontoCategory(Date year, int kontoId, int categoryId);
    
    public ArrayList<Transaktion> getLast10TransaktionsForUser(LoginUser user);
    
    public ResultSet getReport(LoginUser user);

}
