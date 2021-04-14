// https://github.com/michael-ciniawsky/postcss-load-config

module.exports = {
  "plugins": {
    "postcss-import": {},
    "postcss-url": {},
  	"postcss-write-svg": {
  		"utf8": false
  	},
    "postcss-cssnext": {},
    "postcss-px-to-viewport": {
      "viewportWidth": 1920,
      "viewportHeight": 1080,
      "unitPrecision": 5,
      "viewportUnit": "vw",
      "selectorBlackList": ['.dashboard'],
      "minPixelValue": 1,
      "mediaQuery": false
    },
    "cssnano": {
      "preset": "advanced",
      "autoprefixer": false,
      "postcss-zindex": false
    }
  }
}
