package com.example.shareDay

import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.Chat


class ChatAdapter(chatData: MutableList<Chat>?, name: String) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    private val chatList = chatData
    private val name: String = name

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView
        var msgText: TextView
        var msgLinear: LinearLayout
        var rootView: View

        init {
            nameText = itemView.findViewById(R.id.nameText)
            msgText = itemView.findViewById(R.id.msgText)
            msgLinear = itemView.findViewById(R.id.msgLinear)
            rootView = itemView
            itemView.isEnabled = true
            itemView.isClickable = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //inflation 과정
        val linearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.msg_item, parent, false) as LinearLayout
        return MyViewHolder(linearLayout)
    }

    //각 뷰의 기능 설정
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chat: Chat = chatList!![position]
        holder.nameText.setText(chat.name)
        holder.msgText.setText(chat.msg)
        if (chat.name.equals(name)) {
            //사용자가 저장된 이름과 같을 시 오른쪽으로 정렬
            holder.nameText.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            holder.msgText.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            holder.msgLinear.gravity = Gravity.RIGHT
        } else {
            //아닐 시 왼쪽 정렬
            holder.nameText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            holder.msgText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            holder.msgLinear.gravity = Gravity.LEFT
        }
    }

    //메시지아이템 갯수세기
    override fun getItemCount(): Int {
        return chatList?.size ?: 0
    }

    //메시지아이템의 추가 및 적용
    fun addChat(chat: Chat) {
        chatList!!.add(chat)
        notifyItemInserted(chatList.size - 1)
    }

    init {
        //MainActivity.java에서 받은 데이터들을 저장
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
