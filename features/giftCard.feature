Feature: Gift Card Module

 Background:
    Given User is on home page and select city "Kolkata"
    
  @GiftCardNavigation
  Scenario Outline: Navigate to the Gift Card section and validate Check Gift Card Balance icon
    When User navigate to the gift card section
    Then User should see the "<linktext2>" icon displayed
    
    Examples:
    |city   |linktext1|linktext2              |
    |Kolkata|Gift Card|Check Gift Card Balance|

  @GiftCardInvalidVoucher
  Scenario Outline: Validate error message for invalid gift card voucher
    When User navigate to the gift card section
    When User click on the "<linktext2>" icon
    And User enter an invalid voucher code "<code>"
    And User click on the "<btnTxt>" button
    Then User should see an error message "<message>"
    
    Examples:
    |city   |code     |message            |linktext1|linktext2              |btnTxt       |
    |Kolkata|XYZ1234BC|Please verify input|Gift Card|Check Gift Card Balance|Check Balance|