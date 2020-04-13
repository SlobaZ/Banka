package banka.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="klijent")
public class Klijent {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column(nullable=false)
	private String imeprezime;
	@Column(nullable=false)
	private String mesto;
	@Column(nullable=false)
	private String adresa;
	@Column(nullable=false)
	private String jmbg;
	@Column(nullable=false)
	private String telefon;
	@Column(nullable=false)
	private String mobilni;
	@Column(nullable=false)
	private String brojracuna;
	@Column(nullable=false)
	private Double stanje;
	
	@OneToMany(mappedBy="uplatilac", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Transakcija> transakcijeuplatioca = new ArrayList<>();
	
	@OneToMany(mappedBy="primalac", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Transakcija> transakcijeprimaoca = new ArrayList<>();
	
		
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
	
	public List<Transakcija> getTransakcijeuplatioca() {
		return transakcijeuplatioca;
	}
	public void setTransakcijeuplatioca(List<Transakcija> transakcijeuplatioca) {
		this.transakcijeuplatioca = transakcijeuplatioca;
	}
	public void addTransakcijaUplatioca(Transakcija transakcijauplatioca) {
		if(transakcijauplatioca.getUplatilac() != this) {
			transakcijauplatioca.setUplatilac(this);
		}
		transakcijeuplatioca.add(transakcijauplatioca);
	}
	
	public List<Transakcija> getTransakcijeprimaoca() {
		return transakcijeprimaoca;
	}
	public void setTransakcijeprimaoca(List<Transakcija> transakcijeprimaoca) {
		this.transakcijeprimaoca = transakcijeprimaoca;
	}
	public void addTransakcija(Transakcija transakcijaprimaoca) {

		if(transakcijaprimaoca.getPrimalac() != this) {
			transakcijaprimaoca.setPrimalac(this);
		}
		transakcijeprimaoca.add(transakcijaprimaoca);
	}
	
	
	
	

}
