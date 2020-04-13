package banka.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import banka.model.Klijent;
import banka.model.Transakcija;
import banka.repository.KlijentRepository;
import banka.repository.TransakcijaRepository;
import banka.service.TransakcijaService;
import banka.utils.PomocnaKlasa;

@Service
public class JpaTransakcijaService implements TransakcijaService{
	
	@Autowired
	private TransakcijaRepository transakcijaRepository;
	
	@Autowired
	private KlijentRepository klijentRepository;

	@Override
	public Transakcija getOne(Integer id) {
		return transakcijaRepository.getOne(id);
	}

	@Override
	public List<Transakcija> findAll() {
		return transakcijaRepository.findAll();
	}

	@Override
	public Page<Transakcija> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return transakcijaRepository.findAll(pageable);
	}

	@Override
	public Transakcija save(Transakcija transakcija) {		
		return transakcijaRepository.save(transakcija);
	}

	@Override
	public Transakcija delete(Integer id) {
		Transakcija Transakcija = transakcijaRepository.getOne(id);
		if( Transakcija != null) {
			transakcijaRepository.delete(Transakcija);
		}
		return Transakcija;
	}

	@Override
	public Page<Transakcija> search(String uplatilac, String primalac, String uplatilacMesto , String primalacMesto , 
									Double iznosMin, Double iznosMax, String datumvremePocetak, String datumvremeKraj, int pageNum) {
		Timestamp datumVremePocetak = null;
		Timestamp datumVremeKraj = null;
		
		if(uplatilac != null) {
			uplatilac = '%' + uplatilac + '%';
		}
		if(primalac != null) {
			primalac = '%' + primalac + '%';
		}
		if(uplatilacMesto != null) {
			uplatilacMesto = '%' + uplatilacMesto + '%';
		}
		if(primalacMesto != null) {
			primalacMesto = '%' + primalacMesto + '%';
		}
		if(datumvremePocetak != null) {
		datumVremePocetak = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremePocetak);
		}
		if(datumvremeKraj !=null) {
			 datumVremeKraj = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremeKraj);
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return transakcijaRepository.search(uplatilac, primalac, uplatilacMesto , primalacMesto , iznosMin, iznosMax , datumVremePocetak, datumVremeKraj, pageable);
	}

	
	
	@Override
	public Transakcija uplati(Transakcija transakcija) {

		Klijent uplatilac = transakcija.getUplatilac();
		Klijent primalac = transakcija.getPrimalac();
		
		if(uplatilac.getStanje() < transakcija.getIznos()) {
			return null;
		}
				
		uplatilac.setStanje(uplatilac.getStanje() - transakcija.getIznos());
		klijentRepository.save(uplatilac);
		primalac.setStanje(primalac.getStanje() + transakcija.getIznos());
		klijentRepository.save(primalac);
		
		return transakcijaRepository.save(transakcija);
		
	}

	
	
	

	
	
	
	
	
	

}
