

package ocsf.client;

import java.io.*;
import java.net.*;
import java.util.*;


class AdaptableClient extends AbstractClient
{

  private ObservableClient client;


  public AdaptableClient(String host, int port, ObservableClient client)
  {
    super(host, port);
    this.client = client;
  }


  final protected void connectionClosed()
  {
    client.connectionClosed();
  }


  final protected void connectionException(Exception exception)
  {
    client.connectionException(exception);
  }


  final protected void connectionEstablished()
  {
    client.connectionEstablished();
  }


  final protected void handleMessageFromServer(Object msg)
  {
    client.handleMessageFromServer(msg);
  }
}
