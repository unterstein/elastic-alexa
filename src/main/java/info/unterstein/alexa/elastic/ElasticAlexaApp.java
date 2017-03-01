package info.unterstein.alexa.elastic;

import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ElasticAlexaApp {

  public static void main(String[] args) {
    setAmazonProperties();
    SpringApplication.run(ElasticAlexaApp.class, args);
  }

  /**
   * Sets system properties which are picked up by the {@link SpeechletServlet}.
   */
  private static void setAmazonProperties() {
    // Disable signature checks for development
    System.setProperty(Sdk.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true");
    // Allow all application ids for development
    System.setProperty(Sdk.SUPPORTED_APPLICATION_IDS_SYSTEM_PROPERTY, "");
    // Disable timestamp verification for development
    System.setProperty(Sdk.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, "");
  }

  /**
   * Registers the {@link SpeechletServlet} at the application.
   *
   * @param speechlet Speechlet.
   * @return Registration.
   */
  @Bean
  public ServletRegistrationBean alexaServlet(ElasticSpeechlet speechlet) {
    SpeechletServlet speechServlet = new SpeechletServlet();
    speechServlet.setSpeechlet(speechlet);

    ServletRegistrationBean servlet = new ServletRegistrationBean(speechServlet, "/alexa");
    servlet.setName("alexa");

    return servlet;
  }
}
