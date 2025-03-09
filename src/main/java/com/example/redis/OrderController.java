package com.example.redis;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderRepository orderRepository;

  @PostMapping
  public ItemOrder create(@RequestBody ItemOrder order) {
    return orderRepository.save(order);
  }

  @GetMapping
  public List<ItemOrder> readAll() {
    List<ItemOrder> orders = new ArrayList<>();
    orderRepository.findAll().forEach(orders::add);
    return orders;
  }

  @GetMapping("/{id}")
  public ItemOrder readOne(@PathVariable("id") String id) {
    return orderRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
    );
  }

  @PutMapping("/{id}")
  public ItemOrder update(@PathVariable("id") String id, @RequestBody ItemOrder order) {
    ItemOrder updatedOrder = orderRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
    );
    updatedOrder.setItem(order.getItem());
    updatedOrder.setCount(order.getCount());
    updatedOrder.setTotalPrice(order.getTotalPrice());
    updatedOrder.setStatus(order.getStatus());
    return orderRepository.save(updatedOrder);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) {
    orderRepository.deleteById(id);
  }
}
