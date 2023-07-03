package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.time.LocalDateTime;
import Pojo.CardvalidationnPojo;

public class CardValidationn {
	String cardnumber,databasecardno,expiry;
	String failed="failed";
	String sucessfull="sucessfull";
	String GET_DATA="SELECT * FROM cardvalidation WHERE cardno=?";
	String UPDATE="UPDATE cardvalidation SET cardvalidation=?,cardvalidatedtime=?,otp=? WHERE cardno=?";
	int cvv,zero=0;
	ResultSet rs;
	ResultSet rs1;
	PreparedStatement pstmt1;
	PreparedStatement pstmt2;
	PreparedStatement pstmt;
	public String validation(CardvalidationnPojo c,Connection conn)  {
		if(c.getExpiry()==null && c.getCvv()==0 && c.getCardno()==0) {
			return "enter expiry and cvv and card number";
		}
		else if(c.getExpiry()==null && c.getCvv()==0) {
			return "enter expiry and cvv";
		}
		else if(c.getCardno()==0) {
			return "enter card number";
		}	
		else if(c.getCvv()==0) {
			return "enter cvv";
		}
		else if(c.getExpiry()==null) {
			return "enter expiry";
		}
		else {
			Random rm=new Random();
			int r=rm.nextInt(20)+1;
			int otp=(r*99)+987;
			cardnumber=String.valueOf(c.getCardno());
		LocalDateTime localtime= LocalDateTime.now();
		
		try {
			pstmt1 = conn.prepareStatement(GET_DATA);
		
		pstmt1.setString(1,cardnumber );
		 rs=pstmt1.executeQuery();
		while(rs.next()) {
			databasecardno=rs.getString("cardno");
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
		if(databasecardno.equals(cardnumber)) {
		pstmt=conn.prepareStatement(GET_DATA);
			pstmt.setString(1, cardnumber);
			rs1=pstmt.executeQuery();
			if(rs1.next()) {
				cvv=rs1.getInt("cvv");
				expiry=rs1.getString("expiry");
			}
			pstmt2=conn.prepareStatement(UPDATE);
			if(cvv==(c.getCvv())) {
				
				if(expiry.equals(c.getExpiry())) {
					String time=localtime.toString();
					pstmt2.setString(1, sucessfull);
					pstmt2.setString(2, time);
					pstmt2.setInt(3, otp);
					pstmt2.setString(4, cardnumber);
					pstmt2.execute();
					return "card validation sucessfull  generated otp is "+ otp;
				}
				else {
					String time=localtime.toString();
					pstmt2.setString(1, failed);
					pstmt2.setString(2, time);
					pstmt2.setInt(3, zero);
					pstmt2.setString(4, cardnumber);
					pstmt2.execute();
					return "enter valid expiry date";
				}
			}
			else {
				String time=localtime.toString();
				pstmt2.setString(1, failed);
				pstmt2.setString(2, time);
				pstmt2.setInt(3, zero);
				pstmt2.setString(4, cardnumber);
				pstmt2.execute();
				return "enter valid cvv";
			}
		}
		
		else {
			return "enter valid card number";
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {	
				pstmt.close();
				pstmt2.close();
				rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		}
		return "no method";
		
	}
}
