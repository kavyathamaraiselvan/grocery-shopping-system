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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Void
 */
public class Search {
    static int Fruits = 0;
    static int Vegetables = 0;
    static int Dairy = 0;
    public static ArrayList<ProductList> VegetablesSearch(String origin){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/FruitsDB.db");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Fruits WHERE product=? COLLATE NOCASE OR origin=? COLLATE NOCASE");
            ps.setString(1, origin);
            ps.setString(2, origin);
            ResultSet rs = ps.executeQuery();
            
            ProductList pl, gl, kl=null;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("product"),rs.getString("origin"),
                        rs.getInt("price"),rs.getInt("quantity"),rs.getString("nutritional_values"),
                        rs.getString("photo"));
                Fruits++;
                
                list.add(pl);

            }
            con.close();
            
            con = DriverManager.getConnection("jdbc:sqlite:DBs/VegetablesDB.db");
            ps = con.prepareStatement("SELECT * FROM Vegetables WHERE product=? COLLATE NOCASE OR origin=? COLLATE NOCASE");
            ps.setString(1, origin);
            ps.setString(2, origin);
            rs = ps.executeQuery();

            
            while(rs.next()){
                gl = new ProductList(rs.getString("product"),rs.getString("origin"),
                        rs.getInt("price"),rs.getInt("quantity"),rs.getString("nutrtional_values"),
                        rs.getString("photo"));
                Vegetables++;
                list.add(gl);

            }
            con.close();
            
            con = DriverManager.getConnection("jdbc:sqlite:DBs/DairyDB.db");
            ps = con.prepareStatement("SELECT * FROM Dairy WHERE product=? COLLATE NOCASE OR origin=? COLLATE NOCASE");
            ps.setString(1, origin);
            ps.setString(2, origin);
            rs = ps.executeQuery();

            
            while(rs.next()){
                kl = new ProductList(rs.getString("product"),rs.getString("origin"),
                        rs.getInt("price"),rs.getInt("quantity"),rs.getString("nutritional_values"),
                        rs.getString("photo"));
                Dairy++;
                list.add(kl);

            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(VegetablesDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
}
