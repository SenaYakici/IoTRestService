package iotrestservice.rest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import iotrestservice.DersProgrami;

@Path("/dersprogrami")
public class DersProgramiRestService {

	@GET
	@Path("/{Ogrenci_email}/{Ogrenci_sifre}/{Ders_gunu}")
	@Produces(MediaType.APPLICATION_JSON)

	public List<DersProgrami> Service(@PathParam("Ogrenci_email") String Ogrenci_email,
			@PathParam("Ogrenci_sifre") String Ogrenci_sifre, @PathParam("Ders_gunu") String Ders_gunu) throws SQLException {
		
		ArrayList<DersProgrami> programList = new ArrayList<DersProgrami>();
		Connection con=null;
		try {
			if (Ders_gunu.equals("Pazartesi") || Ders_gunu.equals("Sali") || Ders_gunu.equals("Carsamba")
					|| Ders_gunu.equals("Persembe") || Ders_gunu.equals("Cuma")) {
				RestDbConnect restDb = new RestDbConnect();
				 con = (Connection) restDb.getConnection();
				PreparedStatement ps = con
						.prepareStatement("SELECT o.Ogrenci_no, dp.Ders_kodu,dp.Ders_adi, dp.Ders_baslangic_saati,"
								+ " dp.Ders_bitis_saati, dp.Sinif_kodu, dp.Ders_gunu, "
								+ "dp.Ogretim_uyesi, k.Mesaj FROM konum k, ogrenci o, "
								+ "ders_programi dp where o.Ogrenci_email=? "
								+ " and o.Ogrenci_sifre= ? and dp.Ders_gunu=?  and o.Ogrenci_no=dp.Ogrenci_no  and k.ID=dp.Konum_id");
				ps.setString(1, Ogrenci_email);
				ps.setString(2, Ogrenci_sifre);
				ps.setString(3, Ders_gunu);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					DersProgrami program = new DersProgrami();

					program.setDersKodu(rs.getString("Ders_kodu"));
					program.setDersAdi(rs.getString("Ders_adi"));
					program.setDersBslngcSaati(rs.getString("Ders_baslangic_saati"));
					program.setDersBtsSaati(rs.getString("Ders_bitis_saati"));
					program.setSinifKodu(rs.getString("Sinif_kodu"));
					program.setDersGunu(rs.getString("Ders_gunu"));
					program.setOgretimUyesi(rs.getString("Ogretim_uyesi"));
					program.setBinaMesaj(rs.getString("Mesaj"));
					programList.add(program);

				}


			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			if(null !=con) {
				con.close();
			}
		}

		return programList;

	}
}
