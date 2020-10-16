package com.example.sqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add,viewAll;
    EditText name,age;
    Switch activeCustomer;
    ListView list;
    //ArrayAdapter customerArrayAdapter;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.btn_add);
        viewAll=findViewById(R.id.view_all);
        name=findViewById(R.id.edit_text_name);
        age=findViewById(R.id.age);
        activeCustomer=findViewById(R.id.switch1);
        list=findViewById(R.id.customer_list);
        databaseHelper=new DatabaseHelper(MainActivity.this);
        showCustomerOnListView(databaseHelper);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;
                try {
                    customerModel=new CustomerModel(2,Integer.parseInt(age.getText().toString()),name.getText().toString(),activeCustomer.isChecked());
                    Toast.makeText( MainActivity.this,customerModel.toString(),Toast.LENGTH_SHORT).show();
                    name.getText().clear();
                    age.getText().clear();

                }
                catch (Exception e){
                    Toast.makeText( MainActivity.this,"Please create a Customer",Toast.LENGTH_SHORT).show();
                    customerModel= new CustomerModel(-1,0,"error",false);
                }
                DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addOne(customerModel);
               // Toast.makeText(MainActivity.this,"Success "+success,Toast.LENGTH_LONG).show();
                //showCustomerOnListView(databaseHelper);

            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
                showCustomerOnListView(databaseHelper);
                name.getText().clear();
                age.getText().clear();
                //Toast.makeText( MainActivity.this,everyone.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                CustomerModel clickedCustomer=(CustomerModel) parent.getItemAtPosition(position);
                databaseHelper.deleteOne(clickedCustomer);
                showCustomerOnListView(databaseHelper);
                Toast.makeText(MainActivity.this, clickedCustomer.toString()+" Deleted",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showCustomerOnListView(DatabaseHelper databaseHelper2) {
      ArrayAdapter  customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper2.getEveryOne());
        list.setAdapter(customerArrayAdapter);
    }
}
