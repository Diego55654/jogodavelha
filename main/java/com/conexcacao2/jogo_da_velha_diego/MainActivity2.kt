package com.conexcacao2.jogo_da_velha_diego

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.conexcacao2.jogo_da_velha_diego.databinding.ActivityMain2Binding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    val tabuleiro = arrayOf(
        arrayOf("", "", ""),
        arrayOf("", "", ""),
        arrayOf("", "", "")
    )

    var jogadorAtual = "X"
    var dificuldade = "facil" // Pode ser "facil" ou "dificil"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun buttonClick(view: View) {
        val buttonSelecionado = view as Button

        when(buttonSelecionado.id){
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }

        buttonSelecionado.setBackgroundResource(R.drawable.estrela)
        buttonSelecionado.isEnabled = false

        var vencedor = verificaVencedor(tabuleiro)

        if (!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        if (vencedor.isNullOrBlank()) {
            if (dificuldade == "dificil") {
                val bloqueado = bloquearJogador(tabuleiro)
                if (!bloqueado) {
                    jogadaAleatoria()
                }
            } else {
                jogadaAleatoria()
            }
        }

        vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
            Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != "") {
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != "") {
                return tabuleiro[0][i]
            }
        }

        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != "") {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != "") {
            return tabuleiro[0][2]
        }

        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor == "X" || valor == "O"){
                    empate++
                }
            }
        }
        if(empate == 9){
            return "Empate"
        }
        return null
    }

    fun jogadaAleatoria() {
        var rX: Int
        var rY: Int
        do {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)
        } while (tabuleiro[rX][rY] == "X" || tabuleiro[rX][rY] == "O")
        tabuleiro[rX][rY] = "O"
        atualizarTabuleiro(rX, rY)
    }

    fun bloquearJogador(tabuleiro: Array<Array<String>>): Boolean {
        // Verifica linhas
        for (i in 0 until 3) {
            if (tabuleiro[i].count { it == "X" } == 2 && tabuleiro[i].count { it == "" } == 1) {
                val j = tabuleiro[i].indexOf("")
                tabuleiro[i][j] = "O"
                atualizarTabuleiro(i, j)
                return true
            }
        }
        // Verifica colunas
        for (i in 0 until 3) {
            val coluna = arrayOf(tabuleiro[0][i], tabuleiro[1][i], tabuleiro[2][i])
            if (coluna.count { it == "X" } == 2 && coluna.count { it == "" } == 1) {
                val j = coluna.indexOf("")
                tabuleiro[j][i] = "O"
                atualizarTabuleiro(j, i)
                return true
            }
        }

        // Verifica diagonais
        val diagonal1 = arrayOf(tabuleiro[0][0], tabuleiro[1][1], tabuleiro[2][2])
        if (diagonal1.count { it == "X" } == 2 && diagonal1.count { it == "" } == 1) {
            val i = diagonal1.indexOf("")
            tabuleiro[i][i] = "O"
            atualizarTabuleiro(i, i)
            return true
        }

        val diagonal2 = arrayOf(tabuleiro[0][2], tabuleiro[1][1], tabuleiro[2][0])
        if (diagonal2.count { it == "X" } == 2 && diagonal2.count { it == "" } == 1) {
            val i = diagonal2.indexOf("")
            tabuleiro[i][2 - i] = "O"
            atualizarTabuleiro(i, 2 - i)
            return true
        }

        return false
    }

    fun atualizarTabuleiro(i: Int, j: Int) {
        val posicao = i * 3 + j
        Handler(Looper.getMainLooper()).postDelayed({
            when (posicao) {
                0 -> {
                    binding.buttonZero.setBackgroundResource(R.drawable.lua2)
                    binding.buttonZero.isEnabled = false
                }
                1 -> {
                    binding.buttonUm.setBackgroundResource(R.drawable.lua2)
                    binding.buttonUm.isEnabled = false
                }
                2 -> {
                    binding.buttonDois.setBackgroundResource(R.drawable.lua2)
                    binding.buttonDois.isEnabled = false
                }
                3 -> {
                    binding.buttonTres.setBackgroundResource(R.drawable.lua2)
                    binding.buttonTres.isEnabled = false
                }
                4 -> {
                    binding.buttonQuatro.setBackgroundResource(R.drawable.lua2)
                    binding.buttonQuatro.isEnabled = false
                }
                5 -> {
                    binding.buttonCinco.setBackgroundResource(R.drawable.lua2)
                    binding.buttonCinco.isEnabled = false
                }
                6 -> {
                    binding.buttonSeis.setBackgroundResource(R.drawable.lua2)
                    binding.buttonSeis.isEnabled = false
                }
                7 -> {
                    binding.buttonSete.setBackgroundResource(R.drawable.lua2)
                    binding.buttonSete.isEnabled = false
                }
                8 -> {
                    binding.buttonOito.setBackgroundResource(R.drawable.lua2)
                    binding.buttonOito.isEnabled = false
                }
            }
        }, 200)
    }
}