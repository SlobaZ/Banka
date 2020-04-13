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
import banka.service.KlijentService;
import banka.support.KlijentDTOToKlijent;
import banka.support.KlijentToKlijentDTO;
import banka.web.dto.KlijentDTO;


@RestController
@RequestMapping(value="/klijenti")
public class ApiKlijentController {

	@Autowired
	private KlijentService klijentService;
	
	@Autowired
	private KlijentToKlijentDTO toDTO;
	 
	@Autowired
	private KlijentDTOToKlijent toKlijent;
		

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<KlijentDTO>> getAll(
			@RequestParam(required=false) String imeprezime,
			@RequestParam(required=false) String mesto,
			@RequestParam(required=false) Double stanje,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Klijent> KlijentPage = null;
		
		if(imeprezime != null || mesto != null || stanje != null) {
			KlijentPage = klijentService.search(imeprezime, mesto, stanje, pageNum);
		}
		else {
			KlijentPage = klijentService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(KlijentPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(KlijentPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<KlijentDTO> getOne(@PathVariable Integer id){
		Klijent Klijent = klijentService.getOne(id);
		if(Klijent==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(Klijent), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<KlijentDTO> delete(@PathVariable Integer id){
		Klijent deleted = klijentService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KlijentDTO> add( @Validated @RequestBody KlijentDTO newKlijentDTO){
		
		Klijent savedKlijent = klijentService.save(toKlijent.convert(newKlijentDTO));
		
		return new ResponseEntity<>( toDTO.convert(savedKlijent), HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<KlijentDTO> edit(
			@Validated @RequestBody KlijentDTO KlijentDTO,
			@PathVariable Integer id){
		
		if(!id.equals(KlijentDTO.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Klijent persisted = klijentService.save(toKlijent.convert(KlijentDTO));
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(value="/{id}/{iznosKredita}/{trajanje}/uzmiKredit" , method=RequestMethod.POST)
	public ResponseEntity<KlijentDTO> uzmiKredit ( @PathVariable Integer id, @PathVariable Double iznosKredita , @PathVariable Integer trajanje ) {
		
		Klijent klijent = klijentService.uzmiKredit(id,iznosKredita ,trajanje );
		if(klijent==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>( toDTO.convert(klijent) , HttpStatus.CREATED);
	}
	
	

	
	
	
}
