package org.apache.commons.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailConcrete extends Email {
    private List<InternetAddress> bccAddresses;

    public EmailConcrete() {
        super();
        bccAddresses = new ArrayList<InternetAddress>();
    }

    @Override
    public Email setMsg(String msg) throws EmailException {
        setContent(msg, EmailConstants.TEXT_PLAIN);
        return this;
    }

    @Override
    public List<InternetAddress> getBccAddresses() {
        return bccAddresses;
    }

    @Override
    public Email addBcc(String... emails) throws EmailException {
        for (String email : emails) {
            try {
				bccAddresses.add(new InternetAddress(email));
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return this;
    }

	public List<InternetAddress> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
}


