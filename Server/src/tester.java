import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import sy.video.model.UserModel;
import sy.video.model.VideoModel;
import sy.video.valueobj.Movie;
import sy.video.valueobj.PremiumMember;
import sy.video.valueobj.SimpleCustomer;
import sy.video.valueobj.User;

/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class tester {
	static UserModel um = new UserModel();
	static VideoModel vm = new VideoModel();

	public static void main(String[] args) {
		User[] lstUser = um.getPremiumMembers();
		for (int i = 0; i < 10; i++)
			System.out.println(lstUser[i]);
	}

	public static void importMovie() {
		int i = 0;

		Hashtable<String, Boolean> hash = new Hashtable<String, Boolean>();

		String[] lstCategory = { "Drama", "Horror", "Comedy", "Cartoon",
				"Romance" };
		Random rand = new Random();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"/Volumes/BigMac/User/sy/Downloads/movies.list"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				i++;
				if (i > 1) {
					try {
						String title = line
								.substring(1, line.lastIndexOf("\""));
						line = line.substring(line.lastIndexOf("\""));
						String year = line.substring(line.indexOf("(") + 1,
								line.indexOf(")"));
						String key = title + " - " + year;

						int releaseyear = Integer.parseInt(year);

						if (!hash.containsKey(key)) {
							hash.put(key, true);
							System.out.println(title + " - " + year);

							Movie m = new Movie();
							m.setMovieName(title);
							m.setMovieBanner(title + " - " + year);
							m.setReleaseDate(releaseyear);
							m.setAvailableCopies(rand.nextInt(5));
							m.setCategory(lstCategory[rand
									.nextInt(lstCategory.length)]);
							m.setRentAmount(rand.nextInt(3));

							// save
							vm.addMovie(m);
						}
						// next line
						line = br.readLine();
					} catch (Exception ex) {
						;
					}
				}

				line = br.readLine();
			}
			String everything = sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void importUser() {
		int i = 0;
		User u;

		BufferedReader br = null;
		try {
			br = new BufferedReader(
					new FileReader(
							"/Volumes/BigMac/User/sy/Desktop/126c85851769490e8914b5d97ca421dd.txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				i++;
				if (i > 1) {
					try {
						if (i < 15000)
							u = new PremiumMember();
						else
							u = new SimpleCustomer();

						String[] s = line.split("\t");

						String zip = s[6].equals("") ? "12345" : s[6];
						zip = zip.substring(0, 5);

						String membership = "610" + String.valueOf(i);
						while (membership.length() < 9)
							membership += "0";

						u.setMembershipNo(membership);
						u.setFirstName(s[1]);
						u.setLastName(s[2]);
						u.setAddress(s[3]);
						u.setCity(s[4]);
						u.setState(s[5]);
						u.setZipCode(zip);
						u.setHashPassword("password");

						// save
						um.addUser(u);

						System.out.println(i);
					} catch (Exception ex) {
						;
					}
				}

				line = br.readLine();
			}
			String everything = sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
