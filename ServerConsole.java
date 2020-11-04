import java.io.*;
import common.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;


public class ServerConsole extends AbstractServer implements ChatIF
{

  final public static int DEFAULT_PORT = 5555;

  ChatIF clientUI;

  public ServerConsole(int port)
  {
      super(port);

  }

  public void accept()
  {
    try
    {
      BufferedReader fromConsole =
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true)
      {
        message = fromConsole.readLine();
        char[] tempMessage = message.trim().toCharArray();
        if(tempMessage[0] == '#') {
        	control(message);
        }else
        	handleMessageFromClient(message, null);
      }
    }
    catch (Exception ex)
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

  public void display(String message)
  {
    System.out.println("> " + message);
  }

  public static void main(String[] args)
  {
    int port = 0;

    try
    {
          port = Integer.parseInt(args[0]);
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      port = 5555;
    }
    ServerConsole chat= new ServerConsole(port);
    chat.accept();
  }

  public void control(String message) {
	  char[] ch = message.toCharArray();
	  char[] temp = new char[ch.length-1];

	  for(int i = 1; i < ch.length; i++) {
		  temp[i-1] = ch[i];
	  }

	  char[] tempPortOrHost = new char[ch.length-8];

	  for(int i = 9; i < ch.length; i++) {
		  tempPortOrHost[i-9] = ch[i];
	  }

	  String newMessage = new String(temp);
	  String setters = new String(tempPortOrHost);

	  if(newMessage == "quit") {
		  try {
			close();
		} catch (IOException e) {
			clientUI.display("Server cannot be closed");
			e.printStackTrace();
		}

	  }else if(newMessage == "getClientConnection") {

	  }else if(newMessage.contains("getNumberOfClient")) {
		  clientUI.display(Integer.toString(getNumberOfClients()));

	  }else if(newMessage == "getport") {
		  clientUI.display(Integer.toString(getPort()));

	  }else if(newMessage.contains("setport")) {
		  try {
			  setPort(Integer.parseInt(setters));
		  }catch (Exception exception) {
			  clientUI.display("Not all digits");
		  }

	  }else if(newMessage == "setTimeout") {
		  try {
			  setTimeout(Integer.parseInt(setters));
		  }catch (Exception exception) {
			  clientUI.display("Not all digits");
		  }

	  }else if(newMessage == "setBacklog") {
		  try {
			  setBacklog(Integer.parseInt(setters));
		  }catch (Exception exception) {
			  clientUI.display("Not all digits");
		  }

	  }
  }



protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	
}
}
