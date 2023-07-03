package Pojo;
public class CardvalidationnPojo {
	long cardno;
	int cvv;
	String expiry;
	public long getCardno() {
		return cardno;
	}
	public void setCardno(long cardno) {
		this.cardno = cardno;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public CardvalidationnPojo(long cardno, int cvv, String expiry) {
		super();
		this.cardno = cardno;
		this.cvv = cvv;
		this.expiry = expiry;
	}
	
}
