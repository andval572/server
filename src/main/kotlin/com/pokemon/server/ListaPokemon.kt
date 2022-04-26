package com.pokemon.server

import java.io.File


class ListaPokemon(var listaPokemon : MutableList<Pokemon> = mutableListOf()) {

    companion object{
        const val filePath = "pokemon.json"
        fun fileExist() : Boolean{
            return File(filePath).exists()
        }
        fun cargarListaPokemonDeFichero() : ListaPokemon {
            val lista = gson.fromJson(File(filePath).readText(), ListaPokemon::class.java)
            return lista
        }
    }



    fun agregar(pokemon: Pokemon) {
        listaPokemon.add(pokemon)
    }

    fun imprimirPokemons(){
        if (listaPokemon.isEmpty()) {
            println("No se ha encontrado a ese Pokémon")
        } else {
            listaPokemon.forEach {
                println(it.decirNombreYTipo())
            }
        }
    }

    fun buscarPokemonPorNombre(nombreBuscado : String) : ListaPokemon {
        // TODO muéstrame al Pokemon que las letras buscadas incluidas en su nombre. Si no hay, dime que no hay
        val listaFiltrada = listaPokemon.filter {
            it.name.contains(nombreBuscado)
        }

        return ListaPokemon(listaFiltrada.toMutableList())
    }

    fun buscarPokemonPorTipo(tipoBuscado : String) : ListaPokemon {
        // TODO muéstrame todos los pokemon de ese tipo. Si no hay, dime que no hay

        val listaFiltrada = listaPokemon.filter { pokemon ->
            var encontrado = false
            pokemon.types.forEach {  tipo ->
                if (tipo.type.name == tipoBuscado)
                    encontrado = true
            }
            encontrado
        }

        return ListaPokemon(listaFiltrada.toMutableList())
    }

    fun buscarPokemonMasBajo() :Pokemon {

        val listaFiltrada = listaPokemon.sortedBy {
            it.height
        }

        return listaFiltrada[0]
    }
    fun buscarPokemonMasAlto() :Pokemon {

        val listaFiltrada = listaPokemon.sortedBy {
            it.height
        }

        return listaFiltrada[listaFiltrada.size-1]
    }

    fun guardarFichero(){
        val file = File(filePath)
        file.writeText(gson.toJson(this))
    }
}