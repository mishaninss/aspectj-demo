package com.epam.steps;

import com.epam.pages.SearchResultsPage;
import cucumber.api.java.en.Given;
import org.junit.Assert;

public class SearchResultsSteps {

    SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Given("I see \"(.*)\" text in the Search Header")
    public void iSeeSearchResultstext(String text){
        try {
            String actualtext = searchResultsPage.readSearchHeader();
            Assert.assertEquals(text, actualtext);
        } catch (Exception ex) {
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
        }
    }
}
