package com.example.shareDay

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.Chat
import com.example.shareDay.chats.ProfilePopUp

//1:1채팅방 Activity에 recyclerView를 연결하는 Adapter
class ChatAdapter(chatData: MutableList<Chat>?, userName: String, chatName: String) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    private val chatList = chatData
    private val name: String = userName
    private val chatName: String = chatName

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView
        var msgText: TextView
        var msgLinear: LinearLayout
        var msgTime: TextView
        var rootView: View

        lateinit var chatTextBox: TextView

        init {
            nameText = itemView.findViewById(R.id.nameText)
            msgText = itemView.findViewById(R.id.msgText)
            msgLinear = itemView.findViewById(R.id.msgLinear)
            msgTime = itemView.findViewById(R.id.msgTime)
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
            holder.msgTime.textAlignment = View.TEXT_ALIGNMENT_VIEW_END

            holder.msgLinear.gravity = Gravity.RIGHT
            //holder.msgTime.gravity = Gravity.RIGHT

            holder.msgText.setBackgroundResource(R.drawable.edit_textbox_me)
            holder.msgText.setTextColor(Color.parseColor("#000000"))

        } else {
            //아닐 시 왼쪽 정렬
            holder.nameText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            holder.msgText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            holder.msgTime.textAlignment = View.TEXT_ALIGNMENT_VIEW_START

            holder.msgLinear.gravity = Gravity.LEFT
            //holder.msgTime.gravity = Gravity.LEFT

            holder.msgText.setBackgroundResource(R.drawable.edit_textbox_you)
            holder.msgText.setTextColor(Color.parseColor("#ffffff"))
        }

        holder.nameText.setOnClickListener{
            val intent = Intent(holder.itemView?.context, ProfilePopUp::class.java)
            intent.putExtra("chatName", chatName)
            intent.putExtra("userName", name)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
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

    interface ClickListener{
        //fun onItemClick(dataModel: UserInfo)
        fun onItemClick()
    }
    private var listener : ClickListener? = null
    fun setOnItemClickListener(listener : ClickListener) {
        this.listener = listener
    }

}
