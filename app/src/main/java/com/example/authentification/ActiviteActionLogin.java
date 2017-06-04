package com.example.authentification;           

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preference.Preference;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;
import com.exemple.sqlitedao.data.MenuRedirection;

public class ActiviteActionLogin extends Activity {
	
	//Lien vers votre page php sur le serveur
	private static final String UPDATE_URL_LOGIN = "http://192.168.43.174/gl52FlowerPot/login.php";
	private static final String UPDATE_URL_IMPORTCONTRAT = "http://192.168.43.174/gl52FlowerPot/test_contratlocation.php";
	private static final String UPDATE_URL_IMPORTINTERVENTION = "http://192.168.43.174/gl52FlowerPot/lister_intervention.php";
	private static final String UPDATE_URL_IMPORTMATERIELINTERVENTION = "http://192.168.43.174/gl52FlowerPot/lister_materiel_intervenir.php";
	
	public ProgressDialog progressDialog;
	private EditText UserEditText;
	private EditText PassEditText;
	DAODB dba;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interface_login);
		dba = new DAODB(this.getBaseContext()); //this = contexte
	    dba.open();
	      
		//initialisation d'une progress bar
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Patientez");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(true);
		
		//Recuperation des elements de la vue definis dans le xml
		UserEditText = (EditText) findViewById(R.id.username);
		PassEditText = (EditText) findViewById(R.id.password);
		Button button = (Button) findViewById(R.id.okbutton);
		
		//Definition du listener du bouton
		button.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				
				SharedPreferences myprefs = PreferenceManager.getDefaultSharedPreferences(ActiviteActionLogin.this);
				
				final String prefsLogin = myprefs.getString("login", null);
				final String prefsPassword = myprefs.getString("password", null);
				
				//tester si les donnees login et password existent dans les preferences
				if (prefsLogin != null && prefsPassword != null)
				{
					//si c'est le cas tester concordance login et password avec les preferences
					String login = UserEditText.getText().toString();
					String password = PassEditText.getText().toString();
					try
					{
						if(login.equals(prefsLogin) && password.equals(prefsPassword))
						{
							Toast.makeText(ActiviteActionLogin.this, "Authentification reussie !!", Toast.LENGTH_SHORT).show();
							Intent i = new Intent(ActiviteActionLogin.this, MenuRedirection.class);
							ActiviteActionLogin.this.startActivity(i);
						}
						else
						{
							Toast.makeText(ActiviteActionLogin.this, "Authentification echouee", Toast.LENGTH_SHORT).show();
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					} //fin stack
				}
				else
				{
					// si ce n'est pas les cas, basculer dans les preferences
					Intent i = new Intent(ActiviteActionLogin.this, MenuRedirection.class);
					startActivity(i);

					/*int usersize = UserEditText.getText().length();
					int passsize = PassEditText.getText().length();
					//si les deux champs sont remplis
					if(usersize > 0 && passsize > 0)
					{
						progressDialog.show();
						String user = UserEditText.getText().toString();
						String pass = PassEditText.getText().toString();
						//On appelle la fonction doLogin qui va communiquer avec le PHP
						doLogin(user, pass);
					}
					else
						createDialog("Error", "Please enter Username and Password");*/
				}
			}
		});
		
		button = (Button) findViewById(R.id.cancelbutton);
		//Creation du listener du bouton cancel (on sort de l'appli)
		
		button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) 
			{
				quit(false, null);
			}
		});
		
	}
	
	private void quit(boolean success, Intent i)
	{
		// On renvoie un resultat qui va permettre de quitter l'appli
		setResult((success) ? Activity.RESULT_OK : Activity.RESULT_CANCELED, i);
		finish();
	}
	
	private void createDialog(String title, String text)
	{
		//Creation d'une popup affichant un message
		AlertDialog ad = new AlertDialog.Builder(this).setPositiveButton("OK", null).setTitle(title).setMessage(text).create();
		ad.show();
	}

	private void doLogin(final String login, final String pass)
	{
		//final String pw = md5(pass);
		final String pw = pass;
		//Creation d'un thread
		Thread t = new Thread()
		{
			public void run()
			{
            Looper.prepare();
                //On se connecte au serveur afin de communiquer avec le PHP
			    DefaultHttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 15000);
				
				HttpResponse response = null;
				HttpEntity entity = null;
				
				//importerContrat(response, entity, client);
				//imporIntervention(response, entity, client);
				//imporMaterielIntervention(response, entity, client);
				
				try
				{
					//On etablit un lien avec le scirpt PHP
					HttpPost post = new HttpPost(UPDATE_URL_LOGIN);
					List<NameValuePair> nvps = new ArrayList<NameValuePair>();
					nvps.add(new BasicNameValuePair("username", login));
					nvps.add(new BasicNameValuePair("password", pw));
					post.setHeader("Content-Type", "application/x-www-form-urlencoded");
					
					//On passe les parametres login et password qui vont etre recuperes par le script PHP en post
					post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
					
					//On recupere le resultat du script
					response = client.execute(post);
					entity = response.getEntity();
					InputStream is = entity.getContent();

					//On appelle une fonction definie plus bas pour traduire la reponse
					read(is);
					dba.close();
					is.close();
					if(entity != null)
						entity.consumeContent();					
				}
				catch(Exception e)
				{
					progressDialog.dismiss();
					createDialog("ERREUR LOGIN", "Couldn't establish connection" + e.getMessage());
				}	
				Looper.loop();
			}
		};
		t.start();
	}
	
	public void importerContrat(HttpResponse response, HttpEntity entity, DefaultHttpClient client)
	{
		// DEBUT IMPORT CONTRATLOCATIONS
		try
		{
			//On etablit un lien avec le scirpt PHP
			HttpPost post = new HttpPost(UPDATE_URL_IMPORTCONTRAT);
			post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			//On recupere le resultat du script
			response = client.execute(post);
			entity = response.getEntity();
			InputStream is = entity.getContent();
			
			//On appeller une fonctione pour traduire le JSON
			String strJson = lireLeJSON(is);
			
			// Creation et alimentation d'un tableau JSON avec la chaine de caracteres
			JSONArray jsonArray = new JSONArray(strJson);
			//createDialog("Nb Objet contrat:", " =" + jsonArray.length());
			// Parcours du tableau JSON
			for(int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				dba.insererContratLocation(Integer.parseInt(jsonObject.getString("idContratLocation")), Integer.parseInt(jsonObject.getString("idSociete")),
			  			Integer.parseInt(jsonObject.getString("ticketGratuit")), Integer.parseInt(jsonObject.getString("nbTickets")),
			  			jsonObject.getString("dateDebContrat"), Float.parseFloat(jsonObject.getString("ptttc")),
			  			Float.parseFloat(jsonObject.getString("pttva")), Float.parseFloat(jsonObject.getString("ptht")), Float.parseFloat(jsonObject.getString("depotGarantie")));
			}
			
			is.close();
			if(entity != null)
				entity.consumeContent();					
		}
		catch(Exception e)
		{
			progressDialog.dismiss();
			createDialog("ERREUR IMPORT CONTRAT", "Couldn't establish connection" + e.getMessage());
		}	
		// FIN IMPORT CONTRATLOCATIONS
		
	}
	
	public void imporIntervention(HttpResponse response, HttpEntity entity, DefaultHttpClient client) 
	{
		// DEBUT IMPORT INTERVENTIONS
		try
		{
			//On etablit un lien avec le scirpt PHP
			HttpPost post = new HttpPost(UPDATE_URL_IMPORTINTERVENTION);
			post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			//On recupere le resultat du script
			response = client.execute(post);
			entity = response.getEntity();
			InputStream is = entity.getContent();
			
			//On appelle une fonctione pour traduire le JSON
			String strJson = lireLeJSON(is);
			
			// Creation et alimentation d'un tableau JSON avec la chaine de caracteres
			JSONArray jsonArray = new JSONArray(strJson);
			// Parcours du tableau JSON
			createDialog("Nb Objet intervention:", " =" + jsonArray.length());
			for(int k = 0; k < jsonArray.length(); k++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(k);
				//createDialog("Test JSON", jsonObject.getString("dateCreainterv"));
				dba.insererIntervention(Integer.parseInt(jsonObject.getString("idIntervention")),jsonObject.getString("dateCreainterv")
						,jsonObject.getString("commentaire"),Integer.parseInt(jsonObject.getString("idEmploye")));
			}
			
			is.close();
			if(entity != null)
				entity.consumeContent();					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		// FIN IMPORT INTERVENTIONS
	}
	
	public void imporMaterielIntervention(HttpResponse response, HttpEntity entity, DefaultHttpClient client) 
	{
		// DEBUT IMPORT MATERIELINTERVENTION
		try
		{
			//On etablit un lien avec le scirpt PHP
			HttpPost post = new HttpPost(UPDATE_URL_IMPORTMATERIELINTERVENTION);
			post.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			//On recupere le resultat du script
			response = client.execute(post);
			entity = response.getEntity();
			InputStream is = entity.getContent();
			
			//On appeller une fonctione pour traduire le JSON
			String strJson = lireLeJSON(is);
			
			// Creation et alimentation d'un tableau JSON avec la chaine de caracteres
			JSONArray jsonArray = new JSONArray(strJson);
			// Parcours du tableau JSON
			createDialog("Nb Objet MaterielIntervention:", " =" + jsonArray.length());
			for(int j = 0; j < jsonArray.length(); j++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(j);
				createDialog("Test JSON", jsonObject.getString("idIntervention") + jsonObject.getString("nomcommercial") + jsonObject.getString("etat"));
				createDialog("Test JSON Indice Tab", "" + j);
				
				dba.insererMaterielIntervention(Integer.parseInt(jsonObject.getString("idIntervention")),jsonObject.getString("nomcommercial")
						,jsonObject.getString("etat"), jsonObject.getString("conseilTech"),  Integer.parseInt(jsonObject.getString("idMateriel")));
				
			}
			
			is.close();
			if(entity != null)
				entity.consumeContent();					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		// FIN IMPORT MATERIELINTERVENTION
	}
	
	public String lireLeJSON(InputStream is)
	{
		StringBuilder builder = new StringBuilder();
		InputStreamReader isr;
		BufferedReader data;
		
		try
		{
			isr = new InputStreamReader(is);
			data = new BufferedReader(isr);
			String line;
			while((line = data.readLine()) != null)
			{
				builder.append(line);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return builder.toString(); // donnees JSON au format chaine
	} // fin de la methode lireLeJSON
	
	private void read(InputStream in)
	{
		//On traduit le resultat d'un flux
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp;
		
		try
		{
			sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			
			//Cette classe est definie plus bas
			LoginContentHandler uch = new LoginContentHandler();
			xr.setContentHandler(uch);
			xr.parse(new InputSource(in));
		}
		catch (ParserConfigurationException e){ createDialog("READ", "ParserConfigurationException" + e.getMessage());}
		catch (SAXException e){ createDialog("READ", "SAXException" + e.getMessage() + " " + e.getLocalizedMessage());}
		catch (IOException e){ createDialog("READ", "IOException" + e.getMessage()); }
	}
	
	private class LoginContentHandler extends DefaultHandler
	{
		//Classe traitant le message de retour du script PHP
		private boolean in_loginTag = false;
		private int userID;
		private boolean error_occured = false;
		
		public void endElement(String n, String l, String q) throws SAXException
		{ 
			//on renvoie l'id si tout est ok
			if (l == "login")
			{
				in_loginTag = false;
				
				if(!error_occured)
				{
					progressDialog.dismiss();
					// On redirige sur Activite Login afin d'afficher l'ID
					Intent i = new Intent();
					i.putExtra("userid", userID);
					quit(true, i);
				}
			}
		}
		
		@Override
		public void startElement(String uri, String localName, String qName,
				org.xml.sax.Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			if(localName == "login")
				in_loginTag = true;
			if(localName == "error")
			{
				progressDialog.dismiss();
				switch (Integer.parseInt(attributes.getValue("value")))
				{
					case 1:
						createDialog("Error", "Couldn't connect to database");
						break;
					case 2:
						createDialog("Error", "Error in database: table missing");
						break;
					case 3:
						createDialog("Error", "Invalid username and/or password");
						break;
				}
				error_occured = true;
			}
			
			if(localName == "user" && in_loginTag && attributes.getValue("id") != "")
			
			//Dans le cas ou tout se passe bien on recupere l'ID de l'utilisateur
			userID = Integer.parseInt(attributes.getValue("id"));
		}

		public void characters(char ch[], int start, int length){}
		public void startDocument() throws SAXException {}
		public void endDocument() throws SAXException {}
	}
	
	
}
