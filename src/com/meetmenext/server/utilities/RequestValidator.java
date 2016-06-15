package com.meetmenext.server.utilities;

import com.meetmenext.server.objects.EventsBean;
import com.meetmenext.server.objects.UserBean;
import com.meetmenext.server.service.EventService;
import com.meetmenext.server.service.UserService;
import com.meetmenext.server.service.UsersEventsService;

public class RequestValidator {

	public static boolean validateEventSubscriptionRequest(String userId, String eventId) {
		boolean isValid = false;
		UserBean ub = UserService.getUserById(userId);
		EventsBean eb = EventService.getEventById(eventId);
		boolean isSubs = UsersEventsService.isAlreadySubscribed(userId, eventId);
		if ( ub != null && eb != null && "1".equals(ub.getStatus()) && !isSubs)
			isValid = true;
		return isValid;
	}
}
