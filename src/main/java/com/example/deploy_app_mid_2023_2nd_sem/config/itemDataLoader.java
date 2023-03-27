package com.example.deploy_app_mid_2023_2nd_sem.config;

import com.example.deploy_app_mid_2023_2nd_sem.models.TodoItem;
import com.example.deploy_app_mid_2023_2nd_sem.repositories.itemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class itemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(itemDataLoader.class);

    final
    itemRepository todoItemRepository;

    public itemDataLoader(itemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public void run(String... args) {
        loadSeedData();
    }

    private void loadSeedData() {
        if (todoItemRepository.count() == 0) {
            TodoItem todoItem1 = new TodoItem("to do mid");
            TodoItem todoItem2 = new TodoItem("do not forgot about workout");

            todoItemRepository.save(todoItem1);
            todoItemRepository.save(todoItem2); 
        }

        logger.info("Number of TodoItems: {}", todoItemRepository.count());
    }
    
}
