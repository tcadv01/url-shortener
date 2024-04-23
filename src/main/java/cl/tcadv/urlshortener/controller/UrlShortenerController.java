package cl.tcadv.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import cl.tcadv.urlshortener.dto.GenericResponseDTO;
import cl.tcadv.urlshortener.dto.ShortenUrlRequestDTO;
import cl.tcadv.urlshortener.dto.UpdateUrlRequestDTO;
import cl.tcadv.urlshortener.exeption.UrlNotFoundException;
import cl.tcadv.urlshortener.model.UrlDetail;
import cl.tcadv.urlshortener.service.UrlShortenerService;
import jakarta.validation.Valid;

@RestController()
public class UrlShortenerController {

	@Autowired
	private UrlShortenerService urlShortenerService;

	@PostMapping("/url-shortener")
	public ResponseEntity<GenericResponseDTO<UrlDetail>> shortenUrl(
			@Valid @RequestBody ShortenUrlRequestDTO shortenUrlRequestDTO) {
		UrlDetail urlDetail = this.urlShortenerService.shortenUrl(shortenUrlRequestDTO.getUrl());
		GenericResponseDTO<UrlDetail> genericResponseDTO = new GenericResponseDTO<UrlDetail>(HttpStatus.OK.value(),
				"success", urlDetail);
		return ResponseEntity.ok(genericResponseDTO);
	}

	@PatchMapping("/url-shortener/{id}")
	public ResponseEntity<GenericResponseDTO<UrlDetail>> patchUrl(@PathVariable long id,
			@Valid @RequestBody UpdateUrlRequestDTO updateUrlRequestDTO) throws UrlNotFoundException {
		UrlDetail urlDetail = new UrlDetail(id, updateUrlRequestDTO.getUrl(), updateUrlRequestDTO.isEnabled());
		urlDetail = this.urlShortenerService.updateUrl(urlDetail);
		GenericResponseDTO<UrlDetail> genericResponseDTO = new GenericResponseDTO<UrlDetail>(HttpStatus.OK.value(),
				"success", null);
		return ResponseEntity.ok(genericResponseDTO);
	}

	@GetMapping("/url/{id}")
	public RedirectView expandUrl(@PathVariable long id) throws UrlNotFoundException {
		UrlDetail urlDetail = this.urlShortenerService.expandUrl(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(urlDetail.getUrl());
		return redirectView;
	}

	@GetMapping("/url-shortener/{id}")
	public ResponseEntity<GenericResponseDTO<UrlDetail>> getUrl(@PathVariable long id) throws UrlNotFoundException {
		UrlDetail urlDetail = this.urlShortenerService.getUrl(id);
		GenericResponseDTO<UrlDetail> genericResponseDTO = new GenericResponseDTO<UrlDetail>(HttpStatus.OK.value(),
				"success", urlDetail);
		return ResponseEntity.ok(genericResponseDTO);
	}

}
