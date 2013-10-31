package sy.video.valueobj;

public class SimpleCustomer {
	private String MembershipNo;
	/**
	 * Amount needs to be taken as the rent of the movies issued
	 */
	private String Balance;
	private String TotalOutstandingMovies;
	public String getMembershipNo() {
		return MembershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		MembershipNo = membershipNo;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public String getTotalOutstandingMovies() {
		return TotalOutstandingMovies;
	}
	public void setTotalOutstandingMovies(String totalOutstandingMovies) {
		TotalOutstandingMovies = totalOutstandingMovies;
	}
	
	
}
