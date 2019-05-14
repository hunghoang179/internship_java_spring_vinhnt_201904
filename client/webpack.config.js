const path = require("path");
// const UglifyJsPlugin = require("uglifyjs-webpack-plugin");

module.exports = {
  entry: ["babel-polyfill", "./src/index.js"],
  mode: "development",
  output: {
    path: path.resolve(__dirname, "../src/main/resources/static/javascript"),
    filename: "index.js"
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: "babel-loader",
        query:
        {
          presets:['react']
        }
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      }
    ]
  },
  devtool: "cheap-module-eval-source-map",
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src/")
    },
    extensions: [".js"]
  }
  // plugins: [new UglifyJsPlugin({ sourceMap: true })]
};