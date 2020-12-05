package org.wecancodeit.libraryjpa2demo.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.libraryjpa2demo.repositories.CampusRepository;

import javax.annotation.Resource;

@Controller
public class CampusController {


        @Resource
        private CampusRepository campusRepo;

        @RequestMapping({"/campuses", "/", ""})
        public String displayCampuses(Model model){
            model.addAttribute("campuses", campusRepo.findAll());
            return "campusesView";

        }
}
