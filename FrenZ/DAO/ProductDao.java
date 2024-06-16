/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrenZ.DAO;

import FrenZ.dbutil.DBConnection;
import FrenZ.pojo.ProductPojo;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author himan
 */
public class ProductDao {
    public static String getNewId()throws SQLException
    {
        Connection conn= DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery("Select max(product_id) from products");
        rs.next();
        String productId="";
        
        String id=rs.getString(1);
        if(id!=null){
        id=id.substring(4);
        productId="PRD-"+(Integer.parseInt(id)+1);
        }
        else
        {
            productId="PRD-101";
        }
        return productId;
    }
    public static boolean addproduct(ProductPojo product)throws SQLException,IOException
    {
        //convert image to BufferedImage
        BufferedImage bufferedImage = new BufferedImage(product.getProductImage().getWidth(null),product.getProductImage().getHeight(null),BufferedImage.TYPE_INT_RGB);
        // Draw the bufferedimage 
        Graphics gr = bufferedImage.getGraphics();
        gr.drawImage(product.getProductImage(), 0, 0, null);
        //convert buffered imAGE INTO byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, product.getProductImageType(), baos);
        byte[] imageData=baos.toByteArray();
        //convert byte array to inputStream
        ByteArrayInputStream bais=new ByteArrayInputStream(imageData);
        
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into products values(?,?,?,?,?)");
        
        ps.setString(1, getNewId());
ps.setString(2, product.getCompanyId());
ps.setString(3, product.getProductName());
ps.setDouble(4, product.getProductPrice());
ps.setBinaryStream(5, bais, imageData.length);
        int x = ps.executeUpdate();
        return x > 0;
        
        
    }
    public static Map<String, ProductPojo> getProductDetailsByCompanyId(String companyId) throws SQLException, IOException      {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from products where company_id=?");
        ps.setString(1, companyId);
        ResultSet rs = ps.executeQuery();
        Map<String, ProductPojo> productDetails = new HashMap<>();
        while (rs.next()) {
            ProductPojo product = new ProductPojo();
            product.setProductName(rs.getString(3));
            product.setProductPrice(rs.getDouble(4));
            InputStream inputStream = rs.getBinaryStream("product_image");

            // Convert InputStream to BufferedImage
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // Convert BufferedImage to Image
            Image image = bufferedImage;
            product.setProductImage(image);
            productDetails.put(product.getProductName(), product);

        }
        return productDetails;
    }

}
