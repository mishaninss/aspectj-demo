package com.epam.steps;

import com.epam.pages.Header;
import com.epam.pages.SearchForm;
import cucumber.api.java.en.When;

public class HeaderSteps {

    private Header header = new Header();
    private SearchForm searchForm = new SearchForm();

    @When("I search for (.*) from Header")
    public void iSearchFor(String searchText){
        try {
            try {
                try {
                    header.clickSearchIcon();
                } catch (Exception ex) {
                    System.out.println(ex.getClass());
                    System.out.println(ex.getMessage());
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        searchForm.enterSearchText(searchText);
        searchForm.submitSearchForm();
    }
}
