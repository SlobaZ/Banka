package banka.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import banka.model.Klijent;
import banka.repository.KlijentRepository;
import banka.service.KlijentService;

@Service
public class JpaKlijentService implements KlijentService{
	
	@Autowired
	private KlijentRepository klijentRepository;

	@Override
	public Klijent getOne(Integer id) {
		return klijentRepository.getOne(id); 
	}

	@Override
	public List<Klijent> findAll() {
		return klijentRepository.findAll();
	}

	@Override
	public Page<Klijent> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return klijentRepository.findAll(pageable);
	}

	@Override
	public Klijent save(Klijent klijent) {
		return klijentRepository.save(klijent);
	}

	@Override
	public Klijent delete(Integer id) {
		Klijent klijent = klijentRepository.getOne(id);
		if(klijent != null) {
			klijentRepository.delete(klijent);
		}
		return klijent;
	}

	@Override
	public Page<Klijent> search(String imeprezime, String mesto, Double stanje, int pageNum) {
		if(imeprezime != null) {
			imeprezime = '%' + imeprezime + '%';
		}
		if(mesto != null) {
			mesto = '%' + mesto + '%';
		}
		PageRequest pageable = PageRequest.of(pageNum, 5);
		return klijentRepository.search(imeprezime, mesto, stanje, pageable);
	}

	
	@Override
	public Klijent uzmiKredit(Integer id, double iznosKredita, int trajanje) {
		Klijent klijent = klijentRepository.getOne(id);
		Double mesecnaRata =  iznosKredita / ( trajanje * 12 );
		if (klijent.getStanje() > mesecnaRata) {
			klijent.setStanje(klijent.getStanje() - mesecnaRata);
		} else {
			return null;
		}
		
		klijentRepository.save(klijent);
		
		return klijent;
	}

}
