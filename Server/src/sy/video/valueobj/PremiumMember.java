package sy.video.valueobj;

public class PremiumMember extends User{
	private String MembershipNo;
	private String MonthlySubscriptionFee;
	private String Total;
	
	public String getMembershipNo() {
		return MembershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		MembershipNo = membershipNo;
	}
	public String getMonthlySubscriptionFee() {
		return MonthlySubscriptionFee;
	}
	public void setMonthlySubscriptionFee(String monthlySubscriptionFee) {
		MonthlySubscriptionFee = monthlySubscriptionFee;
	}
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
}
