/**
 * 
 */
package com.meetmenext.server.service;

import static com.meetmenext.server.utilities.Constants.FAILED_STATUS;
import static com.meetmenext.server.utilities.Constants.SUCCESS_STATUS;
import static com.meetmenext.server.utilities.OFYService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cmd.Query;
import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.User;
import com.meetmenext.server.entities.UsersEvents;
import com.meetmenext.server.objects.UsersEventsBean;
import com.meetmenext.server.utilities.Utils;

/**
 *
 * @author waqas.ali
 *
 */
public class UsersEventsService {

	public static String subscribeAnEvent(String userId, String eventId) {
		String response = "";
		try {
			UsersEventsBean bean = new UsersEventsBean();
			bean.setId(String.valueOf(RandomUtils.nextLong()));
			bean.setCreationDate(Utils.longToString(Utils.millis()));
			bean.setUpdationDate(Utils.longToString(Utils.millis()));
			bean.setUserId(userId);
			bean.setEventId(eventId);
			bean.setIsDeleted("N");
			ofy().save().entity(new UsersEvents(bean)).now();
			response =  "SUCCESS: Subscription Successfully";
		} catch (Exception e) {
			response =  "Error: Something went wrong while doing subscription";
		}
		return response;
	}

	public static String getEventsByUser(String userId) {
		String resp = null;
		JSONArray jsonArray = new JSONArray();
		Key<User> key1 = Key.create(User.class,Utils.stringToLong(userId.trim()));
		Ref<User> key = Ref.create(key1);
		Query<UsersEvents> query = ofy().load().type(UsersEvents.class);
		
		List<UsersEvents> eventList = query.filter("user",key).filter("isDeleted", "N").list();
		
		if (!eventList.isEmpty()) {
			for (UsersEvents ev : eventList) {
				jsonArray.add(ev.getModelObject().toJsonObject());
			}			
		}
		resp = jsonArray.toString();
		return resp;
	}
	public static List<UsersEventsBean> getEventsListByUser(String userId) {
		
		Key<User> key1 = Key.create(User.class,Utils.stringToLong(userId.trim()));
		Ref<User> key = Ref.create(key1);
		Query<UsersEvents> query = ofy().load().type(UsersEvents.class);
		
		List<UsersEvents> eventList = query.filter("user",key).filter("isDeleted", "N").list();
		List<UsersEventsBean> beanList = new ArrayList<UsersEventsBean>();
		for ( UsersEvents ue : eventList) {
			beanList.add(ue.getModelObject());
		}
		return beanList;		

	}

	public static String unsubscribeAnEvent(String userId, String eventId) {
		String resp = FAILED_STATUS;
		try {
			
			Ref<User> userKey = Ref.create(Key.create(User.class,Utils.stringToLong(userId.trim())));
			Ref<Events> eventKey = Ref.create(Key.create(Events.class,Utils.stringToLong(eventId.trim())));
			
			Query<UsersEvents> query = ofy().load().type(UsersEvents.class);
			UsersEvents event = query.filter("user",userKey).filter("event",eventKey).first().now();

			event.setIsDeleted("Y");
			ofy().save().entity(event).now();
			resp = "SUCCESS: Unsubscription peformed successfully";
		} catch (Exception e) {
			resp = "Error: Unable to perform unsubscription";
		}
		return resp;
	}
	public static boolean isAlreadySubscribed(String userId, String eventId) {
		boolean resp = true;
		try {
			
			Ref<User> userKey = Ref.create(Key.create(User.class,Utils.stringToLong(userId.trim())));
			Ref<Events> eventKey = Ref.create(Key.create(Events.class,Utils.stringToLong(eventId.trim())));
			
			Query<UsersEvents> query = ofy().load().type(UsersEvents.class);
			UsersEvents event = query.filter("user",userKey).filter("event",eventKey).filter("isDeleted", "N").first().now();

			if (event == null) {
				resp = false;
			}
			
		} catch (Exception e) {
			resp = true;
		}
		return resp;
	}
}
