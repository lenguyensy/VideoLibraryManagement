package sy.video.valueobj;

import java.util.Date;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class Rental {
	public String UserId;
	public String MovieId;
	public String MovieName;
	public int ReleaseDate;
	public float RentAmount;
	public Date RentedDate;
	public Date ExpirationDate;
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getMovieId() {
		return MovieId;
	}
	public void setMovieId(String movieId) {
		MovieId = movieId;
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
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
	public Date getRentedDate() {
		return RentedDate;
	}
	public void setRentedDate(Date rentedDate) {
		RentedDate = rentedDate;
	}
	public Date getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		ExpirationDate = expirationDate;
	}
	
}