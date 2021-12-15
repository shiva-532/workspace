package com.bvr.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvr.models.Rating;
import com.bvr.models.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	public RatingsResource() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/movies/{moviesId}")
	public Rating getMovieRating(@PathVariable("moviesId") String movieId) {
		return new Rating(movieId, 4);
		
	}
	
	@RequestMapping("/user/{userId}")
	public UserRatings getUserRatings(@PathVariable("userId") String userId) {
		UserRatings userRating = new UserRatings();
		userRating.initData(userId);
		return userRating;
		
	}
}
