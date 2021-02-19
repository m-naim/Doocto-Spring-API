package org.naim.doctoo.controller;


import org.naim.doctoo.security.CurrentUser;
import org.naim.doctoo.security.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    @GetMapping("/up")
    public String getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return "app is up";
    }
}
