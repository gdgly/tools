package com.honghu.oauth2.server.security.controller;

import com.honghu.oauth2.Const;
import com.honghu.oauth2.dto.LoginDTO;
import com.honghu.oauth2.server.entity.Client;
import com.honghu.oauth2.server.entity.User;
import com.honghu.oauth2.server.security.server.ISecurityServer;
import com.honghu.oauth2.server.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private ISecurityServer securityServer;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResultVO loginPage(@RequestBody LoginDTO login, HttpServletResponse response) throws IOException {

         //
         User  user = securityServer.loadUser(login.getUsername());
        if (user == null) {
            return ResultVO.error("用户不存在");
        }
        if(!passwordEncoder.matches(login.getPassword(),user.getPassword())){
            return ResultVO.error("密码错误");
        }
        // 根据 clientId 找到对应配置
        String clientId =  login.getClientId();
        Client client = securityServer.loadClient(clientId);
        if (client == null) {
            return ResultVO.error("客户端ID有误");
        }
        Claims claims =new DefaultClaims();
        Long lastTime=System.currentTimeMillis() + client.getExpiration();

        //持有者
        claims.put(Const.SUB,user);
        //过期时间
        claims.put(Const.EXP, new Date(lastTime));
        //权限
        claims.put(Const.AUTHORITIES,securityServer.loadRoles(login.getUsername()));
        String token  = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, client.getSecret())
                .setExpiration( new Date(lastTime))
                .compact();
        return ResultVO.success(token);
    }



}
