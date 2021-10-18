package Tugas.Akhir.SIPELATIHAN.controller;

import Tugas.Akhir.SIPELATIHAN.model.JenisPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PenggunaModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseLaporanPelatihan;
import Tugas.Akhir.SIPELATIHAN.rest.RekrutmenDTO;
import Tugas.Akhir.SIPELATIHAN.restservice.PelatihanRestService;
import Tugas.Akhir.SIPELATIHAN.restservice.RekrutmenRestService;
import Tugas.Akhir.SIPELATIHAN.service.JenisPelatihanService;
import Tugas.Akhir.SIPELATIHAN.service.PelatihanService;
import Tugas.Akhir.SIPELATIHAN.service.PenggunaService;
import Tugas.Akhir.SIPELATIHAN.service.PesertaPelatihanService;
import Tugas.Akhir.SIPELATIHAN.service.PesertaService;
import Tugas.Akhir.SIPELATIHAN.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PelatihanController {
        @Autowired
        PelatihanService pelatihanService;

        @Autowired
        PesertaPelatihanService pesertaPelatihanService;

        @Autowired
        JenisPelatihanService jenisPelatihanService;

        @Autowired
        TrainerService trainerService;

        @Autowired
        PenggunaService penggunaService;

        @Autowired
        PesertaService pesertaService;

        @Autowired
        RekrutmenRestService rekrutmenRestService;

        @Autowired
        PelatihanRestService pelatihanRestService;

        @GetMapping("/pelatihan")
        private String getAllPelatihan(Model model, final HttpServletRequest req) {
                String username = req.getRemoteUser();
                PenggunaModel pengguna = penggunaService.getPenggunaByUsername(username);
                List<PelatihanModel> listPelatihan;
                // match with staff training
                if (pengguna.getRoleModel().getId_role() == 3) {
                        List<PelatihanModel> listPelatihanTemp = pelatihanService.getAllPelatihan();
                        listPelatihan = new ArrayList<PelatihanModel>();

                        String message = req.getParameter("statusDel");
                        model.addAttribute("statusDel", message);

                        for (int i = 0; i < listPelatihanTemp.size(); i++) {
                                PelatihanModel temp = listPelatihanTemp.get(i);
                                if (temp.getUuid_pengaju().equals(pengguna.getUuid())) {
                                        listPelatihan.add(temp);
                                }
                        }

                } else {
                        listPelatihan = pelatihanService.getAllPelatihan();
                }
                model.addAttribute("listPelatihan", listPelatihan);
                return "daftar-pelatihan-ver2";
        }

        @GetMapping("/pelatihan/ubah-status/{idPelatihan}")
        private String formUbahStatus(@PathVariable int idPelatihan, Model model) {
                model.addAttribute("pelatihan", pelatihanService.getPelatihanById(idPelatihan));
                return "ubah-status-pelatihan-ver2";
        }

        @PostMapping("/pelatihan/")
        private String submitUbahStatus(@ModelAttribute("pelatihan") PelatihanModel pelatihan, Model model) {
                pelatihanService.editStatusPersetujuan(pelatihan);
                List<PelatihanModel> listPelatihan = pelatihanService.getAllPelatihan();
                model.addAttribute("listPelatihan", listPelatihan);
                model.addAttribute("message",
                                "Status pelatihan " + pelatihan.getNamaPelatihan() + " berhasil diperbarui.");
                return "daftar-pelatihan-ver2";
        }

        @GetMapping("/pelatihan/{idPelatihan}")
        public String viewDetailPelatihan(@PathVariable int idPelatihan, Model model, final HttpServletRequest req) {
                // get message
                String message = req.getParameter("message");

                PelatihanModel pelatihan = pelatihanService.getPelatihanById(idPelatihan);
                List<PesertaPelatihanModel> listPeserta = pelatihan.getList_peserta();
                /* System.out.println(listPeserta); */
                /*
                 * System.out.println(pesertaPelatihanService.getAllPesertaPelatihan().get(0));
                 */

                TrainerModel trainer = pelatihan.getId_trainer();
                JenisPelatihanModel jenisPelatihan = pelatihan.getId_jenis_pelatihan();
                model.addAttribute("pelatihan", pelatihan);
                model.addAttribute("listPeserta", listPeserta);
                model.addAttribute("trainer", trainer);
                model.addAttribute("jenisPelatihan", jenisPelatihan);

                String uuid_pengaju = pelatihan.getUuid_pengaju();

                // jika dari DB
                String username_pengaju;
                if (penggunaService.getPenggunaById(uuid_pengaju) != null) {
                        username_pengaju = penggunaService.getPenggunaById(uuid_pengaju).getUsername();
                }
                // jika dari API
                else {
                        // TODO: hit API si rekrutment
                        // sementara tambah data dummy
                        username_pengaju = "data dummy nih bro";
                }
                model.addAttribute("username_pengaju", username_pengaju);

                if (pelatihan.getUuid_penyetuju() != null) {

                        String uuid_penyetuju = pelatihan.getUuid_penyetuju();

                        String username_penyetuju = penggunaService.getPenggunaById(uuid_penyetuju).getUsername();
                        model.addAttribute("username_penyetuju", username_penyetuju);
                } else {
                        model.addAttribute("username_penyetuju", "Pelatihan belum disetujui");
                }

                model.addAttribute("message", message);
                return "view-detail-pelatihan-ver2";
        }

        @GetMapping("/pelatihanDelete/idPelatihan/{idPelatihan}")
        private RedirectView delPelatihan(@PathVariable int idPelatihan, Model model, RedirectAttributes redirectAttrs) {
                List<PelatihanModel> listPelatihan = pelatihanService.getAllPelatihan();
                model.addAttribute("listPelatihan", listPelatihan);
                try {

                        PelatihanModel pelatihan = pelatihanService.getPelatihanById(idPelatihan);
                        String namaPelatihan = pelatihan.getNamaPelatihan();
                        boolean hapus = pelatihanService.deletePelatihan(pelatihan);
                        if (hapus) {
                                redirectAttrs.addAttribute("statusDel", "Pelatihan " + namaPelatihan + " berhasil dihapus");
                        } else {
                                redirectAttrs.addAttribute("statusDel", "Pelatihan " + namaPelatihan + " tidak dapat dihapus karena telah disetujui");
                        }
//                        return "daftar-pelatihan-ver2";
                        return new RedirectView("/pelatihan");

                } catch (NoSuchElementException a) {
                        return new RedirectView("/pelatihan");
//                        return "daftar-pelatihan-ver2";
                } catch (NullPointerException a) {
                        return new RedirectView("/pelatihan");
//                        return "daftar-pelatihan-ver2";
                }
        }

        @GetMapping("/pelatihan/tambah")
        private String tambahPelatihan(Model model, final HttpServletRequest req) {
                PelatihanModel pelatihan = new PelatihanModel();
                List<JenisPelatihanModel> listJenisPelatihan = jenisPelatihanService.getAllJenisPelatihan();
                List<TrainerModel> listTrainer = trainerService.getAllTrainer();

                // get message
                String message = req.getParameter("message");

                model.addAttribute("pelatihan", pelatihan);
                model.addAttribute("listJenisPelatihan", listJenisPelatihan);
                model.addAttribute("listTrainer", listTrainer);
                model.addAttribute("message", message);
                return "create-pelatihan-ver2";
        }

        @PostMapping(value = "/pelatihan/tambah", params = "simpan")
        private RedirectView simpanTambahPelatihan(final HttpServletRequest req,
                        @ModelAttribute PelatihanModel pelatihan, Model model, RedirectAttributes redirectAttrs) {

                // validasi nama pelatihan unik
                PelatihanModel pelatihanDariDB = pelatihanService
                                .getPelatihanByNamaPelatihan(pelatihan.getNamaPelatihan());
                if (pelatihanDariDB != null) {
                        redirectAttrs.addAttribute("message", "Nama pelatihan sudah digunakan");
                        return new RedirectView("/pelatihan/tambah");
                }

                // validasi waktu tanggal selesai dan mulai
                LocalDate tglMulai = pelatihan.getTanggalMulai();
                LocalDate tglSelesai = pelatihan.getTanggalSelesai();
                LocalTime waktuMulai = pelatihan.getWaktu_mulai();
                LocalTime waktuSelesai = pelatihan.getWaktu_selesai();
                if (tglMulai.compareTo(tglSelesai) > 0) {
                        redirectAttrs.addAttribute("message", "Tanggal mulai harus lebih dahulu dari tanggal selesai");
                        return new RedirectView("/pelatihan/tambah");
                } else if (tglMulai.compareTo(tglSelesai) == 0) {
                        if (waktuMulai.isAfter(waktuSelesai)) {
                                redirectAttrs.addAttribute("message",
                                                "Waktu mulai harus lebih dahulu dari waktu selesai");
                                return new RedirectView("/pelatihan/tambah");
                        }
                }

                // set status persetujuan
                String usernamePengaju = req.getRemoteUser();
                PenggunaModel pengaju = penggunaService.getPenggunaByUsername(usernamePengaju);
                pelatihan.setUuid_pengaju(pengaju.getUuid());
                pelatihan.setStatus_persetujuan(0);

                // save pelatihan
                pelatihanService.savePelatihan(pelatihan);

                return new RedirectView("/pelatihan");
        }

        @GetMapping("/pelatihan/ubah/{idPelatihan}")
        private String ubahPelatihan(@PathVariable int idPelatihan, Model model, final HttpServletRequest req) {
                PelatihanModel pelatihan = pelatihanService.getPelatihanById(idPelatihan);
                List<JenisPelatihanModel> listJenisPelatihan = jenisPelatihanService.getAllJenisPelatihan();
                List<TrainerModel> listTrainer = trainerService.getAllTrainer();

                // get message
                String message = req.getParameter("message");

                model.addAttribute("pelatihan", pelatihan);
                model.addAttribute("listJenisPelatihan", listJenisPelatihan);
                model.addAttribute("listTrainer", listTrainer);
                model.addAttribute("message", message);
                return "form-ubah-pelatihan-ver2";
        }

        @PostMapping(value = "/pelatihan/ubah", params = "simpan")
        private RedirectView simpanUbahPelatihan(final HttpServletRequest req, @ModelAttribute PelatihanModel pelatihan,
                        Model model, RedirectAttributes redirectAttrs) {

                // validasi nama pelatihan unik
                PelatihanModel pelatihanDariDB = pelatihanService
                                .getPelatihanByNamaPelatihan(pelatihan.getNamaPelatihan());
                if (pelatihanDariDB != null && pelatihanDariDB.getIdPelatihan() != pelatihan.getIdPelatihan()) {
                        redirectAttrs.addAttribute("message", "Nama pelatihan sudah digunakan");
                        return new RedirectView("/pelatihan/ubah/" + pelatihan.getIdPelatihan());
                }

                // validasi waktu tanggal selesai dan mulai
                LocalDate tglMulai = pelatihan.getTanggalMulai();
                LocalDate tglSelesai = pelatihan.getTanggalSelesai();
                LocalTime waktuMulai = pelatihan.getWaktu_mulai();
                LocalTime waktuSelesai = pelatihan.getWaktu_selesai();
                if (tglMulai.compareTo(tglSelesai) > 0) {
                        redirectAttrs.addAttribute("message", "Tanggal mulai harus lebih dahulu dari tanggal selesai");
                        return new RedirectView("/pelatihan/ubah/" + pelatihan.getIdPelatihan());
                } else if (tglMulai.compareTo(tglSelesai) == 0) {
                        if (waktuMulai.isAfter(waktuSelesai)) {
                                redirectAttrs.addAttribute("message",
                                                "Waktu mulai harus lebih dahulu dari waktu selesai");
                                return new RedirectView("/pelatihan/ubah/" + pelatihan.getIdPelatihan());
                        }
                }

                // set status persetujuan
                String usernamePengaju = req.getRemoteUser();
                PenggunaModel pengaju = penggunaService.getPenggunaByUsername(usernamePengaju);
                pelatihan.setUuid_pengaju(pengaju.getUuid());
                pelatihan.setStatus_persetujuan(0);

                // validasi kapasitas
                int jumlahPeserta = (pelatihanDariDB.getList_peserta() == null ? 0
                                : pelatihanDariDB.getList_peserta().size());
                if (jumlahPeserta > pelatihan.getKapasitas()) {
                        redirectAttrs.addAttribute("message", "Jumlah kapasitas tidak mencukupi peserta yang ada");
                        return new RedirectView("/pelatihan/ubah/" + pelatihan.getIdPelatihan());
                }

                // save pelatihan
                pelatihanService.savePelatihan(pelatihan);

                return new RedirectView("/pelatihan");
        }

        @GetMapping("/pelatihan/assign-peserta/{idPelatihan}")
        private String assignPesertaPelatihan(@PathVariable int idPelatihan, Model model,
                        final HttpServletRequest req) {
                PelatihanModel pelatihan = pelatihanService.getPelatihanById(idPelatihan);
                List<PesertaModel> listPeserta = pesertaService.getAllPeserta();

                List<PesertaPelatihanModel> listPesertaPelatihan = pelatihan.getList_peserta();
                if (listPesertaPelatihan == null) {
                        listPesertaPelatihan = new ArrayList<PesertaPelatihanModel>();
                }
                // remove peserta yang sudah terdaftar
                for (int i = 0; i < listPesertaPelatihan.size(); i++) {
                        PesertaModel peserta = listPesertaPelatihan.get(i).getPesertaModel();
                        listPeserta.remove(peserta);
                }
                int kapasitasTersisa = pelatihan.getKapasitas() - listPesertaPelatihan.size();

                model.addAttribute("pelatihan", pelatihan);
                model.addAttribute("listPeserta", listPeserta);
                model.addAttribute("kapasitasTersisa", kapasitasTersisa);
                return "assign-peserta-pelatihan-ver2";
        }

        @PostMapping(value = "/pelatihan/assign-peserta")
        public RedirectView submitAssignPeserta(@ModelAttribute PelatihanModel pelatihan, Model model,
                        final HttpServletRequest req, RedirectAttributes redirectAttrs) {
                List<PesertaModel> listPeserta = new ArrayList<PesertaModel>();
                String[] idPeserta = req.getParameter("list_assign_peserta").split(",");
                for (int i = 0; i < idPeserta.length; i++) {
                        int intIdPeserta = Integer.valueOf(idPeserta[i]);
                        PesertaModel peserta = pesertaService.getPesertaById(intIdPeserta);
                        listPeserta.add(peserta);
                }

                List<PesertaPelatihanModel> listPesertaPelatihan = pelatihan.getList_peserta();
                // cek inisiasi arrList peserta dalam pelatihan
                if (listPesertaPelatihan == null) {
                        pelatihan.setList_peserta(new ArrayList<PesertaPelatihanModel>());
                }

                // pembuatan objek peserta pelatihan
                for (int i = 0; i < listPeserta.size(); i++) {
                        PesertaPelatihanModel pesertaPelatihan = new PesertaPelatihanModel();
                        PesertaModel peserta = listPeserta.get(i);
                        pesertaPelatihan.setId_pelatihan(pelatihan);
                        pesertaPelatihan.setPesertaModel(peserta);
                        pesertaPelatihan.generateNoPeserta();
                        listPesertaPelatihan.add(pesertaPelatihan);
                        pesertaPelatihanService.savePesertaPelatihan(pesertaPelatihan);
                }
                // add ke pelatihan
                pelatihan.setList_peserta(listPesertaPelatihan);
                pelatihanService.savePelatihan(pelatihan);

                // kirim message ke detil

                redirectAttrs.addAttribute("message", idPeserta.length + " peserta berhasil di assign");
                return new RedirectView("/pelatihan/" + pelatihan.getIdPelatihan());

        }

        @GetMapping(value = "/pelatihan/import-karyawan/{idPelatihan}")
        public RedirectView contoh(@PathVariable int idPelatihan, Model model, final HttpServletRequest req,
                        RedirectAttributes redirectAttrs) {
                PelatihanModel pelatihan = pelatihanService.getPelatihanById(idPelatihan);

                // list daftar rekrutmen
                List<RekrutmenDTO> listRekrutmen = rekrutmenRestService.getAllRekrutmen().block().getResult();

                int kapasitas = (pelatihan.getKapasitas() - (pelatihan.getList_peserta().size()));
                int sizeImportRekrutmen = listRekrutmen.size();
                if (kapasitas < sizeImportRekrutmen) {
                        // return kalo gabisa ditambah
                        redirectAttrs.addAttribute("message", " Error: Kapasitas tidak memenuhi");
                        return new RedirectView("/pelatihan/" + pelatihan.getIdPelatihan());
                }

                List<PesertaModel> listPeserta = new ArrayList<PesertaModel>();
                for (int i = 0; i < sizeImportRekrutmen; i++) {
                        // check if id pelamarnya itu udah ada apa belom
                        if (pesertaService.getPesertaByIdPelamar(listRekrutmen.get(i).getIdPelamar()).size() > 0) {
                                continue;
                        }
                        PesertaModel peserta = new PesertaModel();
                        peserta.setIdPelamar(listRekrutmen.get(i).getIdPelamar());
                        peserta.setAlamat(listRekrutmen.get(i).getAlamat());
                        peserta.setDepartemen(listRekrutmen.get(i).getDepartemen());
                        peserta.setNamapeserta(listRekrutmen.get(i).getNama());
                        peserta.setNo_telepon(listRekrutmen.get(i).getNomorTelepon());
                        pesertaService.simpan(peserta);
                        listPeserta.add(peserta);
                }

                if (listPeserta.size() == 0) {
                        redirectAttrs.addAttribute("message", "Error: Tidak ada data rekrutmen baru");
                        return new RedirectView("/pelatihan/" + pelatihan.getIdPelatihan());

                }

                List<PesertaPelatihanModel> listPesertaPelatihan = pelatihan.getList_peserta();

                // cek inisiasi arrList peserta dalam pelatihan
                if (listPesertaPelatihan == null) {
                        pelatihan.setList_peserta(new ArrayList<PesertaPelatihanModel>());
                }

                // pembuatan objek peserta pelatihan
                for (int i = 0; i < listPeserta.size(); i++) {
                        PesertaPelatihanModel pesertaPelatihan = new PesertaPelatihanModel();
                        PesertaModel peserta = listPeserta.get(i);
                        pesertaPelatihan.setId_pelatihan(pelatihan);
                        pesertaPelatihan.setPesertaModel(peserta);
                        pesertaPelatihan.generateNoPeserta();
                        listPesertaPelatihan.add(pesertaPelatihan);
                        // pesertaPelatihanService.savePesertaPelatihan(pesertaPelatihan);
                }

                // add ke pelatihan
                pelatihan.setList_peserta(listPesertaPelatihan);
                pelatihanService.savePelatihan(pelatihan);

                // kirim message ke detil

                redirectAttrs.addAttribute("message", " rekrutmen berhasil di import");
                return new RedirectView("/pelatihan/" + pelatihan.getIdPelatihan());

        }

        @PostMapping(value = "/pelatihan/laporanPeserta/{idPeserta}")
        public String postLaporan(@PathVariable int idPeserta, Model model) {
                PesertaModel peserta = pesertaService.getPesertaById(idPeserta);
                String nama_peserta = pesertaService.getPesertaById(idPeserta).getNamapeserta();
                BaseResponseLaporanPelatihan responseAPI = pelatihanRestService.postLaporan(nama_peserta).block();
                model.addAttribute("peserta", peserta);
                model.addAttribute("responseAPI", responseAPI);
                return "laporan-peserta-ver2";
        }

}