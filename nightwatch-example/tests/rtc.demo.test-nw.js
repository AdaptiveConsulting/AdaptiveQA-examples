const { getConfig } = require('../config');
module.exports = {
  '@tags': ['demo', 'example', 'web'],

  before: function(browser) {
    browser
      .url(getConfig().host)
      .waitForElementPresent('body', getConfig().timeout)
      .pause(getConfig().timeout);
  },
  after: function(browser) {
    browser.end();
  },

  'Load RTC page and check the title : ': function(browser) {
    browser.pause(getConfig().timeout).assert.title('Reactive Trader Cloud');
  },
  'Check the number of tiles displayed is 9': function(browser) {
    browser
      .waitForElementPresent('.workspace-region__item', getConfig().timeout)
      .elements('css selector', '.workspace-region__item', function(result) {
        browser.assert.equal(result.value.length, 9);
      });
  },
  'Check the Profit & Loss tile is displayed': function(browser) {
    browser
      .waitForElementPresent('.analytics__header-title', getConfig().timeout)
      .expect.element('.analytics__header-title')
      .text.to.equal('Profit & Loss');
    browser.expect
      .element('.analytics__chart-title')
      .text.to.equal('Positions');
  },

  'Enter a new notional by clicking into the text box': function(browser) {
    browser
      .waitForElementPresent('.notional__size-input', getConfig().timeout)
      .click('.spot-tile__price--bid')
      .pause(getConfig().timeout)
      .assert.visible('.trade-notification__summary-item--notional');
  }
};
