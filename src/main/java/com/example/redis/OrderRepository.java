package com.example.redis;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ItemOrder, String> {

}
