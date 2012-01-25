package MobilePortugal.main;

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListNewsActivity extends ListActivity{
    
    private ArrayList<Message> m_messages = null;
    private MessageAdapter m_adapter;
    String url = "Geral";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        m_messages = new ArrayList<Message>(); 
        loadFeed(ParserType.DOM);
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(this, NewsActivity.class);
		i.putExtra("Feed", (CharSequence) m_messages.get(position).getWeb());
		i.putExtra("Title", m_messages.get(position).getTitle());
		i.putExtra("Author", m_messages.get(position).getAuthor());
		i.putExtra("Date", m_messages.get(position).getDate());
		startActivity(i);
		
	}
    
    private void loadFeed(ParserType type){
    	try{
	    	FeedParser parser = FeedParserFactory.getParser(type, url);
	    	m_messages = parser.parse();
	    	ArrayList<Message> titles = new ArrayList<Message>(m_messages.size());
	    	for (Message msg : m_messages){
	    		titles.add(msg);
	    	}
	    	this.m_adapter = new MessageAdapter(this, R.layout.list_item, m_messages);
	        setListAdapter(this.m_adapter);
    	} catch (Throwable t){
    		//Log.e("MobilePortugal",t.getMessage(),t);
    	}
    }
    
	public void refresh(View v)
	{
		m_messages.clear();
		loadFeed(ParserType.DOM);
	}
	
	public void topics(View v)
	{
		Intent i = new Intent(this, ListTopicsActivity.class);
		startActivity(i);
	}
	
	public void videos(View v)
	{
		Intent i = new Intent(this, ListVidsActivity.class);
		startActivity(i);
	}
	
	public void expositor(View v)
	{
		//Intent i = new Intent(this, NewsActivity.class);
		//startActivity(i);
	}
	
    private class MessageAdapter extends ArrayAdapter<Message> {

        private ArrayList<Message> items;

        public MessageAdapter(Context context, int textViewResourceId, ArrayList<Message> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_item, null);
                }
                Message o = items.get(position);
                if (o != null) {
                        TextView tt = (TextView) v.findViewById(R.id.title);
                        TextView bt = (TextView) v.findViewById(R.id.description);
                        ImageView image = (ImageView) v.findViewById(R.id.thumb);
                        if (tt != null) {
                        	tt.setText(o.getTitle());                            
                        }
                        
                        if(bt != null)
                        {	
                        	String desc = o.getDescription();
                        	bt.setText(desc+"...");
                        }
                        
                        if(image != null)
                        {
							image.setImageBitmap(o.getBitmap());
                  		}   
                	}
                return v;
        }
    }
}
