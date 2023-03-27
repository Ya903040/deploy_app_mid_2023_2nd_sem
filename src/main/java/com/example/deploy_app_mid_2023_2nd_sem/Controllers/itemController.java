package com.example.deploy_app_mid_2023_2nd_sem.Controllers;

import com.example.deploy_app_mid_2023_2nd_sem.models.TodoItem;
import com.example.deploy_app_mid_2023_2nd_sem.repositories.itemRepository;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
//test

@Controller
public class itemController {

    private final Logger logger = LoggerFactory.getLogger(itemController.class);

    public itemController(itemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }




    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }



    private final itemRepository todoItemRepository;



    @GetMapping("/")
    public ModelAndView index() {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }


        todoItem.setCreatedDate(Instant.now().atZone(ZoneId.of("Asia/Almaty")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        todoItem.setModifiedDate(Instant.now().atZone(ZoneId.of("Asia/Almaty")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "update-todo-item";
        }

        todoItem.setModifiedDate(Instant.now().atZone(ZoneId.of("Asia/Almaty")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }


}