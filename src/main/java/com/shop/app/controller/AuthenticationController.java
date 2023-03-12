package com.shop.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "user/login";
    }

    // @GetMapping("/logout")
    // public String logOut(HttpServletRequest request){
    //     request.getSession().invalidate();
    //     return "redirect:/auth/login";
    // }

    // @GetMapping("/register")
    // public String openRegister(){
    //     return "user/register";
    // }

    // @PostMapping("/register")
    // public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    //     Account account = repository.findByEmail(request.getEmail()).orElse(null);
    //     if(account != null){
    //         return ResponseEntity.badRequest().build();
    //     }
    //     return ResponseEntity.ok(service.register(request));
    // }

    // @PostMapping(path = "/sigin",consumes = "application/json", produces = "application/json")
    // public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request,HttpServletRequest req,HttpServletResponse res) {
    //     try {
    //         HttpSession session = req.getSession(true);
    //         session.setAttribute("username", request.getEmail());
    //         session.setAttribute("role", service.authenticate(request).getRole());
    //         return ResponseEntity.ok(service.authenticate(request));
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }
}