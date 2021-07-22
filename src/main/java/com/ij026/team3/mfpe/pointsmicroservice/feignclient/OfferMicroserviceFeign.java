package com.ij026.team3.mfpe.pointsmicroservice.feignclient;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ij026.team3.mfpe.pointsmicroservice.model.Offer;
import com.ij026.team3.mfpe.pointsmicroservice.model.OfferCategory;

@FeignClient(name = "offers", url = "${OMS_URL:http://localhost:9999/offer-service/}")
public interface OfferMicroserviceFeign {
	@GetMapping("/test")
	public String test(@RequestParam(required = false) Map<String, Object> map);

	@GetMapping("/offers")
	public Collection<Offer> getOffers();

	@GetMapping("/offers/{offerId}")
	public ResponseEntity<?> getOfferDetails(@PathVariable String offerId);

	@GetMapping("/offers/search/by-category")
	public ResponseEntity<?> getOfferDetailsByCategory(@RequestParam(required = true) OfferCategory offerCategory);

	@GetMapping("/offers/search/by-likes")
	public ResponseEntity<?> getOfferDetailsByLikes(@RequestParam(required = false, defaultValue = "3") Integer limit,
			@RequestParam(required = false) String empId);

	@GetMapping("/offers/search/by-creation-date")
	public ResponseEntity<?> getOfferDetailsByPostDate(@RequestParam(required = true) String createdOn);

	@GetMapping("/offers/search/by-author")
	public ResponseEntity<?> getOfferDetailsByAuthor(@RequestParam(required = true) String authorId);

	@PostMapping("/offers")
	public ResponseEntity<Object> addOffer(@Valid @RequestBody Offer newOffer);
}