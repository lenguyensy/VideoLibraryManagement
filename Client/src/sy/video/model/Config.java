package sy.video.model;

public class Config {
	public static final Boolean CLOUD_DEPLOY = Boolean.FALSE;
	
	// Local End Ppoint
	public static final String ENDPOINT_USER = "http://localhost:8080/VideoLibraryServer/services/UserModel";
	public static final String ENDPOINT_MOVIE = "http://localhost:8080/VideoLibraryServer/services/VideoModel";
	public static final String ENDPOINT_RENTAL = "http://localhost:8080/VideoLibraryServer/services/RentalModel";

	
	//Cloud-MySQL End Point.
	public static final String ENDPOINT_USER_CLOUD = "http://videoserver.aws.af.cm/services/UserModel";
	public static final String ENDPOINT_MOVIE_CLOUD = "http://videoserver.aws.af.cm/services/VideoModel";
	public static final String ENDPOINT_RENTAL_CLOUD = "http://videoserver.aws.af.cm/services/RentalModel";
	
	
	public static String getUserModelEndPoint() {
		return CLOUD_DEPLOY ? ENDPOINT_USER_CLOUD : ENDPOINT_USER;
	}
	
	public static String getMovieModelEndPoint() {
		return CLOUD_DEPLOY ? ENDPOINT_MOVIE_CLOUD : ENDPOINT_MOVIE;
	}
	
	public static String getRentalModelEndPoint() {
		return CLOUD_DEPLOY ? ENDPOINT_RENTAL_CLOUD : ENDPOINT_RENTAL;
	}
}
