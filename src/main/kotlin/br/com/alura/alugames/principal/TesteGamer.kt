import br.com.alura.alugames.modelo.Gamer

fun main() {
    val gamer1 = Gamer("Juca", "as@cd.com")
    println(gamer1)

    val gamer2 = Gamer("Juca",
        "ju@dsa.com",
        "19/04/2003",
        "jucX")

    println(gamer2)

    gamer1.let {
        it.dataNascimento = "23/07/1984"
        it.usuario = "JackSkyWalker"
    }.also {
        println(gamer1.idInterno)
    }

    gamer1.usuario = "jack"

    println(gamer1)
}