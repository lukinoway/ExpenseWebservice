package konto.data.DBUtil;

import java.io.ByteArrayOutputStream;

import konto.data.model.Rechnung;

public interface IRechnung {

    public void insertRechnung(Rechnung rechnung);

    public void updateRechnung(Rechnung rechnung);

    public void deleteRechnung(int billId);
    
    public ByteArrayOutputStream donwloadRechnung(int billId);

    //public String downloadRechnung(int billId);

}
