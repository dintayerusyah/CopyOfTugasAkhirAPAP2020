package Tugas.Akhir.SIPELATIHAN.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Tugas.Akhir.SIPELATIHAN.service.RoleService;


@Controller
public class FrontController {

        @Autowired
        RoleService roleService;
        
        @RequestMapping("/")
        private String homePage(Model model) {
                return "home-ver2";
        }

        @RequestMapping("/login")
        public String login(){
            return "login";
        }
    
}
