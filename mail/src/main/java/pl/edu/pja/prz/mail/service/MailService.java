package pl.edu.pja.prz.mail.service;

import pl.edu.pja.prz.mail.model.BaseMail;

public interface MailService {

	void sendEmail(BaseMail dto);
}
