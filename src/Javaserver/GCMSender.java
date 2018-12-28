package Javaserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMSender
{
	public void sendMessage() throws IOException
	{
		Sender sender = new Sender("AIzaSyAmb6MLj9OwG-brINRcq7DMiqEC0pUWQ5c");
		Message message = new Message.Builder().addData("ABS","유통기한이 지난 물품이있습니다").build();
		Result result = sender
				.send(message,
						"APA91bGUnEq0zgRbPcgWgJSBAVoHaEe-EuiQjslAxx0SX1OboYN_yLIn4mHWlWaL9QlyT_UcBGUqmIhycFkDDoagUk0oFEIu5yBSCUCbEjdnfBBmB5bXLkdjKFJ72s70lNDaw0BbXgGH4XDWtVQb9V-vpeufVJqB8Q",
						5);
		System.out.println("Message Result: " + result.toString());
	}

	public void checkList()
	{
		BufferedReader bufferReader;
		try
		{
			bufferReader = new BufferedReader(new InputStreamReader(
					new FileInputStream("1.txt"), "UTF-8"));
			String str;
			int lineNum = 0;
			ArrayList arr = new ArrayList();
			boolean swit = true;
			while (swit)
			{
				if ((str = bufferReader.readLine()) != null)
				{
					arr.add(str);
					lineNum++;
					swit = true;
				}
				else
					swit = false;
			}
			bufferReader.close();
			for (int time = 1; time < arr.size(); time += 2)
			{
				long now = System.currentTimeMillis();
				SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd",
						Locale.KOREA);
				Date today = new Date(now);
				Date deadline = simple.parse((String) arr.get(time));
				if(today.after(deadline)){
					sendMessage();
					//System.out.println("날짜:"+arr.get(time));
					break;
				}
			}
		}

		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
