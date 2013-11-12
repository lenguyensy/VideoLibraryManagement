import static org.junit.Assert.*;

import org.junit.Test;

import sy.config.AppEnum;
import sy.video.model.RentalModel;
import sy.video.model.UserModel;
import sy.video.model.VideoModel;
import sy.video.valueobj.User;

/**
 * unit test for server side code
 * 
 * @author Sy Le
 * 
 */
public class unitTestRent {
	static UserModel um = new UserModel();
	static VideoModel vm = new VideoModel();
	static RentalModel rm = new RentalModel();

	int from = 0;
	int pagesize = 3;
	int userId = 2;
	int movieId = 2;
	String genre = "Action";
	String searchTerm = "World";
	
	
	
	@Test
	public void rentMovie() {
		rm.rentMovie(11, 3);
		assertEquals("rentMovie", rm.getMoviesRentalByUser(2).length, 2);
	}
}