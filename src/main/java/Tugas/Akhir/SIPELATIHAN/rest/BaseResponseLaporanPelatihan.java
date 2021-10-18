package Tugas.Akhir.SIPELATIHAN.rest;

public class BaseResponseLaporanPelatihan {

    private int status;
    private String message;
    private LaporanDTO result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LaporanDTO getResult() {
        return result;
    }

    public void setResult(LaporanDTO result) {
        this.result = result;
    }
}
