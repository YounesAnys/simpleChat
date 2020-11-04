

package ocsf.client;

import java.util.*;
import java.io.*;
import java.net.*;


public class ObservableClient extends Observable
{

  public static final String CONNECTION_EXCEPTION = "#OC:Connection error.";


  public static final String CONNECTION_CLOSED = "#OC:Connection closed.";


  public static final String CONNECTION_ESTABLISHED = "#OC:Connection established.";


  private AdaptableClient service;



  public ObservableClient(String host, int port)
  {
    service = new AdaptableClient(host, port, this);
  }


  final public void openConnection() throws IOException
  {
    service.openConnection();
  }


  final public void closeConnection() throws IOException
  {
    service.closeConnection();
  }


  final public void sendToServer(Object msg) throws IOException
  {
    service.sendToServer(msg);
  }


  final public boolean isConnected()
  {
    return service.isConnected();
  }


  final public int getPort()
  {
    return service.getPort();
  }


  final public void setPort(int port)
  {
    service.setPort(port);
  }


  final public String getHost()
  {
    return service.getHost();
  }


  final public void setHost(String host)
  {
    service.setHost(host);
  }


  final public InetAddress getInetAddress()
  {
    return service.getInetAddress();
  }




  protected void handleMessageFromServer(Object message)
  {
    setChanged();
    notifyObservers(message);
  }

  protected void connectionClosed()
  {
    setChanged();
    notifyObservers(CONNECTION_CLOSED);
  }


  protected void connectionException(Exception exception)
  {
    setChanged();
    notifyObservers(CONNECTION_EXCEPTION);
  }

  
  protected void connectionEstablished()
  {
    setChanged();
    notifyObservers(CONNECTION_ESTABLISHED);
  }
}
