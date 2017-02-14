package io.voiceops.elastic;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ElasticSpeechlet implements Speechlet {
  private static final Logger log = LoggerFactory.getLogger(ElasticSpeechlet.class);

  public ElasticSpeechlet() {
    // TODO initialize elasticsearch client
  }

  @Override
  public void onSessionStarted(final SessionStartedRequest request, final Session session)
      throws SpeechletException {
    log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
  }

  @Override
  public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
      throws SpeechletException {
    log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    return getWelcomeResponse();
  }

  @Override
  public SpeechletResponse onIntent(final IntentRequest request, final Session session)
      throws SpeechletException {
    log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

    Intent intent = request.getIntent();
    String intentName = (intent != null) ? intent.getName() : null;

    if ("ElasticSearch".equals(intentName)) {
      // TODO implement ElasticSearch intent
      return errorResponse();
    } else if ("ElasticCount".equals(intentName)) {
      // TODO implement ElasticCount intent
      return errorResponse();
    } else {
      log.error("Intent {} not recognized for requestId {}", intentName, request.getRequestId());
      throw new SpeechletException("Invalid Intent");
    }
  }

  @Override
  public void onSessionEnded(final SessionEndedRequest request, final Session session)
      throws SpeechletException {
    log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    // any cleanup logic goes here
  }

  /**
   * Welcome text, explaining current elasticsearch skill features.
   *
   * @return welcome text tell response.
   */
  private SpeechletResponse getWelcomeResponse() {
    // TODO use i18n
    String speechText = "Welcome to Elasticsearch. You can ask me to search something.";

    PlainTextOutputSpeech result = new PlainTextOutputSpeech();
    result.setText(speechText);

    return SpeechletResponse.newTellResponse(result);
  }

  /**
   * Handle error.
   *
   * @return error speech response
   */
  private SpeechletResponse errorResponse() {
    // TODO use i18n
    String text = "Something went wrong, please try again.";
    PlainTextOutputSpeech result = new PlainTextOutputSpeech();
    result.setText(text);
    return SpeechletResponse.newTellResponse(result);
  }
}
