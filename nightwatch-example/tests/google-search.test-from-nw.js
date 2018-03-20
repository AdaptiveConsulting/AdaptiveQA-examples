const { getConfig } = require('../config');

describe('Google demo test for Mocha', function() {
  describe('with Nightwatch', function() {
    before(function(client, done) {
      done();
    });

    after('After the test actions', function(client, done) {
      client.end(function() {
        done();
      });
    });

    afterEach(function(client, done) {
      done();
    });

    beforeEach(function(client, done) {
      done();
    });

    it('uses BDD to run the Google simple test', function(client) {
      client
        .url(getConfig().host)
        .expect.element('body')
        .to.be.present.before(1000);

      client
        .setValue('input[type=text]', ['nightwatch', client.Keys.ENTER])
        .pause(1000)
        .assert.title('nightwatch - Google Search')
        .assert.containsText('#main', 'Night Watch');
    });
  });
});
