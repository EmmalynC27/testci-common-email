package snippet;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

public class Snippet {
    // 1. DECLARE MISSING TEST EMAILS CONSTANT
    private static final String[] TEST_EMAILS = { "test@example.com", "test2@example.com" };

    // 2. ADD @Test ANNOTATION
    @Test
    public void testEmailFunctionality() throws EmailException {
        // 3. INITIALIZE EMAIL OBJECT
        Email email = new SimpleEmail();

        // Configure email settings (REQUIRED)
        email.setHostName("smtp.example.com"); // Replace with your SMTP host
        email.setSmtpPort(587); // Common SMTP port
        email.setAuthentication("username", "password");
        email.setStartTLSEnabled(true);
        email.setFrom("user@example.com");

        // 4. USE THE TEST_EMAILS VARIABLE
        email.addTo(TEST_EMAILS);
        email.setSubject("Test Email");
        email.setMsg("test");

        email.send(); // Send the email
    }
}