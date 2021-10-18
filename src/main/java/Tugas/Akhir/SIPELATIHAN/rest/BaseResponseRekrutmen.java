package Tugas.Akhir.SIPELATIHAN.rest;

import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;

import java.util.List;

public class BaseResponseRekrutmen {
    private int status;
    private String message;
    private List<RekrutmenDTO> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public List<PesertaModel> getResult() {
//        return result;
//    }
//
//    public void setResult(List<PesertaModel> result) {
//        this.result = result;
//    }

    public List<RekrutmenDTO> getResult() {
        return result;
    }

    public void setResult(List<RekrutmenDTO> result) {
        this.result = result;
    }
}
