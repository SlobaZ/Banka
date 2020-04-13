package banka.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import banka.model.Transakcija;

public interface TransakcijaService {
	
	Transakcija getOne(Integer id);
	List<Transakcija> findAll();
	Page<Transakcija> findAll(int pageNum);
	Transakcija save(Transakcija transakcija);
	Transakcija delete(Integer id);
	
	Page<Transakcija> search(
			@Param("uplatilac") String uplatilac, 
			@Param("primalac") String primalac, 
			@Param("uplatilacMesto") String uplatilacMesto, 
			@Param("primalacMesto") String primalacMesto, 
			@Param("iznosMin") Double iznosMin,
			@Param("iznosMax") Double iznosMax,
			@Param("datumvremePocetak") String datumvremePocetak,
			@Param("datumvremeKraj") String datumvremeKraj,
			 int pageNum);
	

	Transakcija uplati(Transakcija transakcija);

}
