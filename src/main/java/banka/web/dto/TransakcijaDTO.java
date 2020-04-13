package banka.web.dto;

import java.sql.Timestamp;

public class TransakcijaDTO {

	private Integer id;
	
	private Integer uplatilacId;
	private String uplatilacImePrezime;
	private String uplatilacMesto;
	
	private Integer primalacId;
	private String primalacImePrezime;
	private String primalacMesto;
	
	private Double iznos;
	private Timestamp datumvreme;
	private String datetime;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUplatilacId() {
		return uplatilacId;
	}

	public void setUplatilacId(Integer uplatilacId) {
		this.uplatilacId = uplatilacId;
	}
	
	public String getUplatilacImePrezime() {
		return uplatilacImePrezime;
	}

	public void setUplatilacImePrezime(String uplatilacImePrezime) {
		this.uplatilacImePrezime = uplatilacImePrezime;
	}
	
	public String getUplatilacMesto() {
		return uplatilacMesto;
	}

	public void setUplatilacMesto(String uplatilacMesto) {
		this.uplatilacMesto = uplatilacMesto;
	}

	public Integer getPrimalacId() {
		return primalacId;
	}

	public void setPrimalacId(Integer primalacId) {
		this.primalacId = primalacId;
	}
	
	public String getPrimalacImePrezime() {
		return primalacImePrezime;
	}

	public void setPrimalacImePrezime(String primalacImePrezime) {
		this.primalacImePrezime = primalacImePrezime;
	}

	public String getPrimalacMesto() {
		return primalacMesto;
	}

	public void setPrimalacMesto(String primalacMesto) {
		this.primalacMesto = primalacMesto;
	}

	public Double getIznos() {
		return iznos;
	}

	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}

	public Timestamp getDatumvreme() {
		return datumvreme;
	}

	public void setDatumvreme(Timestamp datumvreme) {
		this.datumvreme = datumvreme;
	}

	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	

		
	

}
