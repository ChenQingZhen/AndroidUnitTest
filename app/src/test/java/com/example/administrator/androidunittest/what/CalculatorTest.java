package com.example.administrator.androidunittest.what;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Copyright ©  bookegou.com
 * Created by Administrator on 2016/11/21.
 */
public class CalculatorTest {
    Calculator mCalculator;

    @Before
    public void setup(){
        mCalculator =new Calculator();
    }
    @Test
    public void testAdd(){

        Assert.assertEquals(mCalculator.add(100,100),200);
        Assert.assertEquals(mCalculator.add(1,1),2);
    }
    @Test
    public void testMultiply(){
        Assert.assertEquals(mCalculator.multiply(2,4),8);
    }
    @Test
    @Ignore("not implemented yet")
    public void testFactorial(){

    }
    @Test(expected = IllegalArgumentException.class)
    public void testDivide(){
       mCalculator.divide(3,0);
    }


}