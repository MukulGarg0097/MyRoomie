package com.myRoomie.Services.eventListner;

import com.myRoomie.Entities.ContactUsEntity;
import com.myRoomie.Entities.RequestCallBackEntity;
import com.myRoomie.Entities.ScheduledVisitsEntity;
import com.myRoomie.response.PropertyRazorOrderResponse;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class EventPojo extends ApplicationEvent {

    private ContactUsEntity contactUsEntity;
    private RequestCallBackEntity callBackEntity;
    private ScheduledVisitsEntity scheduledVisitsEntity;
    private PropertyRazorOrderResponse razorOrderResponse;
    private EventType type;

    public EventPojo(Object source) {
        super(source);
    }

    public enum EventType {
        CONTACT_US,
        REQUEST_CALLBACK,
        SCHEDULED_VISIT,
        RAZOR_ORDER_TRANSACTION,
        SITE_MAP_FILE_GENERATE
    }
}
