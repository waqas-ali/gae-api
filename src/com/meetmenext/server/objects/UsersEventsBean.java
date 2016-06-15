/**
 * 
 */
package com.meetmenext.server.objects;

import org.json.simple.JSONObject;

import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.User;

/**
*
* @author waqas.ali
*
*/
public class UsersEventsBean {
	public String id;
	public String isDeleted;
	private String userId;
	private String eventId;
	private String creationDate;
	private String updationDate;
	private UserBean user;
	private EventsBean event;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId == null ? this.user.getId() : userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventId() {
		return eventId == null ? this.event.event_id : eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(String updationDate) {
		this.updationDate = updationDate;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public EventsBean getEvent() {
		return event;
	}

	public void setEvent(EventsBean event) {
		this.event = event;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 
	 */
	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("creation_date", this.creationDate);
		obj.put("updation_date", this.updationDate);
		obj.put("deleted", this.isDeleted);
		obj.put("user_id", this.user.getId());
		obj.put("event_id", this.event.getEvent_id());

		return obj;

	}

}
