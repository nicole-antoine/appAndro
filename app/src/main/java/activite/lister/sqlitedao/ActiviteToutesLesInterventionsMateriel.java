package activite.lister.sqlitedao;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.classe.adapter.MaterielInterventionAdapter;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteToutesLesInterventionsMateriel extends ListActivity {
	
	public DAODB dba;
	public MaterielInterventionAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
		dba = new DAODB(this);
		dba.open();
		
		setContentView(R.layout.les_materielinterventions);
		super.onCreate(savedInstanceState);
		adapter = new MaterielInterventionAdapter(this, dba);
		this.setListAdapter(adapter);	
		}
		catch (Exception e)
		{
			createDialog("Erreur activite tickets", e.getMessage());
		}
		final ListView prestListView = (ListView) findViewById(android.R.id.list);
	
	
		prestListView.setClickable(true);
		prestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				try
				{
				int[] tab = adapter.getListId(position);
				int idMat = tab[0];
				int idInterv = tab[1];
				
				Toast.makeText(getApplicationContext(), "IdMat: " + idMat + ", IdInterv: " + idInterv, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ActiviteToutesLesInterventionsMateriel.this, ActiviteReglementTicket.class);
				intent.putExtra("idmat", idMat);
				intent.putExtra("idinterv", idInterv);
				startActivity(intent);
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
		//Creation d'une popup affichant un message
		AlertDialog ad = new AlertDialog.Builder(this).setPositiveButton("OK", null).setTitle(title).setMessage(text).create();
		ad.show();
	}
}