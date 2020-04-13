package banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import banka.model.Transakcija;
import banka.utils.PomocnaKlasa;
import banka.web.dto.TransakcijaDTO;



@Component
public class TransakcijaToTransakcijaDTO implements Converter<Transakcija, TransakcijaDTO> {

	@Override
	public TransakcijaDTO convert(Transakcija transakcija) {
		if(transakcija==null){
			return null;
		}
		
		TransakcijaDTO dto = new TransakcijaDTO();
			
		dto.setId(transakcija.getId());
		dto.setUplatilacId(transakcija.getUplatilac().getId());
		dto.setUplatilacImePrezime(transakcija.getUplatilac().getImeprezime());
		dto.setUplatilacMesto(transakcija.getUplatilac().getMesto());
		dto.setPrimalacId(transakcija.getPrimalac().getId());
		dto.setPrimalacImePrezime(transakcija.getPrimalac().getImeprezime());
		dto.setPrimalacMesto(transakcija.getPrimalac().getMesto());
		dto.setIznos(transakcija.getIznos());
		if(transakcija.getDatumvreme()==null) {
			dto.setDatumvreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
			dto.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
		}
		else {
			dto.setDatumvreme(transakcija.getDatumvreme());
			dto.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(transakcija.getDatumvreme()));
		}

		return dto;
	}
	
	public List<TransakcijaDTO> convert(List<Transakcija> transakcije){
		List<TransakcijaDTO> ret = new ArrayList<>();
		
		for(Transakcija t: transakcije){
			ret.add(convert(t));
		}
		
		return ret;
	}

}
