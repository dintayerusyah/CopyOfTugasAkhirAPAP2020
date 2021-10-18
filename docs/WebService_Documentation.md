# SIPELATIHAN

REST API untuk SIPELATIHAN oleh kelompok F-1.

## POST Membuat Pelatihan

POST API untuk membuat pelatihan.<br>
**URL**: https://sipelatihan.herokuapp.com/api/v1/pelatihan
**Method**: POST
**Request Example**:
```
json
{
 "nama_pelatihan":"Pelatihan OnBoarding", //string 
 "deskripsi":"Pelatihan", //string 
 "kapasitas":200, //int
 "tanggal_mulai":"2021-01-10", //string 
 "tanggal_selesai":"2021-01-10", //string 
 "waktu_mulai":"10:10", //string 
 "waktu_selesai":"12:12", //string 
 "status_persetujuan":0, //int
 "id_trainer":1, //int
 "id_jenis_pelatihan":1, //int
 "uuid_pengaju":"40288184764d4e2901764d51534f0000", //string, uuid pengguna yang mengajukan pelatihan  
}
```

**Success Response**:
```
json
{
  "status": 200,
  "message": "Add pelatihan success",
  "result": {
    "nama_pelatihan":"Pelatihan OnBoarding", //string 
    "deskripsi":"Pelatihan", //string 
    "kapasitas":200, //int
    "tanggal_mulai":"2021-01-10", //string 
    "tanggal_selesai":"2021-01-10", //string 
    "waktu_mulai":"10:10", //string 
    "waktu_selesai":"12:12", //string 
    "status_persetujuan":0, //int
    "id_trainer":1, //int
    "id_jenis_pelatihan":1, //int
    "uuid_pengaju":"40288184764d4e2901764d51534f0000", //string, uuid pengguna yang mengajukan pelatihan  
  }
}
```
**failed response when nama pelatihan already exist**:
```
json
{
  "status": 400,
  "message": "Nama pelatihan sudah terdapat dalam database sipelatihan"
  "result": null
}
```

**failed response when end datetime < start datetime**:
```
json
{
  "status": 400,
  "message": "Waktu mulai pelatihan harus lebih dahulu dibandingkan waktu selesai pelatihan"
  "result": null
}
```

## GET Data Peserta Pelatihan

GET API untuk mendapatkan data pelatihan berdasarkan nama peserta
**URL**: https://sipelatihan.herokuapp.com/api/v1/pelatihan/{namaPeserta}
**Method**: GET

**Parameter**:
```
{
  "namaPeserta": "Isla Moore" //string
}
```

**Response Example**
```
json
{
  "status": 200,
  "message": "success",
  "result": [
    {
      "namaPelatihan":"ngodingkuy",
      "tanggalMulai":"2020-12-14",
      "tanggalSelesai":"2020-12-28",
      "idPelatihan":1
    },
    {
      "namaPelatihan":"VueJSIntro",
      "tanggalMulai":"2020-12-28",
      "tanggalSelesai":"2021-01-08",
      "idPelatihan":2
    }
  ]
}
```

**failed response**:
```
json
{
  "status": 404,
  "message": "Peserta dengan nama Mark tidak ditemukan di SIPELATIHAN!",
  "error": "Not Found"
}
```

## GET Semua Data Peserta Pelatihan

GET API untuk mendapatkan data pelatihan berdasarkan nama peserta
**URL**: https://sipelatihan.herokuapp.com/api/v1/all-pelatihan
**Method**: GET

**Response Example**
```
json
{
  "status": 200,
  "message": "success",
  "result": [
        {
            "idPelatihan": 1,
            "namaPelatihan": "Angga",
            "deskripsi": "angga",
            "kapasitas": 3,
            "tanggalMulai": "2000-11-01",
            "tanggalSelesai": "2000-12-01",
            "waktu_mulai": "11:11:00",
            "waktu_selesai": "12:12:00",
            "status_persetujuan": 0,
            "id_trainer": {
                "id": 1,
                "noKtp": "aakdwnawkdankadn",
                "nama_trainer": "angga",
                "no_telepon": "ang",
                "email": "erlanggamuhammad01@gmail.com"
            },
            "id_jenis_pelatihan": {
                "id": 1,
                "nama": "Onboarding"
            },
            "uuid_penyetuju": null,
            "uuid_pengaju": "2c904b16766ad98401766adc01d50000"
        },
        {
            "idPelatihan": 2,
            "namaPelatihan": "Test Pelatihan",
            "deskripsi": "Pelatihan",
            "kapasitas": 25,
            "tanggalMulai": "2021-04-10",
            "tanggalSelesai": "2021-04-10",
            "waktu_mulai": "11:11:00",
            "waktu_selesai": "12:12:00",
            "status_persetujuan": 0,
            "id_trainer": {
                "id": 1,
                "noKtp": "aakdwnawkdankadn",
                "nama_trainer": "angga",
                "no_telepon": "ang",
                "email": "erlanggamuhammad01@gmail.com"
            },
            "id_jenis_pelatihan": {
                "id": 1,
                "nama": "Onboarding"
            },
            "uuid_penyetuju": null,
            "uuid_pengaju": "40288184764d4e2901764d51534f0000"
        },
    ],
}
```