package activite.lister.sqlitedao;

import activite.ajouter.sqlitedao.ActiviteContratAjouter;
import activite.modifier.sqlitedao.ActiviteContratModifier;
import activite.supprimer.sqlitedao.ActiviteContratSupprimer;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.classe.adapter.ContratLocationAdapter;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteTousLesContrats extends ListActivity{
	
	public DAODB dba;
	public ContratLocationAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dba = new DAODB(this);
		dba.open();
		setContentView(R.layout.les_contrats);
	
		super.onCreate(savedInstanceState);
		adapter = new ContratLocationAdapter(this, dba);
		this.setListAdapter(adapter);	
		
		//--------------------------------------------------------------
        //Creation d'un bouton Ajouter associe e la ressource btn_ajouter_contrat
        //--------------------------------------------------------------
        
        Button btnAjouterContrat = (Button) findViewById(R.id.btn_ajouter_contrat);
        
        //Utilisation d'un Listener [interface de gestion d'evenements]
        //pour recuperer l'interaction avec le bouton
        btnAjouterContrat.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View arg0)
        	{ 
        		// Affichage d'un message dans le LogCat du DDMS
        		// Log.i("LocDVD", "Bouton Policier");
        		
        		//Creation d'un AlertDialog
        		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteTousLesContrats.this);
        		alertDialog.setTitle("Ajouter Contrats Location");
        		alertDialog.setMessage("Voulez-vous ajouter un contrat de location ?");
        		//alertDialog.setIcon(R.drawable.policier);
        		
        		alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
        		{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Ajouter", Toast.LENGTH_SHORT);
		        		toast.show();
		        		
		        		Intent intent = new Intent(ActiviteTousLesContrats.this, ActiviteContratAjouter.class);
		        		startActivity(intent);
					}
				});
        		alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
        		alertDialog.setNeutralButton("Je sais pas", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
        		
        	alertDialog.show();
        	
        	}
        	
        });
        
        //--------------------------------------------------------------
        //Creation d'un bouton Modifier associe a la ressource btn_modifier_contrat
        //--------------------------------------------------------------
        
        Button btnModifierContrat = (Button) findViewById(R.id.btn_modifier_contrat);
        
        //Utilisation d'un Listener [interface de gestion d'evenements]
        //pour recuperer l'interaction avec le bouton
        btnModifierContrat.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View arg0)
        	{ 
        		// Affichage d'un message dans le LogCat du DDMS
        		// Log.i("LocDVD", "Bouton Policier");
        		
        		//Creation d'un AlertDialog
        		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteTousLesContrats.this);
        		alertDialog.setTitle("Modifier Contrat Location");
        		alertDialog.setMessage("Voulez-vous modifier un contrat de location ?");
        		//alertDialog.setIcon(R.drawable.policier);
        		
        		alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
        		{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Ajouter", Toast.LENGTH_SHORT);
		        		toast.show();
		        		
		        		Intent intent = new Intent(ActiviteTousLesContrats.this, ActiviteContratModifier.class);
		        		startActivity(intent);
					}
				});
        		alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
        		alertDialog.setNeutralButton("Je sais pas", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
        		
        	alertDialog.show();
        	
        	}
        	
        });
        
        //--------------------------------------------------------------
        //Creation d'un bouton Supprimer associe a la ressource btn_supprimer_contrat
        //--------------------------------------------------------------
        
        Button btnSupprimerContrat = (Button) findViewById(R.id.btn_contrat_supprimer);
        
        //Utilisation d'un Listener [interface de gestion d'evenements]
        //pour recuperer l'interaction avec le bouton
        btnSupprimerContrat.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View arg0)
        	{ 
        		// Affichage d'un message dans le LogCat du DDMS
        		// Log.i("LocDVD", "Bouton Policier");
        		
        		//Creation d'un AlertDialog
        		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteTousLesContrats.this);
        		alertDialog.setTitle("Supprimer Contrats Location");
        		alertDialog.setMessage("Voulez-vous supprimer un contrat de location ?");
        		//alertDialog.setIcon(R.drawable.policier);
        		
        		alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
        		{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Supprimer", Toast.LENGTH_SHORT);
		        		toast.show();
		        		
		        		Intent intent = new Intent(ActiviteTousLesContrats.this, ActiviteContratSupprimer.class);
		        		startActivity(intent);
					}
				});
        		alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
        		alertDialog.setNeutralButton("Je sais pas", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
        		
        	alertDialog.show();
        	
        	}
        	
        });
	}
}
