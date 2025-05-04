package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.ContactUsEntity;
import com.myRoomie.Entities.RequestCallBackEntity;
import com.myRoomie.Entities.ScheduledVisitsEntity;
import com.myRoomie.Services.IEmailService;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.Utilities.EmailUtil;
import com.myRoomie.Utilities.FileUtil;
import com.myRoomie.response.PropertyRazorOrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmailServiceImpl implements IEmailService {

    @Value("${email.from}")
    public String from;

    @Value("${email.host}")
    public String host;

    @Value("${email.port}")
    public String port;

    @Value("${email.username}")
    public String username;

    @Value("${email.password}")
    public String password;

    @Value("${email.sentTo}")
    public String sentTo;

    private String contactUs;

    private String callbackRequest;

    private String scheduledVisit;
    private String bookRoom;

    @PostConstruct
    public void init() throws Exception {
        contactUs = FileUtil.loadResourceFileData("templates/contact-us.html", getClass());
        callbackRequest = FileUtil.loadResourceFileData("templates/request-call-back.html", getClass());
        scheduledVisit = FileUtil.loadResourceFileData("templates/scheduled-visit.html", getClass());
        bookRoom = FileUtil.loadResourceFileData("templates/book_room_transaction.html", getClass());
    }

    @Override
    public boolean sendEmail(List<String> toList, List<String> ccList, String subject, String body) {
        ArrayList<String> bccList = new ArrayList<>();
        String[] toAddress = toList.toArray(new String[toList.size()]);
        String[] ccAddress = ccList.toArray(new String[ccList.size()]);
        String[] bccAddress = bccList.toArray(new String[bccList.size()]);
        boolean success = EmailUtil.sendEmails(toAddress, bccAddress, ccAddress, from, host, port, username, password,
                subject, body, null, true);
        return success;
    }

    @Override
    public boolean sendEmail(String emailId, String subject, String body) {
        boolean success = EmailUtil.sendEmail(emailId, from, host, port, username, password, subject, body, null, true);
        return success;
    }

    @Override
    public boolean sendContactUsEmail(ContactUsEntity entity) {
        String content = contactUs;
        content = content.replaceAll("\\*name\\*", entity.getName() != null ? entity.getName() : "");
        content = content.replaceAll("\\*phone\\*", entity.getPhone() != null ? entity.getPhone() : "");
        content = content.replaceAll("\\*email\\*", entity.getEmail() != null ? entity.getEmail() : "");
        content = content.replaceAll("\\*lookingFor\\*", entity.getLooking() != null ? entity.getLooking() : "");
        content = content.replaceAll("\\*message\\*", entity.getMessage() != null ? entity.getMessage() : "");
        String contentBody = content;
        boolean success = EmailUtil.sendEmail(sentTo, from, host, port, username, password, "Contact Us - Myroomie.in",
                contentBody, null, true);
        return success;
    }

    @Override
    public boolean sendCallBackRequestEmail(RequestCallBackEntity entity) {
        String content = callbackRequest;
        content = content.replaceAll("\\*name\\*", entity.getName() != null ? entity.getName() : "");
        content = content.replaceAll("\\*mobile\\*", entity.getMobileNo() != null ? entity.getMobileNo() : "");
        String contentBody = content;
        boolean success = EmailUtil.sendEmail(sentTo, from, host, port, username, password,
                "Callback Request- Myroomie.in", contentBody, null, true);
        return success;
    }

    @Override
    public boolean sendScheduledVisitEmail(ScheduledVisitsEntity entity) {
        String content = scheduledVisit;
        content = content.replaceAll("\\*name\\*", entity.getName() != null ? entity.getName() : "");
        content = content.replaceAll("\\*contact\\*", entity.getContactNo() != null ? entity.getContactNo() : "");
        content = content.replaceAll("\\*email\\*", entity.getEmail() != null ? entity.getEmail() : "");
        content = content.replaceAll("\\*vistingDate\\*", getDate(entity.getVisitingDate()));
        String contentBody = content;
        boolean success = EmailUtil.sendEmail(sentTo, from, host, port, username, password,
                "Scheduled Visit- Myroomie.in", contentBody, null, true);
        return success;
    }

    @Override
    public boolean bookRoomTransactionEmail(PropertyRazorOrderResponse entity) {
        String content = bookRoom;
        content = content.replaceAll("\\*transaction\\*", entity.getTransactionId() != null ? entity.getTransactionId() + "" : "");
        content = content.replaceAll("\\*status\\*", entity.getStatus() != null ? entity.getStatus() + "" : "");
        content = content.replaceAll("\\*amount\\*", entity.getAmount() != null ? entity.getAmount() + "" : "");
        content = content.replaceAll("\\*name\\*", entity.getTransaction().getCreatedByName() != null ? entity.getTransaction().getCreatedByName() : "");
        content = content.replaceAll("\\*phone\\*", entity.getTransaction().getContactNo() != null ? entity.getTransaction().getContactNo() : "");
        content = content.replaceAll("\\*email\\*", entity.getTransaction().getCreatedByEmail() != null ? entity.getTransaction().getCreatedByEmail() : "");
        content = content.replaceAll("\\*move_date\\*", entity.getTransaction().getDate() != null ? entity.getTransaction().getDate() : "");
        String contentBody = content;
        boolean success = EmailUtil.sendEmail(sentTo, from, host, port, username, password, "Book room - Myroomie.in",
                contentBody, null, true);
        return success;
    }

    private String getDate(Date visitingDate) {
        String date = DateUtil.formateDate(visitingDate, DateUtil.TIMESTAMP_PATTERN);
        if (date != null) {
            return date;
        } else {
            return "";
        }
    }

}
