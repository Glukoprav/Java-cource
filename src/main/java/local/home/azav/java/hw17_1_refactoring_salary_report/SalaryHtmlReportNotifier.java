package local.home.azav.java.hw17_1_refactoring_salary_report;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalaryHtmlReportNotifier {
    private static final Logger LOG = Logger.getLogger(SalaryHtmlReportNotifier.class.getName());
    private GenerateSalaryReport generateSalaryReport;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        generateSalaryReport = new GenerateSalaryReport(databaseConnection);
    }

    private void sendHTMLSalaryReport(StringBuilder resultingHtml, String recipients) {
        // now when the report is built we need to send it to the recipients list
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // we're going to use google mail to send this message
        mailSender.setHost("mail.google.com");
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            // setting message text, last parameter 'true' says that it is HTML format
            helper.setText(resultingHtml.toString(), true);
            helper.setSubject("Monthly department salary report");
        } catch (MessagingException e) {
            LOG.log(Level.SEVERE, "Exception: ", e);
        }
        // send the message
        mailSender.send(message);
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        StringBuilder stringBuilder = generateSalaryReport.convertResultSetToHTML(generateSalaryReport.generateSalaryResultSet(departmentId, dateFrom, dateTo));
        sendHTMLSalaryReport(stringBuilder,recipients);
    }
}
