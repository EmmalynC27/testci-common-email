package org.apache.commons.mail;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.Session;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.Properties;
import java.util.Map;

public class EmailTest {
    private static final String[] TEST_EMAILS = { "abc@example.com", "def@example.com", "ghi@example.com" };
    private EmailConcrete email;

    @Before
    public void setUpEmailTest() throws Exception {
        email = new EmailConcrete();
        email.setHostName("localhost"); // Set default hostname for all tests
    }

    @After
    public void tearDownEmailTest() throws Exception {
        email = null;
    }

    @Test
    public void testAddBcc() throws Exception {
        email.addBcc(TEST_EMAILS);
        assertEquals(3, email.getBccAddresses().size());
    }

    @Test
    public void testAddCc() throws Exception {
        email.addCc("cc@example.com");
        assertEquals(1, email.getCcAddresses().size());
    }

    @Test
    public void testAddCcMultipleTimes() throws Exception {
        email.addCc("cc1@example.com");
        email.addCc("cc2@example.com");
        assertEquals(2, email.getCcAddresses().size());
    }

    @Test
    public void testAddHeader() {
        String headerName = "X-Custom-Header";
        String headerValue = "CustomValue";
        
        email.addHeader(headerName, headerValue);
        Map<String, String> headers = email.getHeaders();
        
        assertNotNull("Headers map should not be null", headers);
        assertEquals("Header value should match", headerValue, headers.get(headerName));
    }

    @Test
    public void testAddReplyTo() throws Exception {
        email.addReplyTo("reply@example.com", "Reply Name");
        assertEquals(1, email.getReplyToAddresses().size());
    }

    @Test
    public void testAddReplyToWithoutName() throws Exception {
        email.addReplyTo("reply@example.com", null);
        assertEquals(1, email.getReplyToAddresses().size());
    }

    @Test
    public void testBuildMimeMessage() throws Exception {
        email.setFrom("from@example.com");
        email.addTo("to@example.com");
        email.setSubject("Test Subject");
        email.setMsg("Test Message");
        email.setHostName("localhost"); // Explicitly set hostname

        email.buildMimeMessage();
        assertNotNull("MimeMessage should not be null", email.getMimeMessage());
    }

    @Test(expected = EmailException.class)
    public void testBuildMimeMessageWithoutFrom() throws Exception {
        email.addTo("to@example.com");
        email.buildMimeMessage();
    }

    @Test
    public void testGetHostName() {
        String testHost = "smtp.example.com";
        email.setHostName(testHost);
        assertEquals(testHost, email.getHostName());
    }

    @Test
    public void testGetHostNameDefault() {
        EmailConcrete defaultEmail = new EmailConcrete();
        assertNull(defaultEmail.getHostName());
    }

    @Test
    public void testGetMailSession() throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.example.com");
        props.setProperty("mail.smtp.port", "587");
        email.setMailSession(Session.getInstance(props));
        assertNotNull(email.getMailSession());
    }

    @Test
    public void testGetMailSessionWithSSL() throws Exception {
        email.setSSLOnConnect(true);
        email.setHostName("localhost"); // Ensure hostname is set
        Session session = email.getMailSession();
        assertEquals("true", session.getProperties().getProperty("mail.smtp.ssl.enable"));
    }

    @Test
    public void testGetSentDate() {
        Date testDate = new Date();
        email.setSentDate(testDate);
        assertEquals(testDate, email.getSentDate());
    }

    @Test
    public void testGetSentDateCustom() {
        Date customDate = new Date(1672531200000L);
        email.setSentDate(customDate);
        assertEquals(customDate, email.getSentDate());
    }

    @Test
    public void testGetSocketConnectionTimeout() {
        email.setSocketConnectionTimeout(5000);
        assertEquals(5000, email.getSocketConnectionTimeout());
    }

    @Test
    public void testSetFrom() throws Exception {
        email.setFrom("from@example.com");
        assertEquals("from@example.com", email.getFromAddress().getAddress());
    }

    @Test(expected = EmailException.class)
    public void testSetFromInvalidEmail() throws Exception {
        email.setFrom("invalid-email-format");
    }
}