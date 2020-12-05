package org.wecancodeit.libraryjpa2demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.libraryjpa2demo.repositories.AuthorRepository;

import javax.annotation.Resource;

@Controller
public class AuthorController {

    @Resource
    private AuthorRepository authorRepo;

    @RequestMapping("/authors")
    public String displayBooks(Model model){
        model.addAttribute("authors", authorRepo.findAll());
        return "authorsView";
    }


}
