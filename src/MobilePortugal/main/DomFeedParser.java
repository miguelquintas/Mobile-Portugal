package MobilePortugal.main;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DomFeedParser extends BaseFeedParser {

	protected DomFeedParser(String feedUrl) {
		super(feedUrl);
	}

	public ArrayList<Message> parse() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(this.getInputStream());
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName(ITEM);
			for (int i=0;i<items.getLength();i++){
				Message message = new Message();
				Node item = items.item(i);
				NodeList properties = item.getChildNodes();
				for (int j=0;j<properties.getLength();j++){
					Node property = properties.item(j);
					String name = property.getNodeName();
					if (name.equalsIgnoreCase(TITLE)){
						message.setTitle(property.getFirstChild().getNodeValue());
					} else if (name.equalsIgnoreCase(LINK)){
						message.setLink(property.getFirstChild().getNodeValue());
					} else if (name.equalsIgnoreCase(DESCRIPTION)){
						StringBuilder text = new StringBuilder();
						NodeList chars = property.getChildNodes();
						for (int k=0;k<chars.getLength();k++){
							text.append(chars.item(k).getNodeValue());
						}
						message.setDescription(text.toString());
					} else if (name.equalsIgnoreCase(PUB_DATE)){
						message.setDate(property.getFirstChild().getNodeValue());
					} else if (name.equalsIgnoreCase(IMAGE)){
						message.setWeb(property.getFirstChild().getNodeValue());
						String image = getImage(property.getFirstChild().getNodeValue());
						
						message.setBitmap(getBitmap(image));
					} else if (name.equalsIgnoreCase(AUTHOR)){
						message.setAuthor(property.getFirstChild().getNodeValue());
					}
				}
				messages.add(message);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return messages;
	}
	
	public String getImage(String image)
	{
		String[] temp = image.split("src=");
		String[] temp2 = temp[1].split("alt");
		String result = temp2[0].replaceAll("\"", "");
		
		return result;
	}
	
	public Bitmap getBitmap(String url)
	{
		Bitmap bm = null;
		URL myFileUrl = null;
		try {
			myFileUrl= new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
            HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            //int length = conn.getContentLength();
            InputStream is = conn.getInputStream();
            
            bm = BitmapFactory.decodeStream(is);
            
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
		
		return bm;
	}
}
