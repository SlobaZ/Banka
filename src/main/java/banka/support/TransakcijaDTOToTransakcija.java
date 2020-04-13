package banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import banka.model.Klijent;
import banka.model.Transakcija;
import banka.service.KlijentService;
import banka.service.TransakcijaService;
import banka.utils.PomocnaKlasa;
import banka.web.dto.TransakcijaDTO;



@Component
public class TransakcijaDTOToTransakcija implements Converter<TransakcijaDTO, Transakcija> {
	
	@Autowired
	private TransakcijaService transakcijaService;
	
	@Autowired
	private KlijentService klijentService;

	@Override
	public Transakcija convert(TransakcijaDTO dto) {
		
		Klijent uplatilac = klijentService.getOne(dto.getUplatilacId());
		Klijent primalac = klijentService.getOne(dto.getPrimalacId());
		
		if(uplatilac!=null && primalac!=null) {
			
			Transakcija transakcija = null;
			
			if(dto.getId() != null) {
				transakcija = transakcijaService.getOne(dto.getId());
			}
			else {
				transakcija = new Transakcija();
			}
			
			transakcija.setId(dto.getId());
			transakcija.setIznos(dto.getIznos());
			if(dto.getDatumvreme()==null) {
				transakcija.setDatumvreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
				transakcija.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
			}
			if(dto.getDatumvreme()!=null) {
			transakcija.setDatumvreme(PomocnaKlasa.KonvertujStringUSqlDatumIVreme(dto.getDatetime()));
			transakcija.setDatetime(dto.getDatetime());
			}
			transakcija.setUplatilac(uplatilac);
			transakcija.setPrimalac(primalac);
			
			return transakcija;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
		
		
	
	}
	
	public List<Transakcija> convert (List<TransakcijaDTO> dtoTransakcije){
		List<Transakcija> transakcije = new ArrayList<>();
		
		for(TransakcijaDTO dto : dtoTransakcije){
			transakcije.add(convert(dto));
		}
		
		return transakcije;
	}

}
