enum class Nivel { BASICO, INTERMEDIARIO, AVANÇADO }

// Representa um usuario/aluno
data class Usuario(val nome: String, val email: String)

// Representa um conteudo educacional
data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

// Representa uma formacao composta por conteudos e alunos inscritos
data class Formacao(
    val nome: String, 
    val nivel: Nivel,
    val conteudos: List<ConteudoEducacional>
) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (usuario !in inscritos) {
            inscritos.add(usuario)
            println("Usuário ${usuario.nome} matriculado na $nome")
        } else {
            println("Usuário ${usuario.nome} já está matriculado na $nome")
        }
    }

    fun cargaHorariaTotal(): Int = conteudos.sumOf {it.duracao}
}

fun main() {
    // Criando conteudos
    val kotlinBasico = ConteudoEducacional("Kotlin Básico", 90)
    val kotlinAvancado = ConteudoEducacional("Kotlin Avançado", 120)
    val springBoot = ConteudoEducacional("Spring Boot", 100)

    // Criando Formacao
    val formacaoKotlin = Formacao(
        nome = "Formação Kotlin Developer",
        nivel = Nivel.INTERMEDIARIO,
        conteudos = listOf(kotlinBasico, kotlinAvancado, springBoot)
    )

    // Criando usuarios
    val usuario1 = Usuario("Leonardo", "leonardo@email.com") 
    val usuario2 = Usuario("Mariana", "mariana@email.com")

    // Matriculando usuarios
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)
    formacaoKotlin.matricular(usuario1) // teste de duplicidade

    // Exibindo informacoes
    println("\nFormação: ${formacaoKotlin.nome}")
    println("Nível: ${formacaoKotlin.nivel}")
    println("Carga horária total: ${formacaoKotlin.cargaHorariaTotal()} minutos")
    println("Conteúdos: ${formacaoKotlin.conteudos.map { it.nome }}")
    println("Inscritos: ${formacaoKotlin.inscritos.map { it.nome }}")
}