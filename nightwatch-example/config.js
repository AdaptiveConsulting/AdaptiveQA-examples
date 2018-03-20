const path = require('path');
let configManager;
let appConfig;

function getConfig() {
  if (!configManager) {
    configManager = require('node-config-manager');
    configManager.init({
      configDir: path.resolve('config')
    });
    configManager.addConfig('app');
    appConfig = configManager.getConfig('app');
  }

  return appConfig;
}

module.exports = {
  getConfig
};
