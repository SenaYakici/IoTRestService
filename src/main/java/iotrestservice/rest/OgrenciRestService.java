package iotrestservice.rest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mysql.jdbc.Connection;

import iotrestservice.Ogrenci;


@Path("/ogrenci")
public class OgrenciRestService {

	@GET
	@Path("/{Ogrenci_email}/{Ogrenci_sifre}")
	@Produces(MediaType.APPLICATION_JSON)
	
		
	public Ogrenci login(@PathParam("Ogrenci_email") String Ogrenci_email,
			@PathParam("Ogrenci_sifre") String Ogrenci_sifre) throws SQLException  {
		Ogrenci ogrenciObj = new Ogrenci();
		ogrenciObj.setOgrenci_ad("bos");

			Connection con=null;

		try {
			RestDbConnect restDb = new RestDbConnect();
			con = (Connection) restDb.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM ogrenci WHERE Ogrenci_email=? and Ogrenci_sifre=? ");
			ps.setString(1, Ogrenci_email);
			ps.setString(2, Ogrenci_sifre);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				ogrenciObj.setOgrenci_ad(rs.getString("Ogrenci_ad"));
				ogrenciObj.setOgrenci_soyad(rs.getString("Ogrenci_soyad"));
				ogrenciObj.setOgrenci_fakulte(rs.getString("Ogrenci_fakulte"));
				ogrenciObj.setOgrenci_bolum(rs.getString("Ogrenci_bolum"));
				ogrenciObj.setOgrenci_email(rs.getString("Ogrenci_email"));
				ogrenciObj.setOgrenci_sifre(rs.getString("Ogrenci_sifre"));
				ogrenciObj.setOgrenci_no(rs.getInt("Ogrenci_no"));
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		finally {
			if (null !=con) {
				con.close();
			}
			
		}
		

		return ogrenciObj;

	}

}
