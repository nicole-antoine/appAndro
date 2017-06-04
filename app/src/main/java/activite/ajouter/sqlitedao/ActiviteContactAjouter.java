package activite.ajouter.sqlitedao;

import activite.lister.sqlitedao.ActiviteTousLesContacts;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteContactAjouter extends Activity {
	  EditText nomET, telephoneET;
	  Button sauverBT;
	  Button listerBT;
	  DAODB dba;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_ajouter); //layout contact.xml
        dba = new DAODB(this.getBaseContext()); //this = contexte
        dba.open();
        nomET = (EditText)findViewById(R.id.nom);
        telephoneET = (EditText)findViewById(R.id.telephone);
        sauverBT = (Button)findViewById(R.id.submitButton);
        sauverBT.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    try {
                    	sauvegarderContactDansBD();
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        listerBT = (Button)findViewById(R.id.listerButton);
        listerBT.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                	Intent intent = new Intent(ActiviteContactAjouter.this,ActiviteTousLesContacts.class);
                	ActiviteContactAjouter.this.startActivity(intent);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
    public void sauvegarderContactDansBD(){
    	dba.insererContact(nomET.getText().toString(), 	telephoneET.getText().toString());
    	dba.close();
    	nomET.setText("");
    	telephoneET.setText("");
    	Intent i = new Intent(ActiviteContactAjouter.this, ActiviteTousLesContacts.class);
    	this.startActivity(i);
    }
}

