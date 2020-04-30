/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Void
 */
public class DairyDB {
    public static boolean flag = false;
    public static void insertIntoDairyDB(String product, String origin, int price, int qty, String nutritional_values, String imagePath){
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
         
            PreparedStatement ps = con.prepareStatement("INSERT INTO Dairy(product,origin, price,"
                    + "quantity, nutritional_values, photo) VALUES(?,?,?,?,?,?)");
            
            ps.setString(1, product);
            ps.setString(2, origin);
            ps.setInt(3, price);
            ps.setInt(4, qty);
            ps.setString(5, nutritional_values);
            ps.setString(6, imagePath);
            
            if(ps.executeUpdate()==1)
                JOptionPane.showMessageDialog(null, "Entry successful!");
            
        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    public static void updateDairyDB(String origin, int qty){
         try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
            
            PreparedStatement ps = con.prepareStatement("UPDATE Dairy SET quantity=? WHERE origin=?");
            
            ps.setInt(1, qty);
            ps.setString(2, origin);
            
            if(ps.executeUpdate()==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else if(ps.executeUpdate()==1 && flag){
                JOptionPane.showMessageDialog(null, "Stock updated successfully!");
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DairyDB.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
    
    public static ArrayList<ProductList> TableGenerator(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT product, origin, price,quantity, nutritional_values, photo FROM Dairy");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("product"),rs.getString("origin"),
                        rs.getInt("price"),rs.getInt("quantity"),rs.getString("nutritional_values"),
                        rs.getString("photo"));
                
                list.add(pl);

            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    public static ArrayList<ProductList> homePageContent(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT product, origin, price,quantity, nutritional_values, photo FROM Dairy ORDER BY id DESC LIMIT 3");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("producct"),rs.getString("origin"),
                        rs.getInt("price"),rs.getInt("quantity"),rs.getString("nutritional_values"),
                        rs.getString("photo"));
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
    public static ArrayList<ProductList> checkStock(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT product, origin, price,quantity, nutritional_values, photo FROM Dairy");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        0, rs.getInt("mquantity"),null, null);
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
       public static void delete(String origin){
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
            PreparedStatement ps = con.prepareStatement("DELETE FROM Dairy WHERE origin=?");
            ps.setString(1, origin);
            if(ps.executeUpdate()==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else
                JOptionPane.showMessageDialog(null, "Entry deleted successfully!");
            
        } catch (SQLException ex) {
            Logger.getLogger(FruitsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            
   }
    
    
    
}
