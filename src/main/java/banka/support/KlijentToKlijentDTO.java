package banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import banka.model.Klijent;
import banka.web.dto.KlijentDTO;


@Component
public class KlijentToKlijentDTO implements Converter<Klijent, KlijentDTO> {

	@Override
	public KlijentDTO convert(Klijent klijent) {
		if(klijent==null){
			return null;
		}
		
		KlijentDTO dto = new KlijentDTO();
				
		dto.setId(klijent.getId());
		dto.setImeprezime(klijent.getImeprezime());
		dto.setMesto(klijent.getMesto());
		dto.setAdresa(klijent.getAdresa());
		dto.setJmbg(klijent.getJmbg());
		dto.setTelefon(klijent.getTelefon());
		dto.setMobilni(klijent.getMobilni());
		dto.setBrojracuna(klijent.getBrojracuna());
		dto.setStanje(klijent.getStanje());
		
		return dto;
	}
	
	public List<KlijentDTO> convert(List<Klijent> klijenti){
		List<KlijentDTO> ret = new ArrayList<>();
		
		for(Klijent p: klijenti){
			ret.add(convert(p));
		}
		
		return ret;
	}


}
