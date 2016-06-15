/**
 * 
 */
package com.meetmenext.server.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.meetmenext.server.objects.FavouritesBean;
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
public class Favourites {
		@Id
		private Long favourite_id;
		@Index
		private Long user_id;
		@Index
		public boolean active;
		@Load
		@Index private Ref<Speaker> speaker;
		/**
		 * 
		 */
		public Favourites() {
			// TODO Auto-generated constructor stub
		}
		
		public Favourites(FavouritesBean fb){
			this.favourite_id = Utils.stringToLong(fb.getId());
			this.speaker = Ref.create(Key.create(Speaker.class,Utils.stringToLong(fb.getSpeaker_id())));
			this.user_id = Utils.stringToLong(fb.getUser_id());
			this.active = fb.isActive();
		}
		
		public FavouritesBean getModelObject(){
			FavouritesBean fb = new FavouritesBean();
			fb.setId(Utils.longToString(this.favourite_id));
			fb.setSpeaker(this.speaker.get());
			fb.setUser_id(Utils.longToString(this.user_id));
			fb.setActive(this.active);
			return fb;
		}
		
		
}
