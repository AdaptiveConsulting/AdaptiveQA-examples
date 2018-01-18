const configManager = require('node-config-manager');
const appConfig = configManager.getConfig('app');

module.exports.getConfig = function() {
  return appConfig;
};
