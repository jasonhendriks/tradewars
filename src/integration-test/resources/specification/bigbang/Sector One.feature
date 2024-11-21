Feature: Sectors One Warp Lanes

  Scenario: Sector One has Warp Lanes to Sectors One through Seven

    When BigBang runs

    Then Sector One should have Warp Lanes in to and out of:
      | Sector |
      | 2      |
      | 3      |
      | 4      |
      | 5      |
      | 6      |
      | 7      |
