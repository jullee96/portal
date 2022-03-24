package com.hamonize.portal.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductRepository pr;


    @RequestMapping("/pricing")
    public String pricing(Product vo, Model model) {
        List<Product> list = pr.findByPdstatusOrderByPdid("s");
        model.addAttribute("list", list);
        model.addAttribute("listLen", list.size());

        return "/product/pricing";
    }

}
