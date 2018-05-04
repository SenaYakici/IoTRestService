package iotrestservice;

public class YemekListe {
	String Yemek_adi;
	String Ogun;
	String Tarih;

	public String getYemek_adi() {
		return Yemek_adi;
	}

	public void setYemek_adi(String Yemek_adi) {
		this.Yemek_adi = Yemek_adi;
	}

	public String getTarih() {
		return Tarih;
	}

	public void setTarih(String Tarih) {
		this.Tarih = Tarih;
	}

	public String getOgun() {
		return Ogun;
	}

	public void setOgun(String Ogun) {
		this.Ogun = Ogun;
	}

	@Override
	public String toString() {
		return "Track [Yemek Adi=" + Yemek_adi + ", /n  Öðün=" + Ogun + ",/n Tarih =" + Tarih + "]";
	}
}
