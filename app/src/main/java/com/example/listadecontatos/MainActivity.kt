package com.example.listadecontatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecontatos.DetailActivity.Companion.EXTRA_CONTACT

/*
- Criar classe ContactAdapter
- Criar a classe ContactAdapterViewHolder dentro do ContactAdapter
- Criar Classe Contact
- Criar variavel list do tipo MutableList dentro de ContactAdapter
- Criar layout contact_item.xml como card view
- Inflar o layout contact_item onCreateViewHolder no Contact Adapter
- Criar as variáveis dentro do ContactAdapterViewHolder em Contact Adapter
- Popular os dados no bind dentro de ContactAdapter
- Criar metodo updateList dentro de ContactAdapter
- Criar val rvList em Main Activity
- Declarar o Recycler View na activity_main
- Declara o adapter na Main Activity
- Criar metodo bindView na Main Activity
- Criar metodo updateLista na Main Activity

- IMPLEMENTAR MENU
- Criar o menu.xml
- Criar o metodo onCreateOptionsMenu na Main Activity
- Criar um metodo para o Toast

- IMPLEMENTAR UM DRAWER LAYOUT
- Criar drawer_menu.xml
- Mudar o theme adicionando ao final NoActionBar
- Adicionar a Toolbar na activity_main
- Criar metodo initDrawer na Main Activity
- Mudar no onCreate o setContentView(R.layout.activity_main) para setContentView(R.layout.drawer_menu)
- Criar 2 strings no strings.xml Abrir e Fechar o Drawer

CRIANDO NOVAS TELAS
- Criar uma nova Empty Activity com o nome ContactDetail
- Criar o xml dela
- Adicionar a nova activity no manifest
- Criar o layout Contact_detail
- Criar uma INTERFACE ClickItemContactListener
- Implementar ela na Main Activity
- Criar o metodo override fun clickItemContact na Main Activity
- Alterar a Classe Contact para Parselize para pode navegar entre as telas
- Na classe ContactAdapter passar o parametro - var listener: ClickItemContactListener
- Na classe Contact Adapter, mudar a classe para ficar desta forma
  class ContactAdapterViewHolder(ItemView: View, var list: List<Contact>, var listener: ClickItemContactListener) : RecyclerView.ViewHolder(ItemView)
- Na classe Contact Adapter em override fun onCreateViewHolder o retorno fica assim
  return ContactAdapterViewHolder(view, list, listener)
- Na Main Activity acrescentar no adapter o this
  private val adapter = ContactAdapter(this)   ATE AQUI TUDO OK
- Criar na classe Detail Activity o companion object
- Inserir no metodo da Main Activity override fun clickItemContact a linha
  intent.putExtra(EXTRA_CONTACT, contact), importando o EXTRA_CONTACT
- Criar metodo na DetailActivity para a Toolbar
- Implementar uma toolbar na contact_detail

 */
class MainActivity : AppCompatActivity(), ClickItemContactListener{
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }

    private val adapter = ContactAdapter(this)  // declaração do adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        bindView()
        updateLista()
    }
// Inicializa o Drawer
    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

// ação de abrir e fechar o drawer
        val toogle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
    }

    private fun bindView() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun updateLista() {
        adapter.updateList(
            arrayListOf(  // cria uma lista mockada
                Contact(
                    "Pedro da Silva",
                    "(11) 8876-6345",
                    "img.png"
                ),
                Contact(
                    "Maria da Silva",
                    "(11) 2456-8765",
                    "img.png"
                )
            )
        )
    }
// Metodo para mostrar um Toast na tela
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

// Cria o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
// Captura cada click no menu pelo ID
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.item_menu_1 -> {
                showToast("Exibindo item de menu 1")
                true
            }
            R.id.item_menu_2 -> {
                showToast("Exibindo item de menu 2")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
// Quando tocar nos itens da lista abre a nova janela activity
    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}