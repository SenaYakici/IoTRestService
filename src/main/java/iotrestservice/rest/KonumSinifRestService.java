package iotrestservice.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import iotrestservice.SinifKonum;

@Path("/sinif")
public class KonumSinifRestService {
	@GET
	@Path("/{x}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSinif(@PathParam("x") String x) throws SQLException {
		String service = null;
		SinifKonum sinifKonum = new SinifKonum();
		Connection con=null;
		try {
			RestDbConnect restDb = new RestDbConnect();
			con = restDb.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT Mesaj FROM konum_sinif WHERE BSSID='" + x + "'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SinifKonum list = new SinifKonum();
				list.setMesaj(rs.getString("Mesaj"));
				sinifKonum = list;
			}
		
			return sinifKonum.getMesaj();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			if(null !=con) {
				con.close();
			}
		}
		return service;
	}
}