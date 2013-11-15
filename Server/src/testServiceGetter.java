import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import sy.config.AppEnum;
import sy.video.model.RentalModel;
import sy.video.model.UserModel;
import sy.video.model.VideoModel;

@RunWith(Parameterized.class)

/**
 * unit test for server side code
 * 
 * @author Sy Le
 * 
 */
public class testServiceGetter {
	static UserModel um = new UserModel();
	static VideoModel vm = new VideoModel();
	static RentalModel rm = new RentalModel();
	
	int from = 0;
	int pagesize = 3;
	int userId = 2;
	int movieId = 2;
	String genre = "Action";
	String searchTerm = "World";
	
	@Parameterized.Parameters
	/**
	 * load test
	 * @return
	 */
    public static List<Object[]> data() {
        return Arrays.asList(new Object[20][0]);
    }

	private static void print(Object[] ol) {
		for (int i = 0; i < ol.length; i++)
			System.out.println(ol[i]);
	}

	private static void print(Object o) {
		System.out.println(o);
	}

	// test all the get calls
	@Test
	public void getUsers() {
		System.out.println("Test getUsers");
		Object[] o = um.getUsers(from, pagesize);
		assertEquals("getUsers", o.length, pagesize);
		print(o);
	}

	@Test
	public void getUserByTypeCount() {
		System.out.println("Test getUserByTypeCount");

		int ret;
		ret = um.getUserByTypeCount(AppEnum.USER_TYPE_PREMIUM);
		Assert.assertNotEquals("getUserByTypeCount", ret, 0);

		ret = um.getUserByTypeCount(AppEnum.USER_TYPE_SIMPLE);
		Assert.assertNotEquals("getUserByTypeCount", ret, 0);

		ret = um.getUserByTypeCount("Admin");
		Assert.assertNotEquals("getUserByTypeCount", ret, 0);
	}

	@Test
	public void getUsersCount() {
		System.out.println("Test getUsersCount");

		int ret;
		ret = um.getUsersCount();
		Assert.assertNotEquals("getUsersCount", ret, 0);
	}

	@Test
	public void getUser() {
		System.out.println("Test getUser");

		Object o = um.getUser(userId);
		assertNotNull("getUser " + userId, o);
		print(o);
	}

	@Test
	public void getUserByType() {
		System.out.println("Test getUserByType");

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
		System.out.println("Test getMovies");

		Object[] o = vm.getMovies(from, pagesize);
		assertEquals("getMovies", o.length, pagesize);
		print(o);
	}

	@Test
	public void getMoviesByGenre() {
		System.out.println("Test getMoviesByGenre");

		Object[] o = vm.getMoviesByGenre(genre, from, pagesize);
		assertEquals("getMoviesByGenre " + genre, o.length, pagesize);
		print(o);
	}

	@Test
	public void getMoviesBySearchTerm() {
		System.out.println("Test getMoviesBySearchTerm");

		Object[] o = vm.getMoviesBySearchTerm(searchTerm, from, pagesize);
		assertEquals("getMoviesBySearchTerm " + searchTerm, o.length, pagesize);
		print(o);
	}

	@Test
	public void getMovie() {
		System.out.println("Test getMovie");

		Object o = vm.getMovie(movieId);
		assertNotNull("getMovie " + movieId, o);
		print(o);
	}

	@Test
	public void getUserByMovieId() {
		System.out.println("Test getUserByMovieId");

		Object[] o = rm.getUserByMovieId(userId);
		assertNotNull("getUserByMovieId " + movieId, o);
		print(o);
	}

	@Test
	public void getMoviesRentalByUser() {
		System.out.println("Test getMoviesRentalByUser");

		Object[] o = rm.getMoviesRentalByUser(userId);
		assertNotNull("getMoviesRentalByUser " + userId, o);
		print(o);
	}

	@Test
	public void getMoviesCount() {
		System.out.println("Test getMoviesCount");

		int ret;
		ret = vm.getMoviesCount();
		Assert.assertNotEquals("getMoviesCount", ret, 0);
	}

	@Test
	public void getMoviesByGenreCount() {
		System.out.println("Test getMoviesByGenreCount");

		int ret;
		ret = vm.getMoviesByGenreCount("action");
		Assert.assertNotEquals("getMoviesByGenreCount", ret, 0);
	}

	@Test
	public void getMoviesBySearchTermCount() {
		System.out.println("Test getMoviesBySearchTermCount");

		int ret;
		ret = vm.getMoviesBySearchTermCount("world");
		Assert.assertNotEquals("getMoviesBySearchTermCount", ret, 0);
	}
}