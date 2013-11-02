package sy.video.valueobj;

public class Movie {
	private String MovieId;	
	private String MovieName;
	/**
	 * Production company name
	 */
	private String MovieBanner;
	private String ReleaseDate;
	private String RentAmount;
	private String Category;
	private String AvailableCopies;
	
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
	public String getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}
	public String getRentAmount() {
		return RentAmount;
	}
	public void setRentAmount(String rentAmount) {
		RentAmount = rentAmount;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getAvailableCopies() {
		return AvailableCopies;
	}
	public void setAvailableCopies(String availableCopies) {
		AvailableCopies = availableCopies;
	}
	public String getMovieId() {
		return MovieId;
	}
	public void setMovieId(String movieId) {
		MovieId = movieId;
	}
}
