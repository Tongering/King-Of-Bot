package com.tongering.backend.service.impl.user.account;

import com.tongering.backend.pojo.User;
import com.tongering.backend.service.impl.util.UserDetailsImpl;
import com.tongering.backend.service.user.account.LoginService;
import com.tongering.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);


        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl loginuser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginuser.getUser();

        String jwt = JwtUtil.createJWT(user.getId().toString());

        Map<String, String> map = new HashMap<>();

        map.put("error_message", "success");
        map.put("token", jwt);


        return map;
    }
}
