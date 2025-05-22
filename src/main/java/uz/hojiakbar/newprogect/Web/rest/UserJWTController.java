package uz.hojiakbar.newprogect.Web.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.hojiakbar.newprogect.Security.JwtTokenProvider;
import uz.hojiakbar.newprogect.Web.rest.Vm.LoginVm;

@RestController
@RequestMapping("/api")
public class UserJWTController {


    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public UserJWTController(JwtTokenProvider jwtTokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;

    }

    @PostMapping("/authenticate")
    public ResponseEntity authorize(@Valid @RequestBody LoginVm loginVm) {
        UsernamePasswordAuthenticationToken authenticationToken  =
                new UsernamePasswordAuthenticationToken(loginVm.getLogin(), loginVm.getPassword());
        Authentication authentication  = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(loginVm.getLogin(), authentication);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer  " + jwt );
        return new ResponseEntity(new JWTToken(jwt), headers, HttpStatus.OK);
    }
    static class JWTToken{
        private  String Idtoken;
        public JWTToken(String Idtoken) {
            this.Idtoken = Idtoken;
        }

        public String getIdtoken() {
            return Idtoken;
        }

        public void setIdtoken(String idtoken) {
            Idtoken = idtoken;
        }
    }
}
