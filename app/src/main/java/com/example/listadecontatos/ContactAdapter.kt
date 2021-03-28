package com.example.listadecontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Classe que vai gerenciar a lista (Adapter)
class ContactAdapter(var listener: ClickItemContactListener) : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf()  // Armazena a lista de contatos
// Cria cada item visual (view) da tela
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    override fun getItemCount(): Int {  // retorna o tamanhod lista
        return list.size
    }
//  Passa por cada item do array, obtém o item e preenche na lista do Recycler View
    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }
// Passa a lista para o Adapter
    fun updateList(list: List<Contact>) {
    this.list.clear()
    this.list.addAll(list)
    notifyDataSetChanged()  // notifica o adapter que houve mudança
}

// Classe que gerencia cada item da lista
    class ContactAdapterViewHolder(ItemView: View, var list: List<Contact>, var listener: ClickItemContactListener) : RecyclerView.ViewHolder(ItemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        private val tvPhotograph: ImageView = itemView.findViewById(R.id.iv_photograph)

        init {
            itemView.setOnClickListener {
                listener.clickItemContact(list[adapterPosition])
            }
        }
// Recebe os dados da classe Contact
        fun bind(contact: Contact) {
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }
    }
}