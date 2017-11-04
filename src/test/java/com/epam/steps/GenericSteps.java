package com.epam.steps;

import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import ru.stqa.selenium.factory.WebDriverPool;

public class GenericSteps {

    WebDriver driver = WebDriverPool.DEFAULT.getDriver("chrome");

    @Given("I'm on the \"(.*)\" page")
    public void iSearchFor(String url){
        driver.get(url);
    }
}
