package Javaserver;

public class RunMain
{
	public static void main(String args[])
	{
		ListSender send = new ListSender();
		GCMSender sender = new GCMSender();
		sender.checkList();
		send.listSender();
	}
}
