package iotrestservice.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import iotrestservice.YemekListe;

@Path("/yemekhane")
public class YemekRestService {

	@GET
	@Path("/{ogun}")

	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<YemekListe> Service(@PathParam("ogun") String ogun) throws SQLException {
		ArrayList<YemekListe> service = null;
		Connection con = null;
		try {

			RestDbConnect restDb = new RestDbConnect();
			con = restDb.getConnection();
			Date simdikiZaman = new Date();
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String tarih = df.format(simdikiZaman);

			PreparedStatement stmt = con.prepareStatement(
					"SELECT Yemek_Adi FROM yemek WHERE Tarih='" + tarih + "' and  Ogun='" + ogun + "'");

			ResultSet rs = stmt.executeQuery();

			ArrayList<YemekListe> yemekList = new ArrayList<YemekListe>();

			while (rs.next()) {

				YemekListe ymkList = new YemekListe();
				ymkList.setYemek_adi(rs.getString("Yemek_Adi"));
				yemekList.add(ymkList);
			}

			service = yemekList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(null !=con) {
				con.close();
			}
		}
		return service;
	}
}
