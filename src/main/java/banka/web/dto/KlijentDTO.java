package banka.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class KlijentDTO {
	
	private Integer id;
	@NotBlank
	@NotNull(message = "Ime i Prezime ne moze ostati prazno !")
	private String imeprezime;
	@NotBlank
	@NotNull(message = "Mesto ne moze ostati prazno !")
	private String mesto;
	@NotBlank
	@NotNull(message = "Adresa ne moze ostati prazna !")
	private String adresa;
	@NotBlank
	@NotNull
	@Size(min = 13, max = 13 , message = "JMBG mora imati tacno 13 cifara !")
	private String jmbg;
	@NotBlank
	@NotNull
	@Size(min = 9, max = 10 , message = "Telefon mora imati 9 ili 10 cifara !")
	private String telefon;
	@NotBlank
	@NotNull
	@Size(min = 9, max = 10 , message = "Telefon mora imati 9 ili 10 cifara !")
	private String mobilni;
	@NotBlank
	@NotNull(message = "Broj racuna ne moze ostati prazan !")
	private String brojracuna;
	@Min(value = 0 , message = "Stanje racuna ne moze biti manje od nule !")
	@Max(value = 1000000 , message = "Stanje racuna ne moze biti vece od 1.000.000 !")
	private Double stanje;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getImeprezime() {
		return imeprezime;
	}
	public void setImeprezime(String imeprezime) {
		this.imeprezime = imeprezime;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMobilni() {
		return mobilni;
	}
	public void setMobilni(String mobilni) {
		this.mobilni = mobilni;
	}
	public String getBrojracuna() {
		return brojracuna;
	}
	public void setBrojracuna(String brojracuna) {
		this.brojracuna = brojracuna;
	}
	public Double getStanje() {
		return stanje;
	}
	public void setStanje(Double stanje) {
		this.stanje = stanje;
	}
	
	
	

}
