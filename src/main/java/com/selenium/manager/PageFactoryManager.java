package com.selenium.manager;

import com.selenium.pages.google.HomePage;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }
}
