package com.fernandez.service;

import com.fernandez.dto.EmailBody;

public interface EmailPort {
    public boolean sendEmail(EmailBody emailBody);
}
