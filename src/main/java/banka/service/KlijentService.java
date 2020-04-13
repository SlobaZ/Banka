package banka.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import banka.model.Klijent;

public interface KlijentService {
	
	
	Klijent getOne(Integer id);
	List<Klijent> findAll();
	Page<Klijent> findAll(int pageNum);
	Klijent save(Klijent klijent);
	Klijent delete(Integer id);
	
	Page<Klijent> search(
			@Param("imeprezime") String imeprezime, 
			@Param("mesto") String mesto, 
			@Param("stanje") Double stanje,
			 int pageNum);
	
	Klijent uzmiKredit(Integer id , double iznosKredita , int trajanje);

}
