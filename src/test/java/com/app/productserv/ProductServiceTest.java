package com.app.productserv;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProductServiceTest {

    @Test
    public void sampleTest(){
        int a = 1;
        int b = 2;
        Assert.isTrue(a>0,"Not Working");
    }

}
