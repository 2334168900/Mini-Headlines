package org.example;

import org.example.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private JwtHelper jwtHelper;


    @Test
    public void test() {
        String token=jwtHelper.createToken(1L);
        System.out.println(token);
        int userID=jwtHelper.getUserId(token).intValue();
        System.out.println(userID);
        boolean expiration=jwtHelper.isExpiration(token);
        System.out.println(expiration);



    }
}
