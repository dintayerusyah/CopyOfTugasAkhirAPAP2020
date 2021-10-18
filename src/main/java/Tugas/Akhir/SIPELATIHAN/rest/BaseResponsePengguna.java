package Tugas.Akhir.SIPELATIHAN.rest;

public class BaseResponsePengguna {
    private int status;
    private String message;
    private PenggunaDTO result;

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

    public PenggunaDTO getResult() {
        return result;
    }

    public void setResult(PenggunaDTO result) {
        this.result = result;
    }
}
