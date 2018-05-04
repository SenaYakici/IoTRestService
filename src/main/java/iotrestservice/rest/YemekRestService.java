package iotrestservice.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public String Service(@PathParam("ogun") String ogun) {
		String service = null;

		ArrayList<YemekListe> yemekList = new ArrayList<YemekListe>();
		try {

			if (ogun.equals("oglen") || ogun.equals("aksam")) {
				RestDbConnect restDb = new RestDbConnect();
				Connection con = restDb.getConnection();
				/*Date simdikiZaman = new Date();
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String tarih = df.format(simdikiZaman);
				PreparedStatement stmt = con.prepareStatement(
						"SELECT Yemek_Adi FROM yemek WHERE Tarih='" + tarih + "' and  Ogun='" + ogun + "'");*/
				PreparedStatement stmt = con.prepareStatement(
						"SELECT Yemek_Adi FROM yemek WHERE Tarih='2018.04.30' and  Ogun='" + ogun + "'");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					YemekListe ymkList = new YemekListe();
					ymkList.setYemek_adi(rs.getString("Yemek_Adi"));

					yemekList.add(ymkList);

				}
				Gson gson = new Gson();
				service = gson.toJson(yemekList);
				return service;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}

}
