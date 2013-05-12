/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) 2006-2011 Worklight, Ltd. 
 * Upgraded by Doers' Guild  
 */

package com.phonegap.plugins.analytics;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.google.analytics.tracking.android.Transaction;
import com.google.analytics.tracking.android.Transaction.Item;

public class GoogleAnalyticsTracker extends CordovaPlugin {
	public static final String START = "start";
	public static final String STOP = "stop";
	public static final String TRACK_PAGE_VIEW = "trackPageView";
	public static final String TRACK_EVENT = "trackEvent";
	public static final String SET_CUSTOM_DIMENSION = "setCustomDimension";
	public static final String TRACK_TIMING = "trackTiming";
	public static final String TRACK_SOCIAL = "trackSocial";
	public static final String TRACK_COMMERCE = "trackCommerce";

	private Tracker tracker;
	private com.google.analytics.tracking.android.EasyTracker instance;

	public GoogleAnalyticsTracker() {
		instance = com.google.analytics.tracking.android.EasyTracker
				.getInstance();
	}

	@Override
	public boolean execute(String action, JSONArray data, CallbackContext callbackContext) {
		boolean result = false;
		if (START.equals(action)) {
			start();
			callbackContext.success();
			result = true;
		} else if (TRACK_PAGE_VIEW.equals(action)) {
			try {
				trackPageView(data.getString(0));
				callbackContext.success();
				result = true;
			} catch (JSONException e) {
				callbackContext.error("JSON Exception");
				result = false;
			}
		} else if (TRACK_EVENT.equals(action)) {
			try {
				trackEvent(data.getString(0), data.getString(1),
						data.getString(2), data.getLong(3));
				callbackContext.success();
				result = true;
			} catch (JSONException e) {
				callbackContext.error("JSON Exception");
				result = false;
			}
		} else if (STOP.equals(action)) {
			stop();
			callbackContext.success();
			result = true;
		} else if (SET_CUSTOM_DIMENSION.equals(action)) {
			try {
				setCustomDimension(data.getInt(0), data.getString(1));
				callbackContext.success();
				result = true;
			} catch (JSONException e) {
				callbackContext.error("JSON Exception");
				result = false;
			}
		} else if (TRACK_TIMING.equals(action)) {
			try {
				trackTiming(data.getString(0), data.getLong(1),
						data.getString(2), data.getString(3));
				callbackContext.success();
				result = true;
			} catch (JSONException e) {
				callbackContext.error("JSON Exception");
				result = false;
			}
		} else if (TRACK_SOCIAL.equals(action)) {
			try {
				trackSocial(data.getString(0), data.getString(1),
						data.getString(2));
				callbackContext.success();
				result = true;
			} catch (JSONException e) {
				callbackContext.error("JSON Exception");
				result = false;
			}
		} else if (TRACK_COMMERCE.equals(action)) {
			try {
				trackCommerce(data.getString(0), data.getLong(1), data.getString(2), data.getString(3),
						data.getString(4), data.getString(5), data.getLong(6), data.getLong(7), data.getString(8));
				callbackContext.success();
				result = true;
			} catch (JSONException e) {
				callbackContext.error("JSON Exception");
				result = false;
			}
		}else {
			callbackContext.error("Invalid Action");
			result = false;
		}
		return result;
	}

	private void start() {
		instance.activityStart(this.cordova.getActivity());
		tracker = EasyTracker.getTracker();
	}

	private void stop() {
		instance.activityStop(this.cordova.getActivity());
	}

	private void trackPageView(String key) {
		tracker.sendView(key);
	}

	private void trackEvent(String category, String action, String label,
			long value) {
		tracker.sendEvent(category, action, label, value);
	}

	private void trackTiming(String category, long loadTime, String name,
			String label) {
		tracker.sendTiming(category, loadTime, name, label);
	}

	private void setCustomDimension(int Index, String dimensionValue) {
		tracker.setCustomDimension(Index, dimensionValue);
	}
	
	private void trackSocial(String network, String action, String target ) {
		tracker.sendSocial(network, action, target);
	}
	
	private void trackCommerce(String transactionId,long orderTotal,String affiliation,String currencyCode,
			String SKU, String productName, long productPrice, long productQuantity, String productCategory) {
		Transaction myTrans = new Transaction.Builder(
		      transactionId,                                        // (String) Transaction Id, should be unique.
		      (long) (orderTotal * 1000000))                        // (long) Order total (in micros)   - *1000000
		      .setAffiliation(affiliation)                          // (String) Affiliation  - In-App Store
		      .setCurrencyCode(currencyCode)                        // (String) Currency  - USD,KRW
		      .build();
	
		  myTrans.addItem(new Item.Builder(
		      SKU,                                                  // (String) Product SKU - product ID
		      productName,                                          // (String) Product name - "30 Coin Pack"
		      (long) (productPrice * 1000000),                      // (long) Product price (in micros)
		      (long) productQuantity)                               // (long) Product quantity
		      .setProductCategory(productCategory)                  // (String) Product category
		      .build());
		tracker.sendTransaction(myTrans);
	}

}