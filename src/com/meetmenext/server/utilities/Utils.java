/**
 * 
 */
package com.meetmenext.server.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;

import com.meetmenext.server.objects.EventsBean;
import com.meetmenext.server.objects.UsersEventsBean;

/**
 * @author Zeeshan A. Syed
 *
 *         Unless required by applicable law or agreed to in writing, software
 *         is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *         OF ANY KIND, either express or implied.
 *
 */
public class Utils {
	public static boolean isNullEmpty(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNotNullEmpty(String str) {
		return StringUtils.isNotBlank(str);
	}

	public static boolean isNullEmpty(Long str) {
		if (str == null || str.toString().isEmpty())
			return true;
		return false;
	}

	public static boolean isNotNullEmpty(Long str) {
		if (str != null && !str.toString().isEmpty())
			return true;
		return false;
	}

	public static Long millis() {
		return System.currentTimeMillis();
	}

	public static Long stringToLong(String val) {
		try {
			if (isNotNullEmpty(val))
				return Long.valueOf(val);
		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	public static String longToString(Long val) {
		try {
			if (isNotNullEmpty(val))
				return String.valueOf(val);
		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static String getEventsWithSubscribedInfo(List<EventsBean> events, List<UsersEventsBean> subEvents) {
		JSONArray jsonArray = new JSONArray();
		for ( EventsBean event : events) {
			for ( UsersEventsBean sub : subEvents) {
				if ( event.getEvent_id().equals(sub.getEventId())) {
					event.setIsSubscribed("Y");
					break;
				}else {
					event.setIsSubscribed("N");
				}
			}
			jsonArray.add(event.toJsonObject());
		}
		
		return jsonArray.toString();
	}
}
