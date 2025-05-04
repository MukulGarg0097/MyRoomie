package com.myRoomie.Services;

import com.myRoomie.Entities.ContactUsEntity;
import com.myRoomie.Entities.RequestCallBackEntity;
import com.myRoomie.Entities.ScheduledVisitsEntity;
import com.myRoomie.response.PropertyRazorOrderResponse;

import java.util.List;

public interface IEmailService {

	public boolean sendEmail(List<String> toList, List<String> ccList, String subject, String body);

	public boolean sendEmail(String emailId, String subject, String body);
	
	public boolean sendContactUsEmail(ContactUsEntity entity);
	
	public boolean sendCallBackRequestEmail(RequestCallBackEntity entity);
	
	public boolean sendScheduledVisitEmail(ScheduledVisitsEntity entity);

    public boolean bookRoomTransactionEmail(PropertyRazorOrderResponse entity);

}
