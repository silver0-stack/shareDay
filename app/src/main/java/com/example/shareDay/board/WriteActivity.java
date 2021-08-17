package com.example.shareDay.board;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareDay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class WriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Board> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference mStore;

    private ImageButton wrtie_upload_button;
    private TextView write_name_text;
    private EditText write_contents_text;


//수정

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpme_write_activity);

        database = FirebaseDatabase.getInstance(); //파이어 베이스 데이터 베이스 연동
        mStore = database.getReference();//데이터베이스 테이블 연
        readBoard();

        wrtie_upload_button=findViewById(R.id.write_upload_button);
        write_contents_text=findViewById(R.id.write_contents_text);
        write_name_text=findViewById(R.id.write_name_text);

       ///write_name_text.setText();



        wrtie_upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=write_name_text.getText().toString();
                String getContents=write_contents_text.getText().toString();

                HashMap result=new HashMap<>();
                result.put("contents",getContents);
               result.put("name",getName);


               writeNewUser(getName,getContents);

                Intent intent2=new Intent(WriteActivity.this, WriteList.class);
                startActivity(intent2);

            }

        });




    }
    private void writeNewUser(String name, String contents){
        Board board=new Board(contents,null, name );
        mStore.child("Board").push().setValue(board);

    }


    private void readBoard(){
        mStore.child("Board").push().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue(Board.class) != null){
                    Board board=snapshot.getValue(Board.class);
                    
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

