const { getConfig } = require('../config');
module.exports = {
  '@tags': ['demo', 'example', 'web'],

  before: function(browser) {
    browser
      .url(getConfig().host)
      .waitForElementPresent('body', 5000)
      .pause(5000);
  },
  after: function(browser) {
    browser.end();
  },

  'Load RTC page and check the title : ': function(browser) {
    browser.pause(5000).assert.title('Reactive Trader Cloud');
  },
  'Check the number of tiles displayed is 9': function(browser) {
    browser
      .waitForElementPresent('.workspace-region__item', 5000)
      .elements('css selector', '.workspace-region__item', function(result) {
        browser.assert.equal(result.value.length, 9);
      });
  },
  'Check the Profit & Loss tile is displayed': function(browser) {
    browser
      .waitForElementPresent('.analytics__header-title', 5000)
      .expect.element('.analytics__header-title')
      .text.to.equal('Profit & Loss');
    browser.expect
      .element('.analytics__chart-title')
      .text.to.equal('Positions');
  },
  'Enter a new notional by clicking into the text box': function(browser) {
    browser
      .waitForElementPresent('.notional__size-input', 5000)
      .clearValue('.notional__size-input')
      .setValue('.notional__size-input', '10000')
      .click('.spot-tile__price--bid')
      .assert.visible('.trade-notification__summary-item--notional');
  }
};
