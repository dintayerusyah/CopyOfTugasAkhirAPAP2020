package Tugas.Akhir.SIPELATIHAN.controller;

import Tugas.Akhir.SIPELATIHAN.model.PenggunaModel;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponsePengguna;
import Tugas.Akhir.SIPELATIHAN.rest.PostPenggunaDTO;
import Tugas.Akhir.SIPELATIHAN.restservice.PenggunaRestService;
import Tugas.Akhir.SIPELATIHAN.service.PenggunaService;
import Tugas.Akhir.SIPELATIHAN.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private PenggunaRestService penggunaRestService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String createAccountFormPage(Model model, final HttpServletRequest req) {
        String message = req.getParameter("message");
        model.addAttribute("user", new PenggunaModel());
        model.addAttribute("listRole", roleService.getListRole());
        model.addAttribute("message", message);
        return "create-account";
    }

    @RequestMapping(value = "/create-account", method = RequestMethod.POST)
    private RedirectView createAccountSubmit(final HttpServletRequest req, RedirectAttributes redirectAttrs,
            @ModelAttribute PenggunaModel user) {

        // validasi belom ada username yang sama dalam DB dan sistem sipegawai
        try {
            PenggunaModel pengguna = penggunaService.getPenggunaByUsername(user.getUsername());
            int statusResApi = penggunaRestService.getPengguna(user.getUsername()).block().getStatus();
            if (pengguna != null || statusResApi == 200) {
                redirectAttrs.addAttribute("message", "Username sudah digunakan");
                return new RedirectView("/user/signup");
            }
        } catch (Exception e) {
            // continue
            ;
        }

        // validasi dua password sama
        String pass1 = req.getParameter("password1");
        String pass2 = req.getParameter("password2");
        if (!pass1.equals(pass2)) {
            redirectAttrs.addAttribute("message", "Password tidak cocok");
            return new RedirectView("/user/signup");
        }

        // set password
        user.setPassword(pass2);

        // post account to webservice
        String username = user.getUsername();
        String nama = req.getParameter("nama");
        String noTelepon = req.getParameter("noTelepon");
        String tempatLahir = req.getParameter("tempatLahir");
        String tanggalLahir = req.getParameter("tanggalLahir");
        String alamat = req.getParameter("alamat");
        Long roleId = user.getRoleModel().getId_role();
        PostPenggunaDTO postPenggunaDTO = new PostPenggunaDTO(username, nama, noTelepon, tempatLahir, tanggalLahir,
                alamat, roleId);

        Mono<BaseResponsePengguna> response = penggunaRestService.postPengguna(postPenggunaDTO);
        BaseResponsePengguna res = response.block();

        if (res.getStatus() == 200) {
            // add account to internal sipelatihan
            penggunaService.addAccount(user);
        }

        return new RedirectView("/");
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model, final HttpServletRequest req) {
        String username = req.getRemoteUser();
        PenggunaModel pengguna = penggunaService.getPenggunaByUsername(username);
        BaseResponsePengguna responseAPI = penggunaRestService.getPengguna(username).block();
        model.addAttribute("pengguna", pengguna);
        model.addAttribute("responseAPI", responseAPI);
        return "profile";
    }

}
