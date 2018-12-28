package Javaserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UTFDataFormatException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListSender
{
	public void listSender()
	{
		ServerSocket ss;
		Socket socket;
		OutputStream os;
		File file = new File("1.txt");
		int readCount = 0;
		FileInputStream fis;
		while (true)
		{
			try
			{
				fis = new FileInputStream(file);
				ss = new ServerSocket(55530);
				socket = ss.accept();
				os = socket.getOutputStream();
				byte[] buffer = new byte[1024];
				System.out.println("클라이언트 ip:"+socket.getInetAddress());
				while ((readCount = fis.read(buffer)) > 0)
				{
					os.write(buffer);
				}
				os.close();
				fis.close();
				ss.close();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
