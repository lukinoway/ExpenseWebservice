package konto.data.Util;

public class DateConverter {

//    public static java.sql.Date convertLocalDateToSqlDate(LocalDate localdate) {
//	LocalDate ld = localdate;
//	Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
//	Date res = Date.from(instant);
//	return new java.sql.Date(res.getTime());
//    }
//
//    public static LocalDate convertDateToLocalDate(Date date) {
//	// date conversion
//	Instant instant = Instant.ofEpochMilli(date.getTime());
//	return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
//    }
    
    public static java.sql.Date convertUtilToSqlDate(java.util.Date utilDate) {
	return new java.sql.Date(utilDate.getTime());
    }
    
    public static java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
	return new java.util.Date(sqlDate.getTime());
    }
    
}
