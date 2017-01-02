package com.example.admin.database_listviewex1;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    EditText ed1,ed2;
    Button b;
    ListView lv;
    //LAST INITIALIZE
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    MyDatabase myDatabase;


    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase=new MyDatabase(getActivity());
        myDatabase.open();//open after (super.)
    }
//step 2 for close
    @Override
    public void onDestroy() {
        myDatabase.close();//close before (super.) method
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_one, container, false);
        //2nd step
        ed1= (EditText) view.findViewById(R.id.et1);
        ed2= (EditText) view.findViewById(R.id.et2);
        lv= (ListView) view.findViewById(R.id.listview);
        b= (Button) view.findViewById(R.id.button1);


        //4==CODES ARE DISPLAYING DATABASE TABLE INFORMATION ON LISTVIEW
        //4==1=get cursor from table
        cursor=myDatabase.queryStudent();
        //4==2=establish link between cursor and cursor adapter
        //prepare a roe xml before link
        simpleCursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.row, cursor, new String[]{"_id","sname","ssub"},
                new int[]{R.id.textview1,R.id.textView2,R.id.textView3});//_id to textview1,sname=textview2,ssub=textview3
        //4==3=establish link between cursor adapter to listview
        lv.setAdapter(simpleCursorAdapter);
        //handling listview click items
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                int sno=cursor.getInt(0);
                String sname=cursor.getString(1);
                String ssub=cursor.getString(2);
                Toast.makeText(getActivity(), ""+sno+"-"+sname+"-"+ssub, Toast.LENGTH_SHORT).show();
            }
        });





        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1program starts after initializing (step1)
            String sname=ed1.getText().toString();
                String ssub=ed2.getText().toString();
                myDatabase.insertStudent(sname,ssub);
                cursor.requery();
                Toast.makeText(getActivity(), "inserted a row", Toast.LENGTH_SHORT).show();
                ed1.setText("");
                ed2.setText("");
                ed1.requestFocus();
            }
        });


        return view;
    }

}
