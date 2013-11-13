package sy.video.model;

public class Config {
	public static final Boolean CLOUD_MYSQL_DEPLOY = Boolean.FALSE;
	public static final Boolean CLOUD_MONGO_DEPLOY = Boolean.FALSE;
	
	// Local End Ppoint
	public static final String ENDPOINT_USER = "http://localhost:8080/VideoLibraryServer/services/UserModel";
	public static final String ENDPOINT_MOVIE = "http://localhost:8080/VideoLibraryServer/services/VideoModel";
	public static final String ENDPOINT_RENTAL = "http://localhost:8080/VideoLibraryServer/services/RentalModel";

	
	//Cloud-MySQL End Point.
	public static final String ENDPOINT_MYSQL_USER_CLOUD = "http://videoserver.aws.af.cm/services/UserModel";
	public static final String ENDPOINT_MYSQL_MOVIE_CLOUD = "http://videoserver.aws.af.cm/services/VideoModel";
	public static final String ENDPOINT_MYSQL_RENTAL_CLOUD = "http://videoserver.aws.af.cm/services/RentalModel";
	
	
	//Cloud-Mongo End Point.
	public static final String ENDPOINT_MONGO_USER_CLOUD = "http://videoserver-mongo.aws.af.cm/services/UserModel";
	public static final String ENDPOINT_MONGO_MOVIE_CLOUD = "http://videoserver-mongo.aws.af.cm/services/VideoModel";
	public static final String ENDPOINT_MONGO_RENTAL_CLOUD = "http://videoserver-mongo.aws.af.cm/services/RentalModel";
	
	
	public static String getUserModelEndPoint() {
		if ( CLOUD_MYSQL_DEPLOY) 
			return ENDPOINT_MYSQL_USER_CLOUD;
		if ( CLOUD_MONGO_DEPLOY)
			return ENDPOINT_MONGO_USER_CLOUD;
		return ENDPOINT_USER;
	}
	
	public static String getMovieModelEndPoint() {
		if ( CLOUD_MYSQL_DEPLOY) 
			return ENDPOINT_MYSQL_MOVIE_CLOUD;
		if ( CLOUD_MONGO_DEPLOY)
			return ENDPOINT_MONGO_MOVIE_CLOUD;
		return ENDPOINT_MOVIE;
	}
	
	public static String getRentalModelEndPoint() {
		if ( CLOUD_MYSQL_DEPLOY) 
			return ENDPOINT_MYSQL_RENTAL_CLOUD;
		if ( CLOUD_MONGO_DEPLOY)
			return ENDPOINT_MONGO_RENTAL_CLOUD;
		return ENDPOINT_RENTAL;
	}
}
