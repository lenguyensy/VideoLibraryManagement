import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import sy.config.MainConfig;
import sy.video.model.RentalModel;
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
	static RentalModel rm = new RentalModel();

	public static void main(String[] args) {
		// importMovie();
		// importUser();

		// vm.rentMovie(1, 2);
		rm.getUserByMovieId(2);
	}

	public static void importMovie() {
		int i = 0;

		String lastKey = "";
		Random rand = new Random();

		BufferedReader br = null;
		ArrayList<String> lstGenres = null;
		try {
			br = new BufferedReader(new FileReader(
					"/Volumes/BigMac/User/sy/Downloads/genres.list"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				i++;
				if (i > 1) {
					try {
						line = line.replace("\"", "");

						String title = line.substring(0, line.lastIndexOf("("));
						String year = line.substring(line.indexOf("(") + 1,
								line.indexOf(")"));
						int releaseyear = Integer.parseInt(year);
						String genre = line.substring(line.lastIndexOf("\t"))
								.trim();
						String key = title + " - " + year;

						if (!lastKey.equals(key)) {
							// save the old one first
							String genresString = "";
							if (lstGenres != null) {
								genresString = MainConfig.combine(lstGenres
										.toArray(new String[lstGenres.size()]),
										",");

								Movie m = new Movie();
								m.setMovieName(title);
								m.setMovieBanner(title + " - " + year);
								m.setReleaseDate(releaseyear);
								m.setAvailableCopies(rand.nextInt(5));
								m.setCategory(genresString);
								m.setRentAmount(rand.nextInt(3));

								// save
								vm.addMovie(m);

								System.out.println(title + "- " + year + " - "
										+ genresString);
							}

							// start the new one
							lstGenres = new ArrayList<String>();
							lstGenres.add(genre);

							lastKey = key;
							System.out.println(title + " - " + year);
						} else {
							// add new genre to the list
							lstGenres.add(genre);
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
						u.setPassword("password");
						u.setEmail(s[0]);
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
