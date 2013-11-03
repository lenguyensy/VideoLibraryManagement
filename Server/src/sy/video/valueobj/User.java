package sy.video.valueobj;


/**
 * 
 * @author Sy Le lenguyensy@gmail.com
 */
public class User {
	private String UserId;
	private String UserType;
	private String FirstName;
	private String LastName;
	private String Address;
	private String City;
	private String State;
	private String ZipCode;
	private String IssuedMovies;
	private String Password;
	private String HashPassword;
	private String MembershipNo;
	private float MonthlySubscriptionFee = 15f;
	private float Total = 20f;
	private float Balance = 0f;
	private int TotalOutstandingMovies = 5;

	public String getMembershipNo() {
		return MembershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		MembershipNo = membershipNo;
	}

	public float getBalance() {
		return Balance;
	}

	public void setBalance(float balance) {
		Balance = balance;
	}

	public int getTotalOutstandingMovies() {
		return TotalOutstandingMovies;
	}

	public void setTotalOutstandingMovies(int totalOutstandingMovies) {
		TotalOutstandingMovies = totalOutstandingMovies;
	}

	public float getMonthlySubscriptionFee() {
		return MonthlySubscriptionFee;
	}

	public void setMonthlySubscriptionFee(float monthlySubscriptionFee) {
		MonthlySubscriptionFee = monthlySubscriptionFee;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}

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

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

}
