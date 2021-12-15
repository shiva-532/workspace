package com.bvr.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bvr.models.CatalogItem;
import com.bvr.models.Movie;
import com.bvr.models.UserRatings;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

	public CatalogResource() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
		UserRatings userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRatings.class);
		
		return userRating.getRatings().stream()
				.map(rating -> {
					Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
					return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
				})
				.collect(Collectors.toList());
		
		
	}
}
