/*
* PhoneGap is available under *either* the terms of the modified BSD license *or* the
* MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
*
* Copyright (c) 2006-2011 Worklight, Ltd.
* Upgraded by Doers' Guild.
*/

/**
 * Constructor
 */
var Analytics = function() {
};

/**
 * Initialize Google Analytics configuration
 *
 * @param successCallback   The success callback
 * @param failureCallback   The error callback
 */
Analytics.prototype.start = function(successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'start', []);
};

/**
 * Stop Google Analytics
 *
 * @param successCallback   The success callback
 * @param failureCallback   The error callback
 */
Analytics.prototype.stop = function(successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'stop', []);
};

/**
 * Track a page view on Google Analytics
 * @param key				The name of the tracked item (can be a url or some logical name).
 * 							The key name will be presented in Google Analytics report.
 * @param successCallback	The success callback
 * @param failureCallback	The error callback
 */
Analytics.prototype.trackPageView = function(key, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'trackPageView', [key]);
};

/**
 * Track an event on Google Analytics
 * @param category			The name that you supply as a way to group objects that you want to track
 * @param action			The name the type of event or interaction you want to track for a particular web object
 * @param label				Provides additional information for events that you want to track (optional)
 * @param value				Assign a numerical value to a tracked page object (optional)

 * @param successCallback	The success callback
 * @param failureCallback	The error callback
 */

Analytics.prototype.trackEvent = function(category, action, label, value, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'trackEvent', [category, action, typeof label === "undefined" ? "" : label, (isNaN(parseInt(value, 10))) ? 0 : parseInt(value, 10)]);
};

/**
 * @param category The category of timing
 * @param timing The timing (loadTime?) value to track (in ms)
 * @param name The name of the timing value
 * @param label Provides additional information for timing events that you want to track (optional)

 * @param successCallback The success callback
 * @param failureCallback The error callback
 */

Analytics.prototype.trackTiming = function(category, timing, name, label, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'trackTiming', [category, timing, name, typeof label === "undefined" ? "" : label]);
};

/**
 * @param index The index of the dimension (set on Google Analytics)
 * @param value The value to set
 *
 * @param successCallback The success callback
 * @param failureCallback The error callback
 */

Analytics.prototype.setCustomDimension = function(index, value, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'setCustomDimension', [parseInt(index, 10) || 0, typeof value === "undefined" ? "" : value]);
};

/**
 * Track social interaction on Google Analytics
 * @param network	Represents the social network with which the user is interacting (e.g. Google+, Facebook, Twitter, etc.).
 * @param action	Represents the social action taken (e.g. Like, Share, +1, etc.).
 * @param target	Represents the content on which the social action is being taken (i.e. a specific article or video). (optional)

 * @param successCallback	The success callback
 * @param failureCallback	The error callback
 */

Analytics.prototype.trackSocial = function(network, action, target, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'trackSocial', [network, action, typeof target === "undefined" ? "" : target]);
};

/**
 * Track e-commerce transaction
 * @param transactionId	   Transaction Id, should be unique.
 * @param orderTotal       Total order 
 * @param affiliation	   Affiliation. (e.g. In-App Store)
 * @param currencyCode     Currency Code (e.g. USD,KRW)
 * @param SKU              Product SKU (ID)
 * @param productName      Product name
 * @param productPrice     Product price
 * @param productQuantity  Product quantity
 * @param productCategory  Product category

 * @param successCallback	The success callback
 * @param failureCallback	The error callback
 */

Analytics.prototype.trackCommerce = function(transactionId,orderTotal,affiliation,currencyCode,SKU,productName,productPrice,productQuantity,productCategory, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'GoogleAnalyticsTracker', 'trackCommerce', 
    		[transactionId,orderTotal,affiliation,currencyCode,SKU,productName,productPrice,productQuantity,productCategory]);
};

/**
 * Load Analytics
 */

if (!window.plugins) {
    window.plugins = {};
}

if (!window.plugins.analytics) {
    window.plugins.analytics = new Analytics();
}
