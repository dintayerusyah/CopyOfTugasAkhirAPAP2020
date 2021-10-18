package Tugas.Akhir.SIPELATIHAN.rest;

import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;

public class BaseResponseTambahPelatihan {
    private int status;
    private String message;
    private PelatihanDTO result;

    public void setupBaseResponse(int status, String message, PelatihanModel pelatihanModel){
        this.status = status;
        this.message = message;
        PelatihanDTO pelatihanDTO = new PelatihanDTO();
        if(pelatihanModel == null){
            setResult(null);
        }else{
            pelatihanDTO.convertToDTO(pelatihanModel);
            setResult(pelatihanDTO);
        }
    }

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

    public PelatihanDTO getResult() {
        return result;
    }

    public void setResult(PelatihanDTO result) {
        this.result = result;
    }
}

