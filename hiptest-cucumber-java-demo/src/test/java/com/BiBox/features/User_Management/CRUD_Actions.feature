Feature: CRUD Actions
    As an Super Admin I want to CRUD users

  Scenario: Create New Users (uid:1229a53f-a780-4afd-8e39-d71486155374)
    # User creation form
    Given users form exists
    When Super Admin creates a new user
    Then a new user should be created

  Scenario: Update existing users (uid:0d905aac-050c-48bc-b317-98e9a9ed9c3b)
    Given users exist
    When super admin submits a change
    Then change is submitted
    And changes are applied to user account

  Scenario: Delete Users (uid:e68463b0-f400-4c26-bf51-709d9c6044c3)
    Given users exist
    When Super Admin deletes a user
    Then the user should be removed from the application

  Scenario: Read User (uid:5f0c868c-87fe-4c1a-ab10-14789bc1556a)
    Given users exist
    When super admin submits a change
    Then users are updated
