# Analytics plugin for Phonegap #

The analytics client allows you to send page views, events, timings to Google Analytics.  It also allows you to set custom dimensions that have been set up on this account (see below).

## Adding the Plugin to your project ##

Using this plugin requires [PhoneGap Cordova library for Android](http://phonegap.com/download) version 1.9 or above.

1) To install the plugin, move `www/analytics.js` to your project's www folder and include a reference to it in your html file after cordova.js.

<pre>
<code>
    &lt;script type="text/javascript" charset="utf-8" src="cordova.js"&gt;&lt;/script&gt;<br/>
    &lt;script type="text/javascript" charset="utf-8" src="analytics.js"&gt;&lt;/script&gt;
</code>
</pre>

2) Create a directory within your project called `src/com/phonegap/plugins/analytics` and copy `src/com/phonegap/plugins/analytics/GoogleAnalyticsTracker.java` into it.

3) Add the following activity to your AndroidManifest.xml file, inside the &lt;application&gt; tag if it isn't already there.

<pre>
<code>
    &lt;activity android:name="com.phonegap.DroidGap" android:label="@string/app_name"&gt;<br/>
      &lt;intent-filter&gt;<br/>
      &lt;/intent-filter&gt;<br/>
    &lt;/activity&gt;
</code>
</pre>

4) Download [GoogleAnalytics](https://developers.google.com/analytics/devguides/collection/android/resources) library (tested with v2.0 Beta 4; Use the version from this repo if needed) and copy `lib/libGoogleAnalyticsV2.jar` into the libs directory within your project.  You may also need to right click on this file in eclipse and add the jar to the build path.

5) In your res/xml/config.xml file add the following line:

```<plugin name="GoogleAnalyticsTracker" value="com.phonegap.plugins.analytics.GoogleAnalyticsTracker" />```

6) Copy the `res/values/analytics.xml` file to the corresponding location in your project and set your App's Tracking ID in there. [Documentation](https://developers.google.com/analytics/devguides/collection/android/v2/parameters)

## Using the plugin ##

The plugin creates the object `window.plugins.analytics`.  To use, call one of the following, available methods:

####start(successCallback, failureCallback)####
Sample use:
<pre>
<code>
	window.plugins.analytics.start(
			function(){
				console.log("Start: success");
			},
		    function(){
		    	console.log("Start: failure");
		    }
	);
</code>  
</pre>  
####stop(successCallback, failureCallback);####
Sample use:
<pre>
<code>
	window.plugins.analytics.stop(
			function(){
				console.log("Stop: success");
			},
	     	function(){
	     		console.log("Stop: failure");
	     	}
	);
</code>
</pre>
####trackPageView(key, successCallback, failureCallback);####
<pre>
/**
 * @param key				The name of the tracked item (can be a url or some logical name).
 * 							The key name will be presented in Google Analytics report.  
 * @param successCallback	The success callback
 * @param failureCallback	The error callback 
 */
</pre>

Sample use:
<pre>
<code>
    window.plugins.analytics.trackPageView(
    			"page1.html",
    			 function(){
    			 	console.log("Track: success");
    			 },
    			 function(){
    			 	console.log("Track: failure");
    			 }
    );
</code>
</pre>
	
####trackEvent(category, action, label, value, successCallback, failureCallback)####
<pre>
/**
 * @param category			The name that you supply as a way to group objects that you want to track
 * @param action			The name the type of event or interaction you want to track for a particular web object
 * @param label				Provides additional information for events that you want to track (optional)
 * @param value				Assign a numerical value to a tracked page object (optional)

 * @param successCallback	The success callback
 * @param failureCallback	The error callback 
 */
</pre>

Sample use:
<pre>
<code>
	window.plugins.analytics.trackEvent(
				"category",
			    "action",
			    "event",
			     1,
			    function(){
			    	console.log("Track: success");
			    }, 
			    function(){
			    	console.log("Track: failure");
			    }
	);
</code>
</pre>
####trackTiming(category, timing, name, label, successCallback, failureCallback)####
Used to track timings within your app, such as load times, server calls, page changes etc.

This is new to SDK v2 and only works with beta 4 and above.  Reporting is still sketchy.
https://developers.google.com/analytics/devguides/collection/android/v2/usertimings?hl=en
<pre>
/**
* @param category The category of timing
* @param timing The timing value to track (in ms)
* @param name The name of the timing value
* @param label Provides additional information for timing events that you want to track (optional)

* @param successCallback The success callback
* @param failureCallback The error callback
*/
</pre>
Sample use:
<pre>
<code>
	window.plugins.analytics.trackTiming(
					"load",
					1248,
					"page1",
					"label",
					function(){
				    	console.log("Track: success");
				    }, 
				    function(){
				    	console.log("Track: failure");
				    }
	);
</code>
</pre>
####trackSocial(network, action, target, successCallback, failureCallback)####
Used to track social interaction within your app, such as social network, actions, and target URLs.

<pre>
/**
* @param network	Represents the social network with which the user is interacting (e.g. Google+, Facebook, Twitter, etc.).
* @param action	Represents the social action taken (e.g. Like, Share, +1, etc.).
* @param target	Represents the content on which the social action is being taken (i.e. a specific article or video). (optional)

* @param successCallback The success callback
* @param failureCallback The error callback
*/
</pre>
Sample use:
<pre>
<code>
	window.plugins.analytics.trackSocial(
					"twitter",
					"Tweet",
					"https://developers.google.com/analytics",
					function(){
				    	console.log("Track: success");
				    }, 
				    function(){
				    	console.log("Track: failure");
				    }
	);
</code>
</pre>
####trackCommerce(transactionId,orderTotal,affiliation,currencyCode,SKU,productName,productPrice,productQuantity,productCategory, successCallback, failureCallback)####
Used to track commerce transaction within your app.

<pre>
/**
* @param transactionId	  Transaction Id, should be unique.
* @param orderTotal       Total order 
* @param affiliation	  Affiliation. (e.g. In-App Store)
* @param currencyCode     Currency Code (e.g. USD,KRW)
* @param SKU              Product SKU (ID)
* @param productName      Product name
* @param productPrice     Product price
* @param productQuantity  Product quantity
* @param productCategory  Product category

* @param successCallback The success callback
* @param failureCallback The error callback
*/
</pre>
Sample use:
<pre>
<code>
	window.plugins.analytics.trackCommerce(
					"0_123456",
					2.99,
					"In-App Store",
					"USD"
					"L_789",
					"30 Coin Pack",
					2.99,
					1,
					"Coin",
					function(){
				    	console.log("Track: success");
				    }, 
				    function(){
				    	console.log("Track: failure");
				    }
	);
</code>
</pre>
####setCustomDimension(index,value, successCallback, failureCallback)####
Set a custom dimension.  This replaces custom variables from v1.
see https://developers.google.com/analytics/devguides/platform/features/customdimsmets

Note:  The dimension must be set up through the analytics web console to show up.
see http://support.google.com/analytics/bin/answer.py?hl=en&answer=2709829
<pre>
/**
* @param index The index of the dimension (set on Google Analytics)
* @param value The value to set
* 
* @param successCallback The success callback
* @param failureCallback The error callback
 */
</pre>
<pre>
<code>
	window.plugins.analytics.setCustomDimension(
					1,
					"wifi",
					function(){
				    	console.log("Track: success");
				    }, 
				    function(){
				    	console.log("Track: failure");
				    }
	);
</code>
</pre>
};
Please keep in mind that these methods, as in any other plugin, are ready to be invoked only after '[deviceready](http://docs.phonegap.com/phonegap_events_events.md.html#deviceready)' event has been fired

---

One good practice would be to manually stop the session, when the app closes. Add this code to your main activity:
<pre>    
@Override
public void onDestroy() 
{
    super.onDestroy();
    com.google.analytics.tracking.android.EasyTracker.getInstance().activityStop(this);
}
</pre>

## RELEASE NOTES ##

### FEB, 12, 2013 ###

* Support for GA SDK v2 beta 4
* Merged from [JesseHensold / Cordova-Android-Analytics](https://github.com/JesseHensold/Cordova-Android-Analytics) to add `trackTiming` and `setCustomDimension`
* Add JS API for `trackTiming` and `setCustomDimension`

### NOV, 14, 2012 ###

* Upgraded to support Version 2 of the Google Analytics SDK
* Added the `stop` method

### AUG, 14, 2012 ###

* Added suppport for Cordova 1.9 and above

### AUG, 10, 2011 ###

* Added event tracking

### Jul 24, 2011 ###

* Initial release

## LICENSE ##

PhoneGap is available under *either* the terms of the modified BSD license *or* the
MIT License (2008). As a recipient of PhonegGap, you may choose which
license to receive this code under (except as noted in per-module LICENSE
files). Some modules may not be the copyright of Nitobi.   These
modules contain explicit declarations of copyright in both the LICENSE files in
the directories in which they reside and in the code itself. No external
contributions are allowed under licenses which are fundamentally incompatible
with the MIT or BSD licenses that PhoneGap is distributed under.

 ---
 
### libGoogleAnalyticsV2.jar ###
 
 `libGoogleAnalyticsV2.jar` is distributed under Apache License, Version 2.0.
 + License URL: http://www.apache.org/licenses/LICENSE-2.0
 + libGoogleAnalyticsV2.jar URL: https://dl.google.com/gaformobileapps/GoogleAnalyticsAndroid.zip


---------------------------------------
Like our [work](http://doersguild.com)? [Get in touch!](mailto:mail@doersguild.com)
