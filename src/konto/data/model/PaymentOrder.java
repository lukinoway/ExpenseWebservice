package konto.data.model;

import java.io.Serializable;
import java.util.Date;

public class PaymentOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int paymentId;
    private int erstellerKontoId;
    private int schuldnerKontoId;
    private String paymentText;
    private double betrag;
    private Date date;
    private int status;

    /**
     * Payment order Class - enter creator and konto from other person 
     * @param text
     * @param erstellerId
     * @param schuldnerId
     * @param betrag
     * @param date
     */
    public PaymentOrder(String text, int erstellerId, int schuldnerId, double betrag, Date date) {
	this.paymentText = text;
	this.erstellerKontoId = erstellerId;
	this.schuldnerKontoId = schuldnerId;
	this.betrag = betrag;
	this.date = date;
	//this.setStatus(PaymentStatus.NEU);
	
	this.paymentId = 0;
    }
    
    public PaymentOrder(int paymentId,String text, int erstellerId, int schuldnerId, double betrag, Date date) {
	this.paymentText = text;
	this.erstellerKontoId = erstellerId;
	this.schuldnerKontoId = schuldnerId;
	this.betrag = betrag;
	this.date = date;
	//this.status = PaymentStatus.NEU;
	this.paymentId = paymentId;
    }
    
    public PaymentOrder(int paymentId,String text, int erstellerId, int schuldnerId, double betrag, Date date, int status) {
	this.paymentText = text;
	this.erstellerKontoId = erstellerId;
	this.schuldnerKontoId = schuldnerId;
	this.betrag = betrag;
	this.date = date;
	this.status = status;
	
	
	this.paymentId = paymentId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getErstellerKontoId() {
        return erstellerKontoId;
    }

    public void setErstellerKontoId(int erstellerKontoId) {
        this.erstellerKontoId = erstellerKontoId;
    }

    public int getSchuldnerKontoId() {
        return schuldnerKontoId;
    }

    public void setSchuldnerKontoId(int schuldnerKontoId) {
        this.schuldnerKontoId = schuldnerKontoId;
    }

    public String getPaymentText() {
        return paymentText;
    }

    public void setPaymentText(String paymentText) {
        this.paymentText = paymentText;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
	return status;
    }

    public void setStatus(int status) {
	this.status = status;
    }
    
}
