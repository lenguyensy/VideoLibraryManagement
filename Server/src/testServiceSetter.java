import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import sy.video.model.RentalModel;
import sy.video.model.UserModel;
import sy.video.model.VideoModel;
import sy.video.valueobj.Movie;
import sy.video.valueobj.User;

/**
 * unit test for server side code
 * 
 * @author Sy Le
 * 
 */
public class testServiceSetter {
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
	public void saveUsers() {
		System.out.println("Test saveUsers");

		User u;
		int userIdToTest = 123;
		u = um.getUser(userIdToTest);
		assertEquals("saveUsers", um.saveUser(u),
				"Save is not done, no change happens.");

		// with a change
		Random r = new Random();
		u = um.getUser(userIdToTest);
		u.setFirstName("Premium " + String.valueOf(r.nextInt(1000)));
		assertEquals("saveUsers", um.saveUser(u), "true");
	}

	@Test
	public void addUsers() {
		System.out.println("Test addUsers");
		Random rand = new Random();

		User u;
		u = um.getUser(userId);
		assertEquals(
				"addUser with same email and ssn",
				um.addUser(u),
				"Your SSN has been registered in our database. Please use a different SSN or call customer support for help.");

		u = um.getUser(userId);
		u.setMembershipNo("12345678" + rand.nextInt(9));
		assertEquals(
				"addUser with same email",
				um.addUser(u),
				"Your email address "
						+ u.getEmail()
						+ " has been registered. Please use another email address.");

		u = um.getUser(userId);
		u.setEmail("sy+999" + rand.nextInt(123213) + "@ebay.com");
		assertEquals(
				"addUser with same ssn",
				um.addUser(u),
				"Your SSN has been registered in our database. Please use a different SSN or call customer support for help.");

		u = um.getUser(userId);
		u.setMembershipNo("123" + rand.nextInt(9) + "5678" + rand.nextInt(9));
		u.setEmail("sy+999" + rand.nextInt(91232) + "@ebay.com");
		u.setPassword("password");
		assertEquals("addUser with different ssn and email", um.addUser(u),
				"true");
	}

	@Test
	public void returnRentals() {
		System.out.println("Test returnRentals");

		rm.returnRentals();
	}

	@Test
	public void deleteUser() {
		System.out.println("Test deleteUser");

		int userToDelete = 10000;
		String success = um.deletUser(userToDelete);
		User u = um.getUser(userToDelete);
		assertEquals("deleteUser", success, "true");
		assertEquals("deleteUser", u, null);
	}

	@Test
	public void deletMovie() {
		System.out.println("Test deletMovie");
		int movToDelete = 10000;
		String success = vm.deletMovie(movToDelete);
		Movie v = vm.getMovie(movToDelete);
		assertEquals("deletMovie", success, "true");
		assertEquals("deletMovie", v, null);
	}

	@Test
	public void rentMovie() {
		/*
		 * rm.rentMovie(2, 2); rm.rentMovie(2, 3); assertEquals("rentMovie",
		 * rm.getMoviesRentalByUser(2).length, 2);
		 * 
		 * rm.rentMovie(3, 9); rm.rentMovie(4, 9); rm.rentMovie(2, 9);
		 * assertEquals("rentMovie", rm.getUserByMovieId(9).length, 3);
		 */
	}
}