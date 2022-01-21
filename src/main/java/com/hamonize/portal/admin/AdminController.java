package com.hamonize.portal.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

    @RequestMapping("/main")
    public String dashboard() {
        return "/admin/dashboard";
	}
}
