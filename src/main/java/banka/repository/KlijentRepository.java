package banka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import banka.model.Klijent;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent, Integer>{
	
	@Query("SELECT k FROM Klijent k WHERE "
			+ "(:imeprezime IS NULL or k.imeprezime like :imeprezime ) AND "
			+ "(:mesto IS NULL OR k.mesto like :mesto) AND "
			+ "(:stanje IS NULL or k.stanje >= :stanje ) "
			)
	Page<Klijent> search(
			@Param("imeprezime") String imeprezime, 
			@Param("mesto") String mesto, 
			@Param("stanje") Double stanje,
			Pageable pageRequest);

}
