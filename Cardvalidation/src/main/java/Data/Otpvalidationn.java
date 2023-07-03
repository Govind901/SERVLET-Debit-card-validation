package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import Pojo.OtpvalPojo;

public class Otpvalidationn {
	int otp;
	String sucessfull="sucessfull";
	String failure="failure";
	String cardstatus,dbcardnumber;
	String UPDATION="UPDATE cardvalidation SET otpvalidation=?,otpvalidatedtime=? WHERE cardno=?";
	String CARDVALID="SELECT * FROM cardvalidation WHERE cardno=?";
	String inputcardno;
	PreparedStatement pstmt3;
	PreparedStatement pstmt2;
	PreparedStatement pstmt1;
	PreparedStatement pstmt;
	ResultSet rs2;
	ResultSet rs1;
	ResultSet rs;
	public String otpval(OtpvalPojo o,Connection conn) throws SQLException {
		if(o.getCardno()==0 && o.getOtp()==0 ) {
			return "enter card number and otp";
		}
		else if(o.getCardno()==0) {
			return "enter card number";
		}
		else if(o.getOtp()==0) {
			return "enter otp";
		}
		else {
			LocalDateTime lt= LocalDateTime.now();
			String time=lt.toString();
			inputcardno=String.valueOf(o.getCardno());
		try {
		pstmt3=conn.prepareStatement(CARDVALID);
		pstmt3.setString(1,inputcardno );
		rs2=pstmt3.executeQuery();
		while(rs2.next()) {
			dbcardnumber=rs2.getString("cardno");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				pstmt3.close();
				rs2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(dbcardnumber.equals(inputcardno)) {
			try {
		 pstmt2=conn.prepareStatement(CARDVALID);
		pstmt2.setString(1,inputcardno);
		rs1=pstmt2.executeQuery();
		if(rs1.next()) {
			cardstatus=rs1.getString("cardvalidation");
		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					pstmt2.close();
					rs1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		if(cardstatus.equals(sucessfull)) {
			try {
		pstmt1=conn.prepareStatement(CARDVALID);
		pstmt1.setString(1, inputcardno);
		rs=pstmt1.executeQuery();
		if(rs.next()) {
			otp=rs.getInt("otp");
		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					pstmt1.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try {
		pstmt=conn.prepareStatement(UPDATION);
		if(o.getOtp()==otp) {
			
			pstmt.setString(1,sucessfull );
			pstmt.setString(2,time );
			pstmt.setString(3, inputcardno);
			pstmt.execute();
			return "otp validation successfull";
		}
		else if(o.getOtp()<0) {
			pstmt.setString(1,failure );
			pstmt.setString(2,time);
			pstmt.setString(3, inputcardno);
			pstmt.execute();
			return "otp is negitive and invalid";
		}
		else {
			pstmt.setString(1,failure );
			pstmt.setString(2, time);
			pstmt.setString(3,inputcardno);
			pstmt.execute();
			return"invalid otp";
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {	
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
			
		else {
			return "validate card first";
		}
		}
		else {
			return "enter valid card no";
		}
		}
		return null;
		
	}
}
