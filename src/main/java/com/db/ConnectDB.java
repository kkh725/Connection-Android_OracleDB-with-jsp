package com.db;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() {  }

    // oracle 계정
    String jdbcUrl = "jdbc:mysql://login-lecture.cxh9jd3ejp84.ap-northeast-2.rds.amazonaws.com:3306/login_lecture";
    String userId = "admin";
    String userPw = "jkr123^^7";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    String sql = "";
    String sql2 = "";
    String returns = "a";

    public String connectionDB(String id, String pwd, String Name,  String Birth, String Mail,String Phone, String City) {
        try { // 회원가입 구문
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            

            sql = "SELECT id FROM userTBL WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "false.";
            } 
            else if(id.equals(null)||pwd.equals(null)||Name.equals("")||Phone.equals("")||Birth.equals("")||Mail.equals("")||City.equals("")) {
            	returns = "빈칸을 채워주세요.";
            }
            else {
                sql2 = "INSERT INTO member_dbag2 VALUES(?,?,?,?,?,?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.setString(2, pwd);
                pstmt2.setString(3, Name);           
                pstmt2.setString(4, Birth);// 데이터베이스 안에 문자열->숫자변환이기때문에 1050401843 이런식으로 저장됨.
                // 나중에 안드로이드에 불러올때 0 붙여서 사용. 그냥 문자열로 받을지 고려.
                pstmt2.setString(5, Mail);
                System.out.println(Mail);
                pstmt2.setString(6, Phone);
                pstmt2.setString(7, City);
                pstmt2.executeUpdate();
                returns = "true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
    
    public String Login(String id, String pwd) { //로그인 구문
    	String msg="";
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT PW from member_dbag2 WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1));
            	if( rs.getString(1).equals(pwd)) { // id가 id인 테이블의 pw값 검색하고, 입력한 pw가 맞으면 로그인
                msg = "true";            
                }
            else {
            	msg = "false";
            }
        } 
    	}
    		
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return msg;
    }
    public String checkInfo(String id) {
    	
    	
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            System.out.println("진입");

            sql = "SELECT * from member_dbag2 WHERE ID = ?"; //ID의 이름값찾기
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            System.out.println("진입2");
            
            if(rs.next()) {
            	System.out.println("진입3");
    
            String NAME = rs.getString(3);
            String BIRTH = rs.getString(4);
            String MAIL = rs.getString(5);
            String PHONE = rs.getString(6);
            String CITY = rs.getString(7);
            
            String str = NAME+" "+BIRTH+" "+ MAIL+" " +PHONE+" " + CITY;
            System.out.println(str);
            return str;
            }
            else {
            	System.out.println("진입실패");
            }
    	}
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
    
    public String Complete(String id, String pwd, String Name, String Phone, String Birth, String Mail, String City) {
        try { //회원정보 변경.
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT id FROM member_dbag2 WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                String sql2 = "UPDATE member_dbag2 SET PW = ? WHERE ID = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, pwd);
                pstmt2.setString(2, id);
                pstmt2.executeUpdate();
                
                sql2 = "UPDATE member_dbag2 SET NAME = ? WHERE ID = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, Name);
                pstmt2.setString(2, id);
                pstmt2.executeUpdate();
                
                sql2 = "UPDATE member_dbag2 SET PHONE = ? WHERE ID = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, Phone);
                pstmt2.setString(2, id);
                pstmt2.executeUpdate();
                
                sql2 = "UPDATE member_dbag2 SET BIRTH = ? WHERE ID = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, Birth);
                pstmt2.setString(2, id);
                pstmt2.executeUpdate();
                
                sql2 = "UPDATE member_dbag2 SET MAIL = ? WHERE ID = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, Mail);
                pstmt2.setString(2, id);
                pstmt2.executeUpdate();
                
                sql2 = "UPDATE member_dbag2 SET CITY = ? WHERE ID = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, City);
                pstmt2.setString(2, id);
                pstmt2.executeUpdate();
                
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
    
    public String Select_tag(String id, String count,String tag1,String tag2,String tag3 ) { //tags,id값을 테이블에 저장하기
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            System.out.println("tag값들 출력하기"+tag1+tag2+tag3);
       
            sql2 = "INSERT INTO Tags VALUES(?,?,?,?,?)";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, id);
            pstmt2.setString(2, count);
            pstmt2.setString(3, tag1);
            pstmt2.setString(4, tag2);
            pstmt2.setString(5, tag3);
            pstmt2.executeUpdate();
            //이제 여기에 tag table에 id,tag값 저장하는 구문 넣기.
            return tag1+" "+tag2+" "+tag3;
 
    	
      }
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
    
    public String getCount_tags(String id) { //로그인 구문
    	String msg="";
    	String count = null,tag1 = null,tag2 = null,tag3=null;
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT COUNT from Tags WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	count=rs.getString(1);
        } 
            sql = "SELECT TAG1 from Tags WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	tag1=rs.getString(1);
        } 
            sql = "SELECT TAG2 from Tags WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	tag2=rs.getString(1);
        } 
            sql = "SELECT TAG3 from Tags WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	tag3=rs.getString(1);
        } 
            return count+" " + tag1+" " + tag2+" " + tag3;
    	
    	}
    		
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return msg;
    }
    
    public String Frag_Write( String id, String title, String content, String ptime, String tag) {
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            String tpk = UUID.randomUUID().toString();

                sql = "INSERT INTO tpbd VALUES(?,?,?,?,?,?)";
                pstmt2 = conn.prepareStatement(sql);
                pstmt2.setString(1, tpk);
                pstmt2.setString(2, id);
                pstmt2.setString(3, title);
                pstmt2.setString(4, content);
                pstmt2.setString(5, ptime);
                pstmt2.setString(6, tag);
                pstmt2.executeUpdate();
                returns = tpk+id+title+content+ptime+tag;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
    public String getTitle_ID(String TAG1,String TAG2, String TAG3) { //로그인 구문
    	String ID_TITLE[];
    	int i=0; int j=1; 
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT ID from tpbd WHERE TAG IN(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, TAG1);
            pstmt.setString(2, TAG2);
            pstmt.setString(3, TAG3);
            rs = pstmt.executeQuery();
            ID_TITLE = new String[100];
            while(rs.next()!=false){ 	
        		ID_TITLE[i] = rs.getString(1);//여기서막힌듯
        		i+=2; //j=2,4,6,8 ...
        	}
           
            sql2 = "SELECT TITLE from tpbd WHERE TAG IN(?,?,?)";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, TAG1);
            pstmt2.setString(2, TAG2);
            pstmt2.setString(3, TAG3);
            rs2 = pstmt2.executeQuery();
            
            	while(rs2.next()!=false){
            		ID_TITLE[j] = rs2.getString(1);
            		j+=2; //j=2,4,6,8 ...
            	}
            	
            	String id_title = String.join(",", ID_TITLE);
            	System.out.println(id_title);

            return id_title;  
    }
           
    		
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
    
    public String getTPK(String TAG1,String TAG2, String TAG3) { //로그인 구문
    	String TPK[];
    	int i=0; 
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT tpk from tpbd WHERE TAG IN(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, TAG1);
            pstmt.setString(2, TAG2);
            pstmt.setString(3, TAG3);
            rs = pstmt.executeQuery();
            TPK = new String[100];
            while(rs.next()!=false){
        		TPK[i] = rs.getString(1);//여기서막힌듯
        		i++; 
        		
        	}
           
            String tpk = String.join(",",TPK);
        	System.out.println(tpk);
            return tpk;  
    }
           
    		
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
    
    
    public String getID_TITLE_CONTENT_DATE_TAG(String tpk) { //로그인 구문

    	String id = null,title= null,content= null,date= null,tag =null;
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT ID from tpbd WHERE tpk = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tpk);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	id=rs.getString(1);
        } 
            sql = "SELECT TITLE from tpbd WHERE tpk = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tpk);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	title=rs.getString(1);
        } 
            sql = "SELECT CONTENT from tpbd WHERE tpk = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tpk);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	content=rs.getString(1);
        } 
            sql = "SELECT PTIME from tpbd WHERE tpk = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tpk);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	date=rs.getString(1);
        } 
            sql = "SELECT TAG from tpbd WHERE tpk = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tpk);
            rs = pstmt.executeQuery();
            if (rs.next()) {
            	System.out.println(rs.getString(1)); 	
            	tag=rs.getString(1);
        } 
            
            return id+","+title+","+content+","+date+","+tag;
    	
    	}
    		
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
    
    public String Withdrawal(String id) { 
    	try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
       
            sql2 = "DELETE FROM userTBL WHERE ID =?";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, id);
       
            pstmt2.executeUpdate();
           
            return null;
 
    	
      }
    		catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return null;
    }
   
   }

   
