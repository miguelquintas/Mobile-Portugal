package MobilePortugal.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VidsActivity extends Activity
{
	@Override    
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.vids);
	        
	        Bundle bundle = getIntent().getExtras();
	        String text = bundle.getString("Feed");
	        
	        Document doc = Jsoup.parse(text);
	        Element videoUrl = doc.select("embed").first();
	        String url = videoUrl.absUrl("src");
	        String[] url2 = url.split("=");
	        String finalUrl = url2[1];
	        
	        VideoView videoView = (VideoView) findViewById(R.id.VideoView);
	        MediaController mediaController = new MediaController(this);
	        mediaController.setAnchorView(videoView);
	        // Set video link (mp4 format )
	        Uri video = Uri.parse(finalUrl);
	        videoView.setMediaController(mediaController);
	        videoView.setVideoURI(video);
	        videoView.start();     
	}
}