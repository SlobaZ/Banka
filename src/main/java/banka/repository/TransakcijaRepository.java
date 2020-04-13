package banka.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import banka.model.Transakcija;

@Repository
public interface TransakcijaRepository extends JpaRepository<Transakcija , Integer>{
	
	@Query("SELECT t FROM Transakcija t WHERE "
			+ "(:uplatilac IS NULL or t.uplatilac.imeprezime like :uplatilac ) AND "
			+ "(:primalac IS NULL OR t.primalac.imeprezime like :primalac) AND "
			+ "(:uplatilacMesto IS NULL or t.uplatilac.mesto like :uplatilacMesto ) AND "
			+ "(:primalacMesto IS NULL OR t.primalac.mesto like :primalacMesto) AND "
			+ "(:iznosMin IS NULL or t.iznos >= :iznosMin ) AND "
			+ "(:iznosMax IS NULL or t.iznos <= :iznosMax ) AND "
			+ "(:datumVremePocetak IS NULL or t.datumvreme >= :datumVremePocetak ) AND "
			+ "(:datumVremeKraj IS NULL or t.datumvreme <= :datumVremeKraj ) "
			)
	Page<Transakcija> search(
			@Param("uplatilac") String uplatilac, 
			@Param("primalac") String primalac, 
			@Param("uplatilacMesto") String uplatilacMesto, 
			@Param("primalacMesto") String primalacMesto, 
			@Param("iznosMin") Double iznosMin,
			@Param("iznosMax") Double iznosMax,
			@Param("datumVremePocetak") Timestamp datumVremePocetak,
			@Param("datumVremeKraj") Timestamp datumVremeKraj,
			Pageable pageRequest);

}
