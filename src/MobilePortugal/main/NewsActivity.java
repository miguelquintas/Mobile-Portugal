package MobilePortugal.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsActivity extends Activity
{
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.news_description);
	        
	        Bundle bundle = getIntent().getExtras();
	        
	        WebView web = (WebView) findViewById(R.id.webText);
	        TextView title = (TextView) findViewById(R.id.title);
	        TextView date = (TextView) findViewById(R.id.date);
	        TextView author = (TextView) findViewById(R.id.author);
	        
	        title.setText(bundle.getString("Title"));
	        author.setText(bundle.getString("Author"));
	        
	        String dateString = bundle.getString("Date");
	        String text = bundle.getString("Feed");
	        
	        String finalweb = text.replaceAll("height=\"([^\"]*)\"", "");
	        String complete = finalweb.replaceAll("<!\\[CDATA\\[", "");
	        String finaldate = dateString.substring(0, 22);
	        
	        date.setText(finaldate);
	        
	        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><html><head><style>img{max-width: 200px;}</style></head><body>";
	        web.getSettings().setJavaScriptEnabled(true); 
	        web.getSettings().setDefaultFontSize(12);
	        
	        web.loadData(header+complete, "text/html", "UTF-8");
	    }
	 
	 	public void ultimas(View v)
		{
	 		Intent i = new Intent(this, ListNewsActivity.class);
			startActivity(i);
		}
	 	
	 	public void topics(View v)
		{
			Intent i = new Intent(this, ListTopicsActivity.class);
			startActivity(i);
		}
		
		public void videos(View v)
		{
			//Intent i = new Intent(this, NewsActivity.class);
			//startActivity(i);
		}
		
		public void expositor(View v)
		{
			//Intent i = new Intent(this, NewsActivity.class);
			//startActivity(i);
		}
}