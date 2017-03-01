package info.unterstein.alexa.elastic;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

class ElasticSearchClient {
  private final TransportClient client;

  ElasticSearchClient(String host, int port, String clusterName) {
    Settings settings = Settings.builder()
        .put("cluster.name", clusterName).build();
    try {
      client = new PreBuiltTransportClient(settings)
          .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }


  long count(String term) {
    return client.prepareSearch().setQuery(QueryBuilders.simpleQueryStringQuery(term)).get().getHits().totalHits();
  }
}
