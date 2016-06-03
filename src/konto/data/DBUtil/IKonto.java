package konto.data.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;

import konto.data.model.Konto;
import konto.data.model.LoginUser;

public interface IKonto {

    public void createKonto(Konto konto);

    public void updateKonto(Konto konto);

    public void deleteKonto(Konto konto);
    
    public ArrayList<Konto> getKontoForUser(LoginUser user);
    
    public ArrayList<Konto> getVisibleKontosForUser(LoginUser user);
    
    public ArrayList<Konto> getVisibleKontos(LoginUser user);
    
    public int getUserIdforKonto(int kontoId);
    
    public String getTransferInformationforKonto(int kontoId);
    
    public String getBankURL(int kontoId);
    
    public HashMap<Integer, String> getUserNameforVisibleKonto();

}
