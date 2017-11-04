package com.epam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.selenium.factory.WebDriverPool;

public class SearchForm {
    @FindBy(css = ".extended-search input[name='q']")
    private WebElement searchText;

    @FindBy(css = ".extended-search button[type='submit']")
    private WebElement submitButton;

    public SearchForm(){
        PageFactory.initElements(WebDriverPool.DEFAULT.getDriver("chrome"), this);
    }

    public void enterSearchText(String text){
        searchText.sendKeys(text);
    }

    public void submitSearchForm(){
        submitButton.click();
    }
}
