package io.blinktech.prescribedrug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Drug newDrug = new Drug("Amoxicillin");
        DrugDataInterface object = new DrugDataInterface(getApplicationContext());

        try {
            object.result = object.createDrug(newDrug);
            Log.println(Log.ASSERT,TAG, String.valueOf(object.result));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] array = object.getAllDrugs();
        for (String value: array) {
            Log.println(Log.ASSERT,TAG,value);
        }

        //Log.println(Log.ASSERT,TAG,array[0]);

        final AutoCompleteTextView medicines = (AutoCompleteTextView) findViewById(R.id.medicine_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);
        medicines.setAdapter(adapter);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
