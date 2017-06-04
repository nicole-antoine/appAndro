package activite.lister.sqlitedao;


import com.classe.adapter.ContactAdapter;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

import activite.ajouter.sqlitedao.ActiviteContactAjouter;
import activite.modifier.sqlitedao.ActiviteContactModifier;
import activite.supprimer.sqlitedao.ActiviteContactSupprimer;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActiviteTousLesContacts extends ListActivity{
	
	public DAODB dba;
	public ContactAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dba = new DAODB(this);
		dba.open();
		setContentView(R.layout.les_contacts);
	
		super.onCreate(savedInstanceState);
		adapter = new ContactAdapter(this, dba);
		this.setListAdapter(adapter);
		
		
		//--------------------------------------------------------------
        //Creation d'un bouton Ajouter associé à la ressource btn_ajouter_contact
        //--------------------------------------------------------------
        
        Button btnAjouterContact = (Button) findViewById(R.id.btn_ajouter_contact);
        
        //Utilisation d'un Listener [interface de gestion d'évènements]
        //pour récupérer l'interaction avec le bouton
        btnAjouterContact.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View arg0)
        	{ 
        		// Affichage d'un message dans le LogCat du DDMS
        		// Log.i("LocDVD", "Bouton Policier");
        		
        		//Création d'un AlertDialog
        		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteTousLesContacts.this);
        		alertDialog.setTitle("Ajouter Contact");
        		alertDialog.setMessage("Voulez-vous ajouter un contact ?");
        		//alertDialog.setIcon(R.drawable.policier);
        		
        		alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
        		{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Ajouter", Toast.LENGTH_SHORT);
		        		toast.show();
		        		
		        		Intent intent = new Intent(ActiviteTousLesContacts.this, ActiviteContactAjouter.class);
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
      //Creation d'un bouton Modifier associé à la ressource btn_modifier_contact
      //--------------------------------------------------------------
        
        Button btnModifierContact = (Button) findViewById(R.id.btn_modifier_contact);
        
        //Utilisation d'un Listener [interface de gestion d'évènements]
        //pour récupérer l'interaction avec le bouton
        btnModifierContact.setOnClickListener(new Button.OnClickListener()
        {
        	public void onClick(View arg0)
        	{ 
        		// Affichage d'un message dans le LogCat du DDMS
        		// Log.i("LocDVD", "Bouton Policier");
        		
        		//Création d'un AlertDialog
        		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteTousLesContacts.this);
        		alertDialog.setTitle("Modifier Contact");
        		alertDialog.setMessage("Voulez-vous modifier un contact ?");
        		//alertDialog.setIcon(R.drawable.policier);
        		
        		alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
        		{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Modifier", Toast.LENGTH_SHORT);
		        		toast.show();
		        		
		        		Intent intent = new Intent(ActiviteTousLesContacts.this, ActiviteContactModifier.class);
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
      //Creation d'un bouton Supprimer associé à la ressource btn_supprimer_contact
      //--------------------------------------------------------------
          
          Button btnSupprimerContact = (Button) findViewById(R.id.btn_supprimer_contact);
          
          //Utilisation d'un Listener [interface de gestion d'évènements]
          //pour récupérer l'interaction avec le bouton
          btnSupprimerContact.setOnClickListener(new Button.OnClickListener()
          {
          	public void onClick(View arg0)
          	{ 
          		// Affichage d'un message dans le LogCat du DDMS
          		// Log.i("LocDVD", "Bouton Policier");
          		
          		//Création d'un AlertDialog
          		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteTousLesContacts.this);
          		alertDialog.setTitle("Supprimer Contact");
          		alertDialog.setMessage("Voulez-vous supprimer un contact ?");
          		//alertDialog.setIcon(R.drawable.policier);
          		
          		alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
          		{
  					public void onClick(DialogInterface arg0, int arg1)
  					{
  						Toast toast = Toast.makeText(getApplicationContext(), "Supprimer", Toast.LENGTH_SHORT);
  		        		toast.show();
  		        		
  		        		Intent intent = new Intent(ActiviteTousLesContacts.this, ActiviteContactSupprimer.class);
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
