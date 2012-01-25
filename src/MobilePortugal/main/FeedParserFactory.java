package MobilePortugal.main;
public abstract class FeedParserFactory {
	
	static String feedUrl = "http://feeds.feedburner.com/mobileportugal/OutX?format=xml";
	static String feedUrl2 = "http://mobileportugal.sapo.pt/category/novidades/nacional/feed/";
	static String feedUrl3 = "http://mobileportugal.sapo.pt/category/novidades/internacional/feed/";
	static String feedUrl4 = "http://mobileportugal.sapo.pt/category/novidades/tablets/feed/";
	static String feedUrl5 = "http://mobileportugal.sapo.pt/category/novidades/analises/feed/";
	static String feedUrl6 = "http://mobileportugal.sapo.pt/category/novidades/entrevistas/feed/";
	static String feedUrl7 = "http://mobileportugal.sapo.pt/category/novidades/software/feed/";
	static String feedUrl8 = "http://mobileportugal.sapo.pt/category/videos-2/feed/";
	
	public static FeedParser getParser(){
		return getParser(ParserType.DOM, feedUrl);
	}
	
	public static FeedParser getParser(ParserType type, String url)
	{
		if (url.equals("Nacional"))
		{
			return new DomFeedParser(feedUrl2);
		}
		else if (url.equals("Internacional"))
		{
			return new DomFeedParser(feedUrl3);
		}
		else if (url.equals("Tablets"))
		{
			return new DomFeedParser(feedUrl4);
		}
		else if (url.equals("Anlises"))
		{
			return new DomFeedParser(feedUrl5);
		}
		else if (url.equals("Entrevistas"))
		{
			return new DomFeedParser(feedUrl6);
		}
		else if (url.equals("Aplicaes"))
		{
			return new DomFeedParser(feedUrl7);
		}
		else if (url.equals("Video"))
		{
			return new DomFeedParser(feedUrl8);
		}
		else
		{
			return new DomFeedParser(feedUrl);
		}
	}
}
