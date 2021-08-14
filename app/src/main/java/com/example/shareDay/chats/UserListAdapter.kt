package com.example.shareDay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.example.shareDay.chats.UserInfo
import java.util.ArrayList


class UserListAdapter(val listData: List<UserInfo>, val clickListener: ClickListener):RecyclerView.Adapter<UserListAdapter.MyViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHoler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)

        return MyViewHoler(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHoler(view: View): RecyclerView.ViewHolder(view){
        var name_text: TextView

        init{
            name_text = view.findViewById(R.id.name_text)
        }
    }

    override fun onBindViewHolder(holder: MyViewHoler, position: Int) {
        holder.name_text.text = listData.get(position).name
        holder.itemView.setOnClickListener({
            clickListener.onItemClick(listData.get(position))
        })
    }

    interface ClickListener{
        fun onItemClick(dataModel: UserInfo)
    }

}
