@Search
Feature: Search user stories

  Scenario Outline: Simple search
    Given I'm on the "http://epam.com" page
    When I search for <text> from Header
    Then I see "<searchHeader>" text in the Search Header

    Examples:
    dataprovider: com.epam.utils.DemoDataProvider
    |text|searchHeader|
    |    |            |