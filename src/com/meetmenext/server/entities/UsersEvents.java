package com.meetmenext.server.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.meetmenext.server.objects.UsersEventsBean;
import com.meetmenext.server.utilities.Utils;

/**
 *
 * @author waqas.ali
 *
 */
@Entity
public class UsersEvents {
	
	@Id
	private Long id;
	private long creation_time;
	private long updation_time;
	@Index
	private String isDeleted;

	@Load
	@Index
	private Ref<User> user;
	@Load
	@Index
	private Ref<Events> event;
	
	public long getCreation_time() {
		return creation_time;
	}
	public void setCreation_time(long creation_time) {
		this.creation_time = creation_time;
	}
	public long getUpdation_time() {
		return updation_time;
	}
	public void setUpdation_time(long updation_time) {
		this.updation_time = updation_time;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public UsersEvents() {
		
	}
	public UsersEvents(UsersEventsBean ueb){
		this.id = Utils.stringToLong(ueb.getId());
		this.creation_time = Utils.stringToLong(ueb.getCreationDate());
		this.updation_time = Utils.stringToLong(ueb.getUpdationDate());
		this.isDeleted = ueb.getIsDeleted();
		this.user = Ref.create(Key.create(User.class,Utils.stringToLong(ueb.getUserId())));
		this.event = Ref.create(Key.create(Events.class,Utils.stringToLong(ueb.getEventId())));

	}
	
	public UsersEventsBean getModelObject(){
		UsersEventsBean ueb = new UsersEventsBean();
		ueb.setCreationDate(Utils.longToString(this.creation_time));
		ueb.setId(Utils.longToString(this.id));
		ueb.setIsDeleted(this.isDeleted);
		ueb.setEvent(this.event.get().getModelObject());
		ueb.setEventId(ueb.getEvent().getEvent_id());
		ueb.setUpdationDate(Utils.longToString(this.updation_time));
		ueb.setUser(this.user.get().getModelObject());
		ueb.setUserId(ueb.getUser().getId());
		return ueb;		
	}
	
	
	
}
