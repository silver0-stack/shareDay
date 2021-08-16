package com.example.shareDay

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
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

        holder.itemView.setOnClickListener {
            listener?.onItemClick()

            val intent = Intent(holder.itemView?.context, ChatActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
            //intent.putExtra("", value)
            //startActivity(intent)
        }

    }

    interface ClickListener{
        //fun onItemClick(dataModel: UserInfo)
        fun onItemClick()
    }
    private var listener : ClickListener? = null
    fun setOnItemClickListener(listener : ClickListener) {
        this.listener = listener
    }
}
