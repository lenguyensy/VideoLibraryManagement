package sy.video.valueobj;

public class User {
	private String UserId;
	private String FirstName;
	private String LastName;
	private String Address;
	private String City;
	private String State;
	private String ZipCode;
	private String IssuedMovies;
	private String Password;
	private String HashPassword;
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getIssuedMovies() {
		return IssuedMovies;
	}
	public void setIssuedMovies(String issuedMovies) {
		IssuedMovies = issuedMovies;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getHashPassword() {
		return HashPassword;
	}
	public void setHashPassword(String hashPassword) {
		HashPassword = hashPassword;
	}
}
