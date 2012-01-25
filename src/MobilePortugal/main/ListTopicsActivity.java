package MobilePortugal.main;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListTopicsActivity extends ListActivity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.cat);
        
        String[] names = new String[] { "Análises", "Aplicações", "Entrevistas", "Internacional", "Nacional", "Tablets"};
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_cat, R.id.topic, names));
            
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();
			
		//Toast.makeText(this, keyword.replaceAll("[^\\p{ASCII}]", ""), Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this, ListNewsCatActivity.class);
		i.putExtra("Feed", keyword.replaceAll("[^\\p{ASCII}]", ""));
		startActivity(i);
	}
	
	public void ultimas(View v)
	{
		Intent i = new Intent(this, ListNewsActivity.class);
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
}