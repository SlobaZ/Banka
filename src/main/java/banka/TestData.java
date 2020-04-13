package banka;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import banka.model.Klijent;
import banka.model.Transakcija;
import banka.service.KlijentService;
import banka.service.TransakcijaService;
import banka.utils.PomocnaKlasa;

@Component
public class TestData {
	
	@Autowired
	private KlijentService klijentService;
	
	@Autowired
	private TransakcijaService transakcijaService;
		
	@PostConstruct
	public void init() {
		
		Klijent klijent1 = new Klijent();
		klijent1.setImeprezime("Pera Peric");
		klijent1.setMesto("Novi Sad");
		klijent1.setAdresa("Cara Lazara 81");
		klijent1.setJmbg("1106970775533"); 
		klijent1.setTelefon("021450450");
		klijent1.setMobilni("0641122335");
		klijent1.setBrojracuna("300-123456-50");
		klijent1.setStanje(50000.0);
		klijent1 = klijentService.save(klijent1);
		
		
		Klijent klijent2 = new Klijent();
		klijent2.setImeprezime("Jova Jovic");
		klijent2.setMesto("Beograd");
		klijent2.setAdresa("Vojvode Misica 54");
		klijent2.setJmbg("2208960335577"); 
		klijent2.setTelefon("011450450");
		klijent2.setMobilni("0631122335");
		klijent2.setBrojracuna("300-236541-33");
		klijent2.setStanje(70000.0);
		klijent2 = klijentService.save(klijent2);
		
		Klijent klijent3 = new Klijent();
		klijent3.setImeprezime("Vasa Vasic");
		klijent3.setMesto("Subotica");
		klijent3.setAdresa("Jablanicka 17");
		klijent3.setJmbg("0710950775533"); 
		klijent3.setTelefon("024450450");
		klijent3.setMobilni("0651122335");
		klijent3.setBrojracuna("300-223344-44");
		klijent3.setStanje(60000.0);
		klijent3 = klijentService.save(klijent3);
		
		Transakcija transakcija1 = new Transakcija();
		transakcija1.setUplatilac(klijent1);
		transakcija1.setPrimalac(klijent2);
		transakcija1.setIznos(1000.0);
		transakcija1.setDatumvreme(java.sql.Timestamp.valueOf("2018-09-23 10:10:10"));
		transakcija1.setDatetime("23.09.2018. 10:10");
		transakcija1 = transakcijaService.save(transakcija1);
		
		Transakcija transakcija2 = new Transakcija();
		transakcija2.setUplatilac(klijent2);
		transakcija2.setPrimalac(klijent3);
		transakcija2.setIznos(5000.0);
		transakcija2.setDatumvreme(java.sql.Timestamp.valueOf("2019-04-15 08:25:20"));
		transakcija2.setDatetime("15.04.2019. 08:25");
		transakcija2 = transakcijaService.save(transakcija2);
		
		Transakcija transakcija3 = new Transakcija();
		transakcija3.setUplatilac(klijent3);
		transakcija3.setPrimalac(klijent1);
		transakcija3.setIznos(12000.0);
		transakcija3.setDatumvreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()); 
		transakcija3.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
		transakcija3 = transakcijaService.save(transakcija3);
		
	}

}
