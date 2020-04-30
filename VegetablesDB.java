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
public class VegetablesDB {
    public static boolean flag = false;
    public static void insertIntoVegetablesDB(String product, String origin, int price, int qty, String nutritional_values, String imagePath){
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
         
            PreparedStatement ps = con.prepareStatement("INSERT INTO Vegetables(product, origin, price,"
                    + "quantity, nutritional_values, photo) VALUES(?,?,?,?,?,?)");
            
            ps.setString(1,product);
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
    
    public static void updateVegetablesDB(String origin, int qty){
         try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
            
            PreparedStatement ps = con.prepareStatement("UPDATE Vegetables SET quantity=? WHERE origin=?");
            
            ps.setInt(1, qty);
            ps.setString(2, origin);
            if(ps.executeUpdate()==0)
                JOptionPane.showMessageDialog(null, "Entry does not exist!");
            else if(ps.executeUpdate()==1 && flag){
                JOptionPane.showMessageDialog(null, "Stock updated successfully!");
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
    
    public static ArrayList<ProductList> TableGenerator(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT product, origin, price,quantity,nutritional_values, photo FROM Vegetables");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("product"),rs.getString("origin"),
                        rs.getInt("price"),rs.getInt("quantity"),rs.getString("nutritional_values"),
                        rs.getString("photo"));
                
                list.add(pl);

            }

        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
    public static ArrayList<ProductList> homePageContent(){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT ptoduct, origin, price,quantity, nutritional_values, photo FROM Vegetables ORDER BY id DESC LIMIT 3");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("product"),rs.getString("origin"),
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
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT product, origin, price, quantity FROM Vegetables");
            
            ProductList pl;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("product"),rs.getString("origin"),
                        0, rs.getInt("quantity"),null, null);
                
                list.add(pl);

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
       public static void delete(String origin){
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
            PreparedStatement ps = con.prepareStatement("DELETE FROM Vegetables WHERE origin=?");
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
