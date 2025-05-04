package com.myRoomie.Services.eventListner;

import com.myRoomie.Services.IEmailService;
import com.myRoomie.Services.WriteXMLFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RoomsEventListener implements ApplicationListener<EventPojo> {

    @Autowired
    private IEmailService emailService;
    @Autowired
    private WriteXMLFileService writeXMLFileService;

    @Override
    public void onApplicationEvent(EventPojo event) {
        try {
            if (event.getType().equals(EventPojo.EventType.CONTACT_US)) {
                emailService.sendContactUsEmail(event.getContactUsEntity());
            } else if (event.getType().equals(EventPojo.EventType.REQUEST_CALLBACK)) {
                emailService.sendCallBackRequestEmail(event.getCallBackEntity());
            } else if (event.getType().equals(EventPojo.EventType.SCHEDULED_VISIT)) {
                emailService.sendScheduledVisitEmail(event.getScheduledVisitsEntity());
            } else if (event.getType().equals(EventPojo.EventType.RAZOR_ORDER_TRANSACTION)) {
                emailService.bookRoomTransactionEmail(event.getRazorOrderResponse());
            } else if (event.getType().equals(EventPojo.EventType.SITE_MAP_FILE_GENERATE)) {
                writeXMLFileService.generateXmlFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
