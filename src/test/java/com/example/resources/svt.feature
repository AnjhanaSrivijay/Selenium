Feature: SVT

  Scenario: Verify SVT homepage
    Given user launches the homepage
    When user accepts the cookies
    Then user verify the page title
    And user verify the SVT logo

  Scenario: Verify the Menu of SVT page
    Given user launches the homepage
    When user accepts the cookies
    Then verify the menu on the homepage

  Scenario: Verify Tillgänglighet Link on SVT homepage
    Given user launches the homepage
    When user accepts the cookies
    And user scrolls to the footer section
    Then user verify the Tillgänglighet Link is available

  Scenario: Validate the Tillgänglighet Link
    Given user launches the homepage
    When user accepts the cookies
    And user scrolls to the footer section
    Then user verify the Tillgänglighet Link is available
    When user clicks on the link
    Then user should land on the Tillgänglighet page
    And user verify the page title

  Scenario: Verify program Category
    Given user launches the homepage
    When user accepts the cookies
    And user clicks on the program
    Then user verifies the title of the program page
    And user verifies all the categories are displayed

  Scenario: Verify Kanaler category
    Given user launches the homepage
    When user accepts the cookies
    And user clicks on the kanaler
    Then user verifies the title of the kanaler page
    When user clicks on the programs tab
    Then user verifies the title of the page

  Scenario: Verify Inställingar link on SVT homepage
    Given user launches the homepage
    When user accepts the cookies
    And user scrolls to the footer section
    Then user verify the Inställingar Link is available

  Scenario: Validate the Inställingar Link
    Given user launches the homepage
    When user accepts the cookies
    And user scrolls to the footer section
    Then user verify the Inställingar Link is available
    When user clicks on the Inställingar link
    Then user should land on the Inställingar page

  Scenario: Validate Nyheter Category
    Given user launches the homepage
    When user accepts the cookies
    And user selects the nyheter category
    Then user should land on the Nyheter page
    And user verify the page title

  Scenario:  Verify Barn Category
    Given user launches the homepage
    When user accepts the cookies
    And user validates the title of the Barn category page
    And user clicks on the program tab of the barn category
    Then  user verifies the title of the program category of barn page

  Scenario: Validate the Sök option
    Given user launches the homepage
    When user accepts the cookies
    And user clicks on the Sök tab
    And user provides input as drama
    When user click submit
    Then user should land on the Drama category page