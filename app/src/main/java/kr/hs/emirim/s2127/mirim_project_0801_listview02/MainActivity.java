package kr.hs.emirim.s2127.mirim_project_0801_listview02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    EditText edit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listv = findViewById(R.id.listv);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listv.setAdapter(adapter);
        edit1 = findViewById(R.id.edit1);
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(btnAddListener);
        listv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("삭제여부");
                dlg.setMessage("정말로 삭제하시겠습니까?");
                dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        items.remove(index);
                        adapter.notifyDataSetChanged();
                    }
                });
                dlg.setNegativeButton("취소",null);
                dlg.show();
                return false;
            }
        });
    }

    View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            items.add(edit1.getText().toString());
            adapter.notifyDataSetChanged();
            edit1.setText("");
        }
    };
}