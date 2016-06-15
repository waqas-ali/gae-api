/**
 * 
 */
package com.meetmenext.server;

import static com.meetmenext.server.utilities.Constants.INVALID_INPUT;
import static com.meetmenext.server.utilities.RequestValidator.validateEventSubscriptionRequest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meetmenext.server.entities.Events;
import com.meetmenext.server.entities.UsersEvents;
import com.meetmenext.server.objects.EventsBean;
import com.meetmenext.server.objects.UserBean;
import com.meetmenext.server.objects.UsersEventsBean;
import com.meetmenext.server.service.EventService;
import com.meetmenext.server.service.SpeakerService;
import com.meetmenext.server.service.UserService;
import com.meetmenext.server.service.UsersEventsService;
import com.meetmenext.server.utilities.Utils;
/**
 * @author Zeeshan A. Syed
 *
 *         Unless required by applicable law or agreed to in writing, software
 *         is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *         OF ANY KIND, either express or implied.
 *
 */
public class ClientRequestController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 1. Handle query on Users

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String response = null;
		boolean isResponseTrue = true;
		// 1. handle New User Signup --req-type=1
		// 2. Handle Profile Pic upload --req-type=2
		String type_of_request = req.getParameter("req-type");
		if (Utils.isNotNullEmpty(type_of_request)) {
			switch (type_of_request) {
			case "signup":
			// Handle New UserSignup
			{
				String email = req.getParameter("u_email");
				String password = req.getParameter("u_password");
				String status = req.getParameter("u_status"); // Check if that
																// is to be
																// passed from
																// Client
				String signuptype = req.getParameter("u_signuptype");
				if (Utils.isNotNullEmpty(email)
						&& Utils.isNotNullEmpty(password)
						&& Utils.isNotNullEmpty(signuptype)) {
					UserBean ub = null;
					if ((ub = UserService.getUserByEmail(email)) != null) {
						isResponseTrue = false;
						response = "Error: Email Already Registered [USERID:"
								+ ub.getId() + "]";
					} else {
						ub = new UserBean();
						ub.setEmail(email);
						ub.setPassword(password);
						ub.setSignuptype(signuptype);
						ub.setTown(req.getParameter("u_town"));
						ub.setState(req.getParameter("u_state"));
						ub.setZipcode(req.getParameter("u_zipcode"));
						ub.setStatus("1");
						String json_response = UserService.handleUserSignUp(ub);
						if (json_response == null) {
							isResponseTrue = false;
							response = "Error: Something went Wrong";
						} else {
							response = json_response;
						}
					}

					// response =
					// json_response==null?"Something went Wrong!Email Already Registered":json_response;
				} else {
					isResponseTrue = false;
					// resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response = "Error: Missing Params";
				}
			}

				break;
			case "getimguploadurl":
			// Handle profile picture upload-PartI Return URL To Upload Photo To
			// Second Part of This Service(Uploading the Image) Will be Handled
			// in Post method of This.Java
			{
				String userid = req.getParameter("u_userid");
				if (Utils.isNotNullEmpty(userid)
						&& UserService.getUserById(userid) != null) {
					response = new UserService().getPictureUploadUrl();
				} else {
					isResponseTrue = false;
					response = "Error: Invalid UserID";
				}
			}
				break;

			case "login":
				// Handle Login Authentication
				String u_email = req.getParameter("u_email");
				String u_password = req.getParameter("u_password");
				if (Utils.isNotNullEmpty(u_email)
						&& Utils.isNotNullEmpty(u_password)) {
					UserBean ub = UserService.getUserByLoginCredentials(
							u_email, u_password);
					if (ub == null) {
						isResponseTrue = false;
						response = "Error: Invalid Credentials";
					} else {
						response = ub.toString();
					}
				} else {
					response = "Error: Invalid Credentials";
				}
				break;

			case "updateprofile": {
				// Handle UserProfile Update
				String userid = req.getParameter("u_userid");
				UserBean ub = null;
				if (Utils.isNotNullEmpty(userid)
						&& (ub = UserService.getUserById(userid)) != null) {

					ub.setTos_status(req.getParameter("u_tosstatus"));
					ub.setTown(req.getParameter("u_town"));
					ub.setState(req.getParameter("u_state"));
					ub.setZipcode(req.getParameter("u_zipcode"));
					// ub.setNumberofalerts(req.getParameter("u_numberofalerts"));
					// ub.setSpeakername(req.getParameter("u_speakername"));
					ub.setBio_data(req.getParameter("u_biodata"));
					response = UserService.updateUserProfile(ub);

				} else {
					isResponseTrue = false;
					// resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
					response = "Error: Invalid UserID";
				}
			}

				break;
			case "search-event": {
				String searchParam = req.getParameter("query_param");
				String userId = req.getParameter("user_id");
				List<EventsBean> allEvents = EventService.getEventList(searchParam);
				List<UsersEventsBean> subEvents = UsersEventsService.getEventsListByUser(userId);
				response = Utils.getEventsWithSubscribedInfo(allEvents, subEvents);
				
				/*
				if (Utils.isNumeric(searchParam)) {
					// this is zip code
					// Search Events for this Zip Code
					response = EventService.getEventByZip(searchParam);
					if (response == null) {
						isResponseTrue = false;
						response = "Error: No Events Found For This Criteria";
					}

				} else {
					response = EventService.getEventsByTownState(searchParam);
					if (response == null) {
						isResponseTrue = false;
						response = "Error: No Events Found For This Criteria";
					}
				}
				*/
				break;
			}

			case "search-speaker": {
				String search_param = req.getParameter("query_param");

				response = SpeakerService.getSpeakerByName(search_param);
				if (response == null) {
					isResponseTrue = false;
					response = "Error: No Speakers Found For This Criteria";
				}
				break;

			}

			case "update-favorite-speaker": {

				String user_id = req.getParameter("query_param_1");
				String speaker_id = req.getParameter("query_param_2");
				String active = req.getParameter("query_param_3");

				if (Utils.isNotNullEmpty(user_id)
						&& Utils.isNotNullEmpty(speaker_id)) {
					if (SpeakerService.setFavouriteSpeaker(user_id, speaker_id,
							active)) {
						isResponseTrue = false;
						response = "SUCCESS: Favourites Updated Succesfully";
					} else {
						isResponseTrue = false;
						response = "Error: Unable to Update the Favourites";
					}
				} else {
					isResponseTrue = false;
					response = "Error: Invalid UserID/SpeakerID";
				}

				break;
			}

			case "get-favorite-speakers": {
				String user_id = req.getParameter("query_param");
				response = SpeakerService.getFavouriteSpeakers(user_id);
				if (response == null) {
					isResponseTrue = false;
					response = "Error: No Favourites Found For This User";
				}
				break;
			}
			case "subscribe-event": {
				String userId = req.getParameter("user_id");
				String eventId = req.getParameter("event_id");
				if ( validateEventSubscriptionRequest(userId, eventId) )
					response = UsersEventsService.subscribeAnEvent(userId,eventId);
				else {
					response = "Error: " + INVALID_INPUT;
				}
				break;
			}
			case "add-event": {
				EventsBean bean = new EventsBean();
				bean.setEvent_date(req.getParameter("event_date"));
				bean.setEvent_name(req.getParameter("event_name"));
				bean.setEvent_speaker_name(req.getParameter("speaker_name"));
				bean.setEvent_state(req.getParameter("event_state"));
				bean.setEvent_town(req.getParameter("event_town"));
				bean.setEvent_zip(req.getParameter("event_zip"));
				response = EventService.addEvent(bean);
				break;
			}
			case "unsubscribe-event": {
				String userId = req.getParameter("user_id");
				String eventId = req.getParameter("event_id");
				response = UsersEventsService.unsubscribeAnEvent(userId, eventId);
				break;
			}
			case "fetch-event-list": {
				String userId = req.getParameter("user_id");
				response = UsersEventsService.getEventsByUser(userId);
				break;
			}
			
			default:
				// resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response = "Error: Invalid Request";
				isResponseTrue = false;
				break;
			}
		} else {
			// resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response = "Error: Invalid Request";
		}

		if (isResponseTrue) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("application/json");
		} else {
			resp.setContentType("text/plain");
		}

		resp.getWriter().print(response);

	}
}
