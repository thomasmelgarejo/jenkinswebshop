package com.springtest.demo.controller;

import com.springtest.demo.model.Product;
import com.springtest.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/index")
    public String viewProductList(Model model) {

        model.addAttribute("products", productService.readAll());
        List<Product> a = productService.readAll();
        System.out.println("Hejhej: "+ a.get(a.size()-1).getId());

        return "/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {

        model.addAttribute("product", productService.read(id));

        return "update";
    }

    //update animal
    @PostMapping("/update")
    public String update(Model model, @ModelAttribute Product product) throws SQLException {

        //update by using update service
        productService.update(product, (int) product.getId());

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) throws SQLException {

        productService.delete(id);

        return "redirect:/index";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product) throws SQLException {
        productService.create(product);

        return "redirect:/index";
    }

    @GetMapping("test")
    public String test(){

        return "test";
    }

}
