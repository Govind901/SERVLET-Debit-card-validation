package Pojo;

public class OtpvalPojo {
	int otp;
	long cardno;
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public long getCardno() {
		return cardno;
	}
	public void setCardno(long cardno) {
		this.cardno = cardno;
	}
	public OtpvalPojo(int otp, long cardno) {
		super();
		this.otp = otp;
		this.cardno = cardno;
	}
	
}
