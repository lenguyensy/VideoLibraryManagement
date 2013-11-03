package sy.video.valueobj;

import java.util.Date;

/**
 * 
 * @author Sy Le
 * lenguyensy@gmail.com
 */
public class Movie {
	private String MovieId;
	private String MovieName;
	private String MovieBanner;
	private int ReleaseDate;
	private float RentAmount;
	private String Category;
	private int AvailableCopies;

	public String getMovieName() {
		return MovieName;
	}

	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	public String getMovieBanner() {
		return MovieBanner;
	}

	public void setMovieBanner(String movieBanner) {
		MovieBanner = movieBanner;
	}

	public int getReleaseDate() {
		return ReleaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		ReleaseDate = releaseDate;
	}

	public float getRentAmount() {
		return RentAmount;
	}

	public void setRentAmount(float rentAmount) {
		RentAmount = rentAmount;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public int getAvailableCopies() {
		return AvailableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		AvailableCopies = availableCopies;
	}

	public String getMovieId() {
		return MovieId;
	}

	public void setMovieId(String movieId) {
		MovieId = movieId;
	}
}
