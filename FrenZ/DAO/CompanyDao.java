/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrenZ.DAO;

import FrenZ.dbutil.DBConnection;
import FrenZ.pojo.CompaniesPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author himan
 */
public class CompanyDao {
    public static String getNewId()throws SQLException
    {
        Connection conn= DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(company_id) from companies");
        rs.next();
        String compId="";
        
        String id=rs.getString(1);
        if(id!=null){
        id=id.substring(4);
        compId="CMP-"+(Integer.parseInt(id)+1);
        }
        else
        {
            compId="CMP-101";
        }
        return compId;
    }
   
    

 public static boolean addSeller(CompaniesPojo comp)throws SQLException{
 Connection conn= DBConnection.getConnection();
 PreparedStatement ps=conn.prepareStatement("insert into companies values(?,?,?,?,?,?,?)");
 
  ps.setString(1, getNewId());

ps.setString(2, comp.getCompanyName());

ps.setString(3, comp.getOwnerName());

ps.setString(4, comp.getPassword());

ps.setString(5, "ACTIVE");

ps.setString(6, comp.getEmailId());
ps.setString(7, comp.getSecurityKey());
return ps.executeUpdate()==1;

}
 public static CompaniesPojo validate(String compName,String password)throws SQLException{
     Connection conn= DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select * from companies where company_name=? and password=?");
     ps.setString(1,compName);
     ps.setString(2,password);
     ResultSet rs=ps.executeQuery();
     CompaniesPojo comp = null;
     if(rs.next()){
         comp=new CompaniesPojo();
         comp.setCompanyId(rs.getString(1));
         comp.setOwnerName(rs.getString(3));
         comp.setCompanyName(rs.getString(2));
     }
     return comp;
 }
 public static Map<String,String> getEmailCredentialsByCompanyId(String companyId)throws SQLException{
     Connection conn= DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select email_id,security_key from companies where company_id=? and status='ACTIVE'");
      ps.setString(1,companyId);
      
      Map<String,String> companyCredentials=new HashMap<>();
      ResultSet rs=ps.executeQuery();
      if(rs.next()){
         companyCredentials.put("emailId", rs.getString(1));
          companyCredentials.put("securityKey", rs.getString(2));
          
      }
      return companyCredentials;
 }
}
