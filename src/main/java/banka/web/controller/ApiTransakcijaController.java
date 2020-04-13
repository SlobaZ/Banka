package banka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import banka.model.Klijent;
import banka.model.Transakcija;
import banka.service.KlijentService;
import banka.service.TransakcijaService;
import banka.support.TransakcijaDTOToTransakcija;
import banka.support.TransakcijaToTransakcijaDTO;
import banka.web.dto.TransakcijaDTO;



@RestController
@RequestMapping(value="/transakcije")
public class ApiTransakcijaController {

	@Autowired
	private TransakcijaService transakcijaService;
	
	@Autowired
	private TransakcijaToTransakcijaDTO toDTO;
	 
	@Autowired
	private TransakcijaDTOToTransakcija toTransakcija;
	
	@Autowired
	private KlijentService klijentService;
			

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<TransakcijaDTO>> getAll(
			@RequestParam(required=false) String uplatilac,
			@RequestParam(required=false) String primalac,
			@RequestParam(required=false) String uplatilacMesto,
			@RequestParam(required=false) String primalacMesto,
			@RequestParam(required=false) Double iznosMin,
			@RequestParam(required=false) Double iznosMax,
			@RequestParam(required=false) String datumvremePocetak,
			@RequestParam(required=false) String datumvremeKraj,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Transakcija> transakcijaPage = null;
		
		if(uplatilac != null || primalac != null || uplatilacMesto != null || primalacMesto != null || iznosMin != null || iznosMax != null || datumvremePocetak != null || datumvremeKraj != null ) {
			transakcijaPage = transakcijaService.search(uplatilac, primalac, uplatilacMesto, primalacMesto, iznosMin, iznosMax, datumvremePocetak, datumvremeKraj, pageNum);
		}
		else {
			transakcijaPage = transakcijaService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(transakcijaPage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDTO.convert(transakcijaPage.getContent()),
				headers,
				HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<TransakcijaDTO> getOne(@PathVariable Integer id){
		Transakcija transakcija = transakcijaService.getOne(id);
		if(transakcija==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(transakcija), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<TransakcijaDTO> delete(@PathVariable Integer id){
		Transakcija deleted = transakcijaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TransakcijaDTO> add( @Validated @RequestBody TransakcijaDTO newtoTransakcijaDTO){

		Transakcija savedTransakcija = transakcijaService.save(toTransakcija.convert(newtoTransakcijaDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedTransakcija), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<TransakcijaDTO> edit(
			@Validated @RequestBody TransakcijaDTO transakcijaDTO,
			@PathVariable Integer id){
		
		if(!id.equals(transakcijaDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Transakcija persisted = transakcijaService.save(toTransakcija.convert(transakcijaDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	@RequestMapping(value="/uplati" , method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TransakcijaDTO> uplati( @Validated @RequestBody TransakcijaDTO newtoTransakcijaDTO){
		if(newtoTransakcijaDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Klijent uplatilac = klijentService.getOne(newtoTransakcijaDTO.getUplatilacId()) ;
		Klijent primalac = klijentService.getOne(newtoTransakcijaDTO.getPrimalacId());
		
		if(uplatilac.getStanje() < newtoTransakcijaDTO.getIznos()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
				
		uplatilac.setStanje(uplatilac.getStanje() - newtoTransakcijaDTO.getIznos());
		klijentService.save(uplatilac);
		primalac.setStanje(primalac.getStanje() + newtoTransakcijaDTO.getIznos());
		klijentService.save(primalac);
		
		Transakcija savedTransakcija = transakcijaService.save(toTransakcija.convert(newtoTransakcijaDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedTransakcija), HttpStatus.CREATED);
	}

	
}
