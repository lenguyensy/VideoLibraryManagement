import static org.junit.Assert.*;

import org.junit.Test;

import sy.config.AppEnum;
import sy.video.model.RentalModel;
import sy.video.model.UserModel;
import sy.video.model.VideoModel;

/**
 * unit test for server side code
 * 
 * @author Sy Le
 * 
 */
public class unitTest {
	static UserModel um = new UserModel();
	static VideoModel vm = new VideoModel();
	static RentalModel rm = new RentalModel();

	int from = 0;
	int pagesize = 3;
	int userId = 2;
	int movieId = 2;
	String genre = "Action";
	String searchTerm = "World";

	private static void print(Object[] ol) {
		for (int i = 0; i < ol.length; i++)
			System.out.println(ol[i]);
	}

	private static void print(Object o) {
		System.out.println(o);
	}

	@Test
	public void getUsers() {
		Object[] o = um.getUsers(from, pagesize);
		assertEquals("getUsers", o.length, pagesize);
		print(o);
	}

	@Test
	public void getUser() {
		Object o = um.getUser(userId);
		assertNotNull("getUser " + userId, o);
		print(o);
	}

	@Test
	public void getUserByType() {
		Object[] o;
		o = um.getUserByType(AppEnum.USER_TYPE_PREMIUM, from, pagesize);
		assertEquals("getUsers by USER_TYPE_PREMIUM", o.length, pagesize);
		print(o);

		o = um.getUserByType(AppEnum.USER_TYPE_SIMPLE, from, pagesize);
		assertEquals("getUsers by USER_TYPE_SIMPLE", o.length, pagesize);
		print(o);
	}

	@Test
	public void getMovies() {
		Object[] o = vm.getMovies(from, pagesize);
		assertEquals("getMovies", o.length, pagesize);
		print(o);
	}

	@Test
	public void getMoviesByGenre() {
		Object[] o = vm.getMoviesByGenre(genre, from, pagesize);
		assertEquals("getMoviesByGenre " + genre, o.length, pagesize);
		print(o);
	}

	@Test
	public void getMoviesBySearchTerm() {
		Object[] o = vm.getMoviesBySearchTerm(searchTerm, from, pagesize);
		assertEquals("getMoviesBySearchTerm " + searchTerm, o.length, pagesize);
		print(o);
	}

	@Test
	public void getMovie() {
		Object o = vm.getMovie(movieId);
		assertNotNull("getMovie " + movieId, o);
		print(o);
	}
	
	@Test
	public void getUserByMovieId() {
		Object []o = rm.getUserByMovieId(userId);
		assertNotNull("getUserByMovieId " + movieId, o);
		print(o);
	}
	
	@Test
	public void getMoviesRentalByUser() {
		Object []o = rm.getMoviesRentalByUser(userId);
		assertNotNull("getMoviesRentalByUser " + userId, o);
		print(o);
	}	
}