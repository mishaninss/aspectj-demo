package com.epam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.selenium.factory.WebDriverPool;

public class Header {
    @FindBy(className = "header-menu-search-icon")
    private WebElement searchIcon;

    public Header(){
        PageFactory.initElements(WebDriverPool.DEFAULT.getDriver("chrome"), this);
    }

    public void clickSearchIcon(){
        somePrivateMethod();
        searchIcon.click();
    }

    private void somePrivateMethod(){
        //throw new NullPointerException("This is an exception");
    }
}
