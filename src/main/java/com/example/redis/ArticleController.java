package com.example.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

  private final ValueOperations<String, Integer> ops;

  public ArticleController(RedisTemplate<String, Integer> articleTemplate) {
    ops = articleTemplate.opsForValue();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void read(@PathVariable("id") Long id) {
    ops.increment("articles:%d".formatted(id));
  }


}
