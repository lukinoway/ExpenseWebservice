package konto.data.DBUtil;

import java.util.ArrayList;

import konto.data.model.LoginUser;
import konto.data.model.PaymentOrder;

public interface IPayment {
    
    public void createPayment(PaymentOrder payment);
    
    public void updatePayment(PaymentOrder payment);
    
    public void updatePaymentStatus(PaymentOrder payment);
    
    public void deletePayment(PaymentOrder payment);
    
    public ArrayList<PaymentOrder> getAllPaymentsForUser(LoginUser user);
    
    public ArrayList<PaymentOrder> getOpenPaymentsForUser(LoginUser user);

}
