package com.example.chatappfirebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatappfirebase.ChatLogActivity
import com.example.chatappfirebase.ChatMessage
import com.example.chatappfirebase.R
import com.example.chatappfirebase.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.item_chat_log_left.view.*
import kotlinx.android.synthetic.main.item_chat_log_right.view.*

class ChatLogAdapter(val list: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> return ViewHolderChatRight.parent(parent)
            else -> return ViewHolderChatLeft.parent(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
//        reference.child("/users").get
//        if (list[position].uid = )
        return 1
    }
    class ViewHolderChatRight(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun parent(parent: ViewGroup): ViewHolderChatRight {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_log_right,parent,false)
                return ViewHolderChatRight(view)
            }
        }
        fun bindChatRight(chat: ChatMessage) {
            itemView.chat_log_right_edittext.text = chat.text
        }
    }
    class ViewHolderChatLeft(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun parent(parent: ViewGroup): ViewHolderChatLeft {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_log_left,parent,false)
                return ViewHolderChatLeft(view)
            }
        }
        fun bindChatLeft(chat: ChatMessage) {
            itemView.chat_log_left_edittext.text = chat.text
        }
    }
}                                                                                                                                                                                                                                                                                                                                                                     