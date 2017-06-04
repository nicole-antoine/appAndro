package com.example.authentification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.exemple.sqlitedao.data.MenuRedirection;

public class ActiviteLogin extends Activity {

	//Variables pour l'authentification
	
		private TextView tv;
		public static final int RESULT_Main = 1;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
	
			//Appel de la page Login
			startActivityForResult(new Intent(ActiviteLogin.this, ActiviteActionLogin.class), RESULT_Main);
			tv = new TextView(this);
			setContentView(tv);
		}
			
		// Procedure d'authentification
		private void startup(Intent i)
		{
			//Recupere l'identifiant
			int user = i.getIntExtra("userid", -1);
			//Affiche les identifiants de l'utilisateur
			tv.setText("UserID: " + String.valueOf(user) + " logged in");
		}
		
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
		{
			if(requestCode == RESULT_Main && resultCode == RESULT_CANCELED)
				finish();
			else
				startup(data);
			
			if (data.getIntExtra("userid", 0) !=0 )
			{
				Intent intent = new Intent(ActiviteLogin.this, MenuRedirection.class);
				intent.putExtra("idTech", data.getStringExtra("userid"));
        		startActivity(intent);
			}
			else
			{
				Intent intent = new Intent(ActiviteLogin.this, MenuRedirection.class);
        		startActivity(intent);
			}
		}
}
