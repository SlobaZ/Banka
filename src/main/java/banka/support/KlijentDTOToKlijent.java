package banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import banka.model.Klijent;
import banka.service.KlijentService;
import banka.web.dto.KlijentDTO;



@Component
public class KlijentDTOToKlijent implements Converter<KlijentDTO, Klijent> {
	
	@Autowired
	private KlijentService klijentService;

	@Override
	public Klijent convert(KlijentDTO dto) {
		Klijent klijent = null;
		
		if(dto.getId()!=null){
			klijent = klijentService.getOne(dto.getId());
			
			if(klijent == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant Klijent");
			}
		}
		else {
			klijent = new Klijent();
		}
		
		klijent.setId(dto.getId());
		klijent.setImeprezime(dto.getImeprezime());
		klijent.setMesto(dto.getMesto());
		klijent.setAdresa(dto.getAdresa());
		klijent.setJmbg(dto.getJmbg());
		klijent.setTelefon(dto.getTelefon());
		klijent.setMobilni(dto.getMobilni());
		klijent.setBrojracuna(dto.getBrojracuna());
		klijent.setStanje(dto.getStanje());
		
		return klijent;
	}
	
	public List<Klijent> convert (List<KlijentDTO> dtoKlijents){
		List<Klijent> klijenti = new ArrayList<>();
		
		for(KlijentDTO dto : dtoKlijents){
			klijenti.add(convert(dto));
		}
		
		return klijenti;
	}


}
