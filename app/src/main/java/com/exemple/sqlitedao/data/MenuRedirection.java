package com.exemple.sqlitedao.data;

import com.example.preference.Preference;
import com.exemple.sqlitedao.R;

import activite.lister.sqlitedao.ActiviteTousLesContacts;
import activite.lister.sqlitedao.ActiviteTousLesContrats;
import activite.lister.sqlitedao.ActiviteToutesLesInterventions;
import activite.lister.sqlitedao.ActiviteToutesLesInterventionsMateriel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuRedirection extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.redirection_menu);
		
		//int user = this.getIntent().getIntExtra("userid", -1);
		
		//--------------------------------------------------------------
		//Creation d'un bouton ActiContact associ� � la ressource btn_activite_contact
		//--------------------------------------------------------------
    
		Button btnActiviteContact = (Button) findViewById(R.id.btn_activite_contact);
		
		//Utilisation d'un Listener [interface de gestion d'�v�nements]
		//pour r�cup�rer l'interaction avec le bouton
		btnActiviteContact.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View arg0)
			{ 	
				// Affichage d'un message dans le LogCat du DDMS
				// Log.i("LocDVD", "Bouton Policier");
    		
				//Cr�ation d'un AlertDialog
				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuRedirection.this);
				alertDialog.setTitle("Activit� Contact");
				alertDialog.setMessage("Rejoindre l'activit� contact ?");
				//alertDialog.setIcon(R.drawable.policier);
    		
				alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT);
						toast.show();
	        		
						Intent intent = new Intent(MenuRedirection.this, ActiviteTousLesContacts.class);
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
		//Creation d'un bouton ActiContrat associ� � la ressource btn_activite_contrat
		//--------------------------------------------------------------
    
		Button btnActiviteContrat = (Button) findViewById(R.id.btn_activite_contrat);
    
		//Utilisation d'un Listener [interface de gestion d'�v�nements]
		//pour r�cup�rer l'interaction avec le bouton
		btnActiviteContrat.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View arg0)
    		{ 
				// Affichage d'un message dans le LogCat du DDMS
				// Log.i("LocDVD", "Bouton Policier");
				//setTheme(android.R.style.Theme_Black);
				//Cr�ation d'un AlertDialog
				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuRedirection.this);
				alertDialog.setTitle("Activit� Contrat");
				alertDialog.setMessage("Rejoindre l'activit� contrat ?");
				//alertDialog.setIcon(R.drawable.policier);
    		
				alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Contrat", Toast.LENGTH_SHORT);
						toast.show();
	        		
						Intent intent = new Intent(MenuRedirection.this, ActiviteTousLesContrats.class);
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
		//Creation d'un bouton Pr�f�rence associ� � la ressource pr�f�rence
		//--------------------------------------------------------------
    
		Button btnActivitePreference = (Button) findViewById(R.id.btn_activite_preference);
    
		//Utilisation d'un Listener [interface de gestion d'�v�nements]
		//pour r�cup�rer l'interaction avec le bouton
		btnActivitePreference.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View arg0)
			{ 
				// Affichage d'un message dans le LogCat du DDMS
				// Log.i("LocDVD", "Bouton Policier");
    		
				//Cr�ation d'un AlertDialog
				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuRedirection.this);
				alertDialog.setTitle("Activit� Pr�f�rence");
				alertDialog.setMessage("Rejoindre l'activit� pr�f�rence ?");
				//alertDialog.setIcon(R.drawable.policier);
    		
				alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface arg0, int arg1)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "preference", Toast.LENGTH_SHORT);
						toast.show();
	        		
						Intent intent = new Intent(MenuRedirection.this, Preference.class);
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
		//Creation d'un bouton Pr�f�rence associ� � la ressource pr�f�rence
		//--------------------------------------------------------------
		    
				Button btnActiviteIntervention = (Button) findViewById(R.id.btn_activite_intervention);
		    
				//Utilisation d'un Listener [interface de gestion d'�v�nements]
				//pour r�cup�rer l'interaction avec le bouton
				btnActiviteIntervention.setOnClickListener(new Button.OnClickListener()
				{
					public void onClick(View arg0)
					{ 
						// Affichage d'un message dans le LogCat du DDMS
						// Log.i("LocDVD", "Bouton Policier");
		    		
						//Cr�ation d'un AlertDialog
						final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuRedirection.this);
						alertDialog.setTitle("Activit� Intervention");
						alertDialog.setMessage("Rejoindre l'activit� intervention ?");
						//alertDialog.setIcon(R.drawable.policier);
		    		
						alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
						{
							public void onClick(DialogInterface arg0, int arg1)
							{
								Toast toast = Toast.makeText(getApplicationContext(), "intervention", Toast.LENGTH_SHORT);
								toast.show();
			        		
								Intent intent = new Intent(MenuRedirection.this, ActiviteToutesLesInterventions.class);
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
				//Creation d'un bouton Pr�f�rence associ� � la ressource pr�f�rence
				//--------------------------------------------------------------
				    
						Button btnActiviteMatIntervention = (Button) findViewById(R.id.btn_activite_materielintervention);
				    
						//Utilisation d'un Listener [interface de gestion d'�v�nements]
						//pour r�cup�rer l'interaction avec le bouton
						btnActiviteMatIntervention.setOnClickListener(new Button.OnClickListener()
						{
							public void onClick(View arg0)
							{ 
								// Affichage d'un message dans le LogCat du DDMS
								// Log.i("LocDVD", "Bouton Policier");
				    		
								//Cr�ation d'un AlertDialog
								final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuRedirection.this);
								alertDialog.setTitle("Activit�  Intervention materiel");
								alertDialog.setMessage("Rejoindre l'activit� intervention materiel ?");
								//alertDialog.setIcon(R.drawable.policier);
				    		
								alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
								{
									public void onClick(DialogInterface arg0, int arg1)
									{
										Toast toast = Toast.makeText(getApplicationContext(), "interventionmateriel", Toast.LENGTH_SHORT);
										toast.show();
					        		
										Intent intent = new Intent(MenuRedirection.this, ActiviteToutesLesInterventionsMateriel.class);
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
