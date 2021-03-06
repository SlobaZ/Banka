package banka.utils;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PomocnaKlasa {
	
	
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	

	// Upisi sadasnji Sql datum i vreme 
	public static java.sql.Timestamp  UpisiSadasnjiDatumIVremeSql() {
		Date date = new Date();  
        Timestamp datumIvremeSada=new Timestamp(date.getTime());     
	  return datumIvremeSada;
	}
	
	// Konvertuj Sql datum i vreme u String
	public static String  PrikaziTekstualnoDatumIVreme(Timestamp datumIvreme) {
		String tekst = DATE_TIME_FORMAT.format(datumIvreme);
		// String tekst = datumIvreme.toString(); 
		return tekst;
	}
	
	// Konvertuj String u Sql datum i vreme
	public static java.sql.Timestamp  KonvertujStringUSqlDatumIVreme(String tekst){
		java.util.Date dateTime = null;
		try {
			dateTime = DATE_TIME_FORMAT.parse(tekst);
		} catch (Exception ex) {
			System.out.println("GRESKA");
		}
		Timestamp datumIvreme = new Timestamp(dateTime.getTime());  // Timestamp.valueOf(tekst);
		return datumIvreme;
	}

	
}
