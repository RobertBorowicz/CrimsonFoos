var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

const PATH = {
    BUILD_DIR: path.resolve(__dirname, 'resources/dist'),
    SRC_DIR: path.resolve(__dirname, 'src'),
    ENTRY: path.resolve(__dirname, 'src/index.jsx')
};

// this create a index.html in resources.dist directory based on specified template
var HTMLWebpackPluginConfig = new HtmlWebpackPlugin({
    template: __dirname + '/views/index.html',
    filename: 'index.html',
    inject: 'body'
});

const CONFIG = {
    entry: PATH.ENTRY,
    output: {
        path: PATH.BUILD_DIR,
        filename: 'bundle.js'
    },
    module: {
        preLoaders: [
            {
                test: /\.jsx$|\.js$/,
                loader: 'eslint-loader',
                //exclude: /bundle\.js$/,
                include: PATH.SRC_DIR
            }
        ],
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                include: PATH.SRC_DIR
            },
            {
                test: /\.s?css$/,
                loaders: ['style', 'css', 'sass']
            }
        ]
    },
    plugins: [HTMLWebpackPluginConfig]
};

module.exports = CONFIG;
