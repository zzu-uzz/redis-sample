package com.example.redis;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

  // htt://localhost:8080/set?q=password
  @GetMapping("/set")
  public String set(@RequestParam("q") String q, HttpSession session) {
    session.setAttribute("q", q);
    return "Saved: " + q;
  }

  // http://localhost:8080/get
  @GetMapping("/get")
  public String get(HttpSession session) {
    return String.valueOf(session.getAttribute("q"));
  }
}
