package banka.model;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transakcija")
public class Transakcija {
	
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;

	@Column
	private Double iznos;
	@Column
	private Timestamp datumvreme;
	@Column
	private String datetime;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "uplatilac")
	private Klijent uplatilac;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "primalac")
	private Klijent primalac;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
	public Klijent getUplatilac() {
		return uplatilac;
	}
	public void setUplatilac(Klijent uplatilac) {
		this.uplatilac = uplatilac;
		if(!uplatilac.getTransakcijeuplatioca().contains(this)){
			uplatilac.getTransakcijeuplatioca().add(this);
		}
	}
	
	public Klijent getPrimalac() {
		return primalac;
	}
	public void setPrimalac(Klijent primalac) {
		this.primalac = primalac;
		if(!primalac.getTransakcijeprimaoca().contains(this)){
			primalac.getTransakcijeprimaoca().add(this);
		}
	}
	
	
	
	
	

}
