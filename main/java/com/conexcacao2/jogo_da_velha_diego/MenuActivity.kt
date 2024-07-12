package com.conexcacao2.jogo_da_velha_diego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conexcacao2.jogo_da_velha_diego.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando o botão para abrir MainActivity1
        binding.botaoMultiplayer.setOnClickListener {
            val intent = Intent(this, MenuMaquinaActivity::class.java)
            startActivity(intent)
        }

        // Configurando o botão para abrir MainActivity2
        binding.botaoMaquina.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}