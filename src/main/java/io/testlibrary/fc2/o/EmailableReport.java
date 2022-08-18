package io.testlibrary.fc2.o;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class EmailableReport {
	public static void emailReport(ExtentHtmlReporter htmlReporter) {
		try {

			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(htmlReporter.getFilePath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Report Of Test Script");
			attachment.setName("Automation Test Report.html");

			MultiPartEmail email1 = new MultiPartEmail();
			email1.setHostName("smtp.gmail.com");
			email1.setSmtpPort(465);
			email1.setAuthenticator(new DefaultAuthenticator("formcreatorteam@a-cti.com", "fctest@1234"));
			email1.setSSLOnConnect(true);
			email1.addTo("formcreatorteam@a-cti.com", "Formcreator Team");
			email1.setFrom("formcreatorteam@a-cti.com", "Me");
			email1.setSubject("Test Report For Formcreator2.0 Automation");
			email1.setMsg("Test Report");
			email1.attach(attachment);
			email1.send();
			System.out.println("Report Sent Successfully");
		} catch (Exception rv) {
			System.out.println("email failed");
			System.out.println(rv.getMessage());
		}
	}

}
