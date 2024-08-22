import br.com.alura.alugames.modelo.Gamer

fun main() {
    val gamer1 = Gamer("MikeX", "as@cd.com")
    println(gamer1)

    val gamer2 = Gamer("Juca",
        "ju@dsa.com",
        "19/04/2003",
        "jucX")

    println(gamer2)

    gamer1.let {
        it.dataNascimento = "23/07/1984"
        it.usuario = "JackSkyWalker"
        it.idInterno = "JackSkyWalker1234"
    }

    println(gamer1)
}