package com.example.deploy_app_mid_2023_2nd_sem.repositories;


import com.example.deploy_app_mid_2023_2nd_sem.models.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface itemRepository extends CrudRepository<TodoItem, Long> {
}
