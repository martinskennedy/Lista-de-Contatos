package com.example.listadecontatos

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

// Nova tela
class DetailActivity : AppCompatActivity() {
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_detail)

        initToobar()
        getExtras()
        bindViews()
    }
// Adiciona botão de voltar na Toobar
    private fun initToobar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // exibe o botão voltar na Toolbar
    }
// Obtém os dados da tela anterior
    private fun getExtras() {
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }
// Setar os dados na tela
    private fun bindViews() {
        findViewById<TextView>(R.id.tv_name).text = contact?.name
        findViewById<TextView>(R.id.tv_phone).text = contact?.phone
    }
// Cria chave/valor
    companion object {
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }
// Metodo para que o botão voltar realize a ação de voltar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}