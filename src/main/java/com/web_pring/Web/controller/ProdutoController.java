package com.web_pring.Web.controller;

import com.web_pring.Web.model.Produto;
import com.web_pring.Web.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository repo;

    public ProdutoController(ProdutoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", repo.findAll());
        return "produtos-list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Produto produto) {
        repo.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", repo.findById(id).orElse(null));
        return "produtos-view";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("produto", repo.findById(id).orElse(null));
        return "produtos-form";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Produto produto) {
        produto.setId(id);
        repo.save(produto);
        return "redirect:/produtos";
    }
}