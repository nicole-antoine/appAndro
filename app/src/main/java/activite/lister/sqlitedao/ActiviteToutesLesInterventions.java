package activite.lister.sqlitedao;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

import com.classe.adapter.InterventionAdapter;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteToutesLesInterventions extends ListActivity {
	
	public DAODB dba;
	public InterventionAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dba = new DAODB(this);
		dba.open();
		
		setContentView(R.layout.les_interventions);
		super.onCreate(savedInstanceState);
		adapter = new InterventionAdapter(this, dba);
		this.setListAdapter(adapter);	
		
		final ListView prestListView = (ListView) findViewById(android.R.id.list);
	
	
		prestListView.setClickable(true);
		prestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				try
				{
				Long item = arg0.getAdapter().getItemId(position);
				Toast.makeText(getApplicationContext(), " " + item, Toast.LENGTH_SHORT).show();
				}
				catch (Exception e)
				{
					createDialog("test", e.getMessage());
				}
			}	
		});
	}
	
	private void createDialog(String title, String text)
	{
		//Création d'une popup affichant un message
		AlertDialog ad = new AlertDialog.Builder(this).setPositiveButton("OK", null).setTitle(title).setMessage(text).create();
		ad.show();
	}
}