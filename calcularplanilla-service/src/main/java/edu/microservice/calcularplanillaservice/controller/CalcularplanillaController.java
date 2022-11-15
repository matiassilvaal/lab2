package edu.microservice.calcularplanillaservice.controller;


import edu.microservice.calcularplanillaservice.entity.CalcularplanillaEntity;
import edu.microservice.calcularplanillaservice.model.TokenInfo;
import edu.microservice.calcularplanillaservice.model.UserInfo;
import edu.microservice.calcularplanillaservice.service.CalcularplanillaService;
import edu.microservice.calcularplanillaservice.service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/calcularplanilla")
public class CalcularplanillaController {

    @Autowired
    CalcularplanillaService calcularplanillaService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService usuarioDetailsService;
    @Autowired
    private JwtUtilService jwtUtilService;
    @PostMapping
    public ResponseEntity<CalcularplanillaEntity> calculosDePlanilla(){
        if(calcularplanillaService.calcularPlanilla()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<CalcularplanillaEntity>> getAll(){
        if(calcularplanillaService.getAll().isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(calcularplanillaService.getAll());
    }
    @PostMapping("/autenticar")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody UserInfo userInfo) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userInfo.getUsuario(), userInfo.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(userInfo.getUsuario());
        final String jwt = jwtUtilService.generateToken(userDetails);
        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);
    }

}