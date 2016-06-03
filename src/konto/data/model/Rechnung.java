package konto.data.model;

import java.io.File;
import java.util.Date;

public class Rechnung {

    private final int rechnungsId;
    private final Date rechnungsDate;
    private final String rechnungsText;
    private File data = null;

    public Rechnung(int billId, Date billDate, String billText) {
	this.rechnungsId = billId;
	this.rechnungsDate = billDate;
	this.rechnungsText = billText;
	this.setRechnung(null);
    }

    public int getRechnungsId() {
	return this.rechnungsId;
    }

    public Date getRechnungsDatum() {
	return this.rechnungsDate;
    }

    public String getRechnungsText() {
	return this.rechnungsText;
    }

    public File getRechnung() {
	return data;
    }

    public void setRechnung(File data) {
	this.data = data;
    }
}
