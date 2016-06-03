package konto.data.DBUtil;

import java.sql.ResultSet;
import java.util.Date;

import konto.data.model.LoginUser;

public interface IReport {
    
    public ResultSet getMonthReport(LoginUser user, Date monthYear);
    
    public ResultSet getYearReport(LoginUser user, Date year);

}
