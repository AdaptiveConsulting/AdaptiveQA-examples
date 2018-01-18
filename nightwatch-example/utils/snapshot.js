const path = require('path');
const fs = require('fs');

exports.takeSnapshot = function(mochaCtx, index = 0) {
  const basePath = path.resolve(__dirname, '../../../', 'out/screenshots/');
  const levels = getTestLevels(mochaCtx);

  // Creating directories
  let currentPath = basePath;
  for (let i = 0; i < levels.length - 1; i++) {
    const dirPath = path.resolve(currentPath, levels[i]);
    if (!fs.existsSync(dirPath)) {
      fs.mkdirSync(dirPath);
    }

    currentPath = dirPath;
  }

  return nightwatch => {
    const imgPath = path.resolve(
      currentPath,
      `${index}_${levels[levels.length - 1]}.png`
    );

    return nightwatch.screenshot(imgPath);
  };
};

function getTestLevels(mochaCtx) {
  const { test } = mochaCtx;
  const names = [];

  let currentItem = test;
  while (currentItem && currentItem.title && currentItem.title.length > 0) {
    const title = currentItem.title
      .replace(/ /g, '_')
      .replace(/[^a-zA-Z_]/g, '')
      .toLowerCase();
    names.unshift(title);

    currentItem = currentItem.parent;
  }

  const fileName = test.file.toString();

  return [fileName, ...names];
}
