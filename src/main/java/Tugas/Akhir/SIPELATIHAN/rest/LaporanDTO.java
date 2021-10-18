package Tugas.Akhir.SIPELATIHAN.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LaporanDTO {
    private String id;

    private int jumlahBonus;

    private String tanggalDiberikan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getJumlahBonus() {
        return jumlahBonus;
    }

    public void setJumlahBonus(int jumlahBonus) {
        this.jumlahBonus = jumlahBonus;
    }

    public String getTanggalDiberikan() {
        return tanggalDiberikan;
    }

    public void setTanggalDiberikan(String tanggalDiberikan) {
        this.tanggalDiberikan = tanggalDiberikan;
    }
}
