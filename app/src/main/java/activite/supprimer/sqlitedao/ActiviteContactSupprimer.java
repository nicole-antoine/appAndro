package activite.supprimer.sqlitedao;

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

public class ActiviteContactSupprimer extends Activity {
	  EditText nomET, telephoneET, idET;
	  Button sauverBT;
	  Button listerBT;
	  DAODB dba;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_supprimer); //layout contact_supprimer.xml
        dba = new DAODB(this.getBaseContext()); //this = contexte
        dba.open();
        idET = (EditText)findViewById(R.id.id_supprimer);
        nomET = (EditText)findViewById(R.id.nom_supprimer);
        telephoneET = (EditText)findViewById(R.id.telephone_supprimer);
        sauverBT = (Button)findViewById(R.id.submit_supprimer);
        sauverBT.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    try {
                    	supprimerContactDansBD();
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        listerBT = (Button)findViewById(R.id.lister_supprimer);
        listerBT.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                	Intent intent = new Intent(ActiviteContactSupprimer.this,ActiviteTousLesContacts.class);
                	ActiviteContactSupprimer.this.startActivity(intent);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
    public void supprimerContactDansBD(){
    	dba.supprimerUnContact(Integer.parseInt(idET.getText().toString()));
    	dba.close();
    	idET.setText("");
    	nomET.setText("");
    	telephoneET.setText("");
    	Intent i = new Intent(ActiviteContactSupprimer.this, ActiviteTousLesContacts.class);
    	this.startActivity(i);
    }
}