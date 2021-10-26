package com.electronics;
import java.util.*;  
import java.sql.*;  
  
public class MobileDao {  
  
    public static Connection getConnection(){  
        Connection con=null;  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","abcd");  
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  
    public static int save(Mobile m){  
        int status=0;  
        try{  
            Connection con=MobileDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "insert into mobiles.mob(name,date,notes,status) values (?,?,?,?)");  
            ps.setString(1,m.getName());  
            ps.setString(2,m.getDate());  
            ps.setString(3,m.getNotes());  
            ps.setString(4,m.getStatus());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    public static int update(Mobile e){  
        int status=0;  
        try{  
            Connection con=MobileDao.getConnection();  
            PreparedStatement ps=con.prepareStatement(  
                         "update mobiles.mob set name=?,date=?,notes=?,status=? where id=?");  
            ps.setString(1,e.getName());  
            ps.setString(2,e.getDate());  
            ps.setString(3,e.getNotes());  
            ps.setString(4,e.getStatus());  
            ps.setInt(5,e.getId());  
              
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return status;  
    }  
    public static int delete(int id){  
        int status=0;  
        try{  
            Connection con=MobileDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("delete from mobiles.mob where id=?");  
            ps.setInt(1,id);  
            status=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return status;  
    }  
    public static Mobile getEmployeeById(int id){  
    	Mobile m=new Mobile();  
          
        try{  
            Connection con=MobileDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from mobiles.mob where id=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
                m.setId(rs.getInt(1));  
                m.setName(rs.getString(2));  
                m.setDate(rs.getString(3));  
                m.setNotes(rs.getString(4));  
                m.setStatus(rs.getString(5));  
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return m;  
    }  
    public static List<Mobile> getAllEmployees(){  
        List<Mobile> list=new ArrayList<Mobile>();  
          
        try{  
            Connection con=MobileDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from mobiles.mob");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
            	Mobile m=new Mobile();  
                m.setId(rs.getInt(1));  
                m.setName(rs.getString(2));  
                m.setDate(rs.getString(3));  
                m.setNotes(rs.getString(4));  
                m.setStatus(rs.getString(5));  
                list.add(m);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
	}  
}