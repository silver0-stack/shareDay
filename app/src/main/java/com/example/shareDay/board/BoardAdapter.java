package com.example.shareDay.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareDay.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {

    private ArrayList<Board> arrayList;  //보드의 정보를 담고 있는 리스트
    private Context context; //선택된 액티비티의 정보를 가져올때 사용

    public BoardAdapter(ArrayList<Board> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //실제 리스트 뷰가 어댑터에 연결된 후 뷰 홀더 만들어냄
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.helpme_item_main,parent,false);
        BoardViewHolder holder=new BoardViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {//뷰 홀더에 정보 넣어주기
        //Glide.with(holder.itemView)
       //         .load(arrayList.get(position).getProfile())
       //        .into(holder.item_profile);
        holder.item_id_text.setText(arrayList.get(position).getId());
        holder.item_name_text.setText(arrayList.get(position).getName());
        holder.item_contents_text.setText(arrayList.get(position).getContents());


    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0); //리스트뷰가 널이 아니면 리턴 삼항 연산자
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {  //보드뷰홀

      //  ImageView item_profile;
        TextView item_name_text;
        TextView item_contents_text;
        TextView item_id_text;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.item_profile=itemView.findViewById(R.id.item_profile);
            this.item_name_text=itemView.findViewById(R.id.item_name_text);
            this.item_contents_text=itemView.findViewById(R.id.item_contents_text);
            this.item_id_text=itemView.findViewById(R.id.item_id_text);
        }

    }
}
