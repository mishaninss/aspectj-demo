package com.epam.utils.cucumber;

import gherkin.formatter.model.Examples;

/**
 * Created by Sergey_Mishanin on 8/10/17.
 */
public interface DataProvider {
    String[][] getData(Examples examples);
}
