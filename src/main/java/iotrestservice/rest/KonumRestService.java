package iotrestservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import iotrestservice.KonumBilgisi;

@Path("/konum")

public class KonumRestService {

	@GET
	@Path("/{x}/{y}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMsg(@PathParam("x") Double x, @PathParam("y") Double y) throws SQLException {

		String service = null;
		Double enlem = x;
		Double boylam = y;
		Double yaricap;
		String mesaj;
		Connection con=null;
		try

		{
			RestDbConnect restDb = new RestDbConnect();
			con = restDb.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM konum");
			Statement st = (Statement) con.createStatement();

			ResultSet rs = stmt.executeQuery();

			KonumBilgisi knmListAsil = new KonumBilgisi();
			double enKucuk = 1000000;
			while (rs.next())

			{
				KonumBilgisi knmList = new KonumBilgisi();

				enlem = rs.getDouble("Enlem");
				knmList.setEnlem(enlem);
				boylam = rs.getDouble("Boylam");
				knmList.setBoylam(boylam);
				yaricap = rs.getDouble("Yaricap");
				knmList.setYaricap(yaricap);
				mesaj = rs.getString("mesaj");
				knmList.setMesaj(mesaj);

				if ((((knmList.getEnlem() - x) * (knmList.getEnlem() - x))
						+ ((knmList.getBoylam() - y) * (knmList.getBoylam() - y))) < (knmList.getYaricap()
								* knmList.getYaricap())) {

					double fark = ((((knmList.getEnlem() - x) * (knmList.getEnlem() - x))
							+ ((knmList.getBoylam() - y) * (knmList.getBoylam() - y)))
							- (knmList.getYaricap() * knmList.getYaricap()));
					if (fark < 0) {
						fark *= -1;
					}
					if (enKucuk > fark) {
						enKucuk = fark;
						knmListAsil = knmList;
					}
				}
			}
			
			if (enKucuk != 1000000) {
				st.executeUpdate("Insert into ogrenci_konum(Mesaj) values('" + knmListAsil.getMesaj() + "')");
				knmListAsil.getMesaj().length();

				System.out.println(knmListAsil.getMesaj());
				return knmListAsil.getMesaj();

			}

		}

		catch (Exception e) {
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
