package com.epam.utils;

import com.epam.utils.cucumber.DataProvider;
import gherkin.formatter.model.Examples;

/**
 * Created by Sergey_Mishanin on 8/10/17.
 */
public class DemoDataProvider implements DataProvider {
    @Override
    public String[][] getData(Examples examples) {
        String[][] data =
            {
                {"java", "We found 36 results matching your search"},
                {"cucumber", "We found 1 result matching your search"},
                {"webdriver", "We found 1 result matching your search"}
            };
        return data;
    }
}
