package br.com.alura.alugames.principal
import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.servicos.ConsumoApi
import org.example.br.com.alura.alugames.modelo.Jogo
import transformarEmIdade
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso! Dados do gamer: $gamer")
    println("Idade do gamer: " +gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite um codigo de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb)
        }

        resultado.onFailure {
            println("Jogo inexistente!! Tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descricao personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descricao personalizada para o jogo")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()


    } while (resposta.equals("S", true))

    println("Jogos buscados: ")
    println(gamer.jogosBuscados)

    println("\n Jogos por titulos:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach{
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("Jogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if(opcao.equals("s", true)) {
        println(gamer.jogosBuscados)
        println("Informe a posicao do jogo que deseja excluir")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }
    println("\n Lista atualizada: ")
    println(gamer.jogosBuscados)


    println("Busca finalizada com sucesso!")
}