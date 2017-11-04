package com.epam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.selenium.factory.WebDriverPool;

public class SearchResultsPage {
    @FindBy(css = ".search-header > h1")
    private WebElement searchHeader;

    public SearchResultsPage(){
        PageFactory.initElements(WebDriverPool.DEFAULT.getDriver("chrome"), this);
    }

    public String readSearchHeader(){
        return searchHeader.getText().trim();
    }
}
