/**
 * 
 */
package com.meetmenext.server.entities;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.meetmenext.server.objects.EventsBean;
import com.meetmenext.server.utilities.Utils;

/**
 * @author Zeeshan A. Syed
 *
 * Unless required by applicable law or agreed to in writing, software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied.
 *
 */
@Entity
public class Events {
	@Id
	private Long event_id;
	private String event_name;
	@Index
	private String event_date;
	@Index
	private String event_state;
	@Index
	private String event_town;
	@Index
	private String event_zip;
	@Index
	private String event_speaker_name;
	@Index
	private String event_active;
	
//	@Load
//	private List<Ref<User>> user_list;
	
	/**
	 * 
	 */
	public Events() {
		// TODO Auto-generated constructor stub
	}
	
	public Events(EventsBean eb){
		this.event_name = eb.getEvent_name();
		this.event_state = eb.getEvent_state();
		this.event_town = eb.getEvent_town();
		this.event_zip	= eb.getEvent_zip();
		this.event_id = Utils.stringToLong(eb.getEvent_id());
		this.event_speaker_name =eb.getEvent_speaker_name();
		this.event_date = eb.getEvent_date();
		//this.user_list = getUserListFromBean(eb.getUser_list());
	}
	
	public EventsBean getModelObject(){
		EventsBean eb = new EventsBean();
		eb.setEvent_name(this.event_name);
		eb.setEvent_id(Utils.longToString(this.event_id));
		eb.setEvent_state(this.event_state);
		eb.setEvent_town(this.event_town);
		eb.setEvent_zip(this.event_zip);
		eb.setEvent_speaker_name(this.event_speaker_name);
		eb.setEvent_date(this.event_date);
		//eb.setUser_list(getUsersListFromModel());
		return eb;		
	}
	
	/*
	private List<User> getUsersListFromModel() {
		List<User> list = new ArrayList<>();
		for ( Ref<User> event : this.user_list ) {
			list.add(event.get());
		}
		return list;
	}
	private List<Ref<User>> getUserListFromBean(List<User> users) {
		List<Ref<User>> list = new ArrayList<Ref<User>>();
		for ( User user : users) {
			list.add(Ref.create(Key.create(User.class,Utils.stringToLong(user.getModelObject().getId()))));
		}
		return list;
	}
	*/
	
	
	
}
