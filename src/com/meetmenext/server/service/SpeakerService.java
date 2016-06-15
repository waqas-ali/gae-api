/**
 * 
 */
package com.meetmenext.server.service;

import static com.meetmenext.server.utilities.OFYService.ofy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.json.simple.JSONArray;

import com.googlecode.objectify.Key;
import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.Favourites;
import com.meetmenext.server.entities.Speaker;
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
public class SpeakerService {
		public static String getSpeakerByName(String param){
			String resp = null;
			List<Speaker> speakers = ofy().load().type(Speaker.class).filter("firstName",param).list();
			List<Speaker> speakers2 = ofy().load().type(Speaker.class).filter("lastName",param).list();
			speakers.addAll(speakers2);
			
			if(speakers!=null && speakers.size()>0){
				JSONArray jsonArray  = new JSONArray();
				for(Speaker speaker:speakers){
					jsonArray.add(speaker.getModelObject().toJsonObject());
				}
				resp = jsonArray.toString();
			}
			
			return resp;
		}
		
		public static boolean setFavouriteSpeaker(String param1, String param2,String param3){
			boolean updated = false;
			try{
				Favourites fav;
				//check if this Fav already exists,then update the same
				if((fav = isExistingFavourite(param1,param2))==null)
				{
					//Trying to delete a favourite ,that doesnt exist
					if("0".equals(param3.trim()))
					{
						System.out.println("Cannot Unfav The NonExisting Favorite ["+param1+":"+param2+"]");
						return updated;
					}
					FavouritesBean fb = new FavouritesBean();
					fb.setId(String.valueOf(RandomUtils.nextLong()));
					fb.setUser_id(param1.trim());
					fb.setSpeaker_id(param2.trim());
					fb.setActive("1".equals((param3.trim())));
					fav = new Favourites(fb);
				}
				else{
					//If this fav already exists,modify the Active Status as per choice
					fav.active = "1".equals((param3.trim()));
				}
				ofy().save().entity(fav).now();
				updated = true;
			}
			catch(Exception e){
				System.out.println("Something went wrong while saving Favourites ["+param1+":"+param2+"]");
				
			}
			return updated;
			
		}
		
		private static Favourites isExistingFavourite(String user_id,String speaker_id){
			return ofy().load().type(Favourites.class).filter("user_id",Utils.stringToLong(user_id.trim())).filter("speaker",Key.create(Speaker.class,Utils.stringToLong(speaker_id.trim()))).first().now();
		}
		
		public static String getFavouriteSpeakers(String userId){
			String resp = null;
			List<Favourites> favourites_list = ofy().load().type(Favourites.class).filter("user_id",Utils.stringToLong(userId.trim())).filter("active",true).list();
			if(favourites_list!=null && favourites_list.size()>0){
				JSONArray jsonArray  = new JSONArray();
				for(Favourites fav : favourites_list){
					FavouritesBean fb = fav.getModelObject();
					jsonArray.add(fb.getSpeaker().getModelObject().toJsonObject());
				}
				resp = jsonArray.toString();
			}
			return resp;
		}
}
