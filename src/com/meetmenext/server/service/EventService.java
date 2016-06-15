/**
 * 
 */
package com.meetmenext.server.service;

import static com.meetmenext.server.utilities.Constants.FAILED_STATUS;
import static com.meetmenext.server.utilities.Constants.SUCCESS_STATUS;
import static com.meetmenext.server.utilities.OFYService.ofy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.User;
import com.meetmenext.server.entities.UsersEvents;
import com.meetmenext.server.objects.EventsBean;
import com.meetmenext.server.objects.UsersEventsBean;
import com.meetmenext.server.utilities.Utils;

/**
 * @author Zeeshan A. Syed
 *
 *         Unless required by applicable law or agreed to in writing, software
 *         is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *         OF ANY KIND, either express or implied.
 *
 */
public class EventService {
	public static String getEventByZip(String zip) {
		String resp = null;
		List<Events> events = ofy().load().type(Events.class)
				.filter("event_zip", zip.trim()).list();

		if (events != null && events.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			for (Events ev : events) {
				jsonArray.add(ev.getModelObject().toJsonObject());
			}
			resp = jsonArray.toString();
		}

		return resp;

	}

	public static String addEvent(EventsBean bean) {
		JSONObject jsonobj = new JSONObject();
		try {
			bean.setEvent_id(String.valueOf(RandomUtils.nextLong()));
			ofy().save().entity(new Events(bean)).now();
			jsonobj.put("status", SUCCESS_STATUS);
			jsonobj.put("event_id", bean.getEvent_id());
		} catch (Exception e) {
			jsonobj.put("status", FAILED_STATUS);
		}
		return jsonobj.toString();

	}

	public static String getEventsByTownState(String param) {
		String resp = null;
		List<Events> events1 = ofy().load().type(Events.class)
				.filter("event_town", param.trim()).list();
		List<Events> events2 = ofy().load().type(Events.class)
				.filter("event_state", param.trim()).list();
		events1.addAll(events2);
		if (events1 != null && events1.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			for (Events ev : events1) {
				jsonArray.add(ev.getModelObject().toJsonObject());
			}
			resp = jsonArray.toString();
		}
		return resp;
	}

	public static EventsBean getEventById(String eventId) {
		Events event = ofy().load().type(Events.class)
				.id(Utils.stringToLong(eventId)).now();
		return event.getModelObject();
	}

	public static List<EventsBean> getEventList(String param) {
		List<EventsBean> beanList = new ArrayList<>();
		try {
			List<Events> events = new ArrayList<>();
			List<Events> zipEvents = ofy().load().type(Events.class)
					.filter("event_zip", param.trim()).list();
			List<Events> townEvents = ofy().load().type(Events.class)
					.filter("event_town", param.trim()).list();
			List<Events> stateEvents = ofy().load().type(Events.class)
					.filter("event_state", param.trim()).list();
			events.addAll(zipEvents);
			events.addAll(townEvents);
			events.addAll(stateEvents);
			for ( Events event : events) {
				beanList.add(event.getModelObject());
			}
		} catch (Exception e) {

		}
		return beanList;
	}
}
