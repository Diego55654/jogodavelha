package com.conexcacao2.jogo_da_velha_diego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conexcacao2.jogo_da_velha_diego.databinding.ActivityMenuMaquinaBinding

class MenuMaquinaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuMaquinaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuMaquinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando o botão para abrir MainActivity1
        binding.buttonFacil.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Configurando o botão para abrir MainActivity2
        binding.buttonDificil.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}