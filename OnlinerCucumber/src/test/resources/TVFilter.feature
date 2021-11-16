Feature: I want to test TV filtration

  Scenario Outline: I want to filter TV's by some parameters
    Given I go to main page
    When I go to "Каталог" page
    And there I choose "Электроника" category
    And I choose "Телевидение" subcategory
    And I choose "Телевизоры" product
    And I filter product by manufacturer "<manufacturer>"
    And I filter product by maxPrice "<maxPrice>"
    And I filter product by resolution "<resolution>"
    And I filter product by minScreenSize "<minScreenSize>"
    And I filter product by maxScreenSize "<maxScreenSize>"
    Then I get products according to parameters : "<manufacturer>", "<maxPrice>", "<resolution>", "<minScreenSize>", "<maxScreenSize>"
    Examples:
      | manufacturer | maxPrice | resolution | minScreenSize | maxScreenSize |
      | Samsung      | 2000,00  | 1920x1080  | 40            | 50            |
