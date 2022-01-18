package com.hamonize.portal.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

    @RequestMapping("/aaa")
    public String dashboard() {
        return "/admin/dashboard";
	}
}
