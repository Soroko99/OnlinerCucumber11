package Onliner.tests;

import framework.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends Browser {

    @BeforeTest
    public void driverStart(){
        setup();
        getURl();
    }

    @AfterTest
    public void testCompletion(){
        driverClose();
    }
}
