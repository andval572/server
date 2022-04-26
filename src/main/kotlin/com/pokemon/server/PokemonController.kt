package com.pokemon.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController
class PokemonController {

    var numRequestRecibidas = AtomicInteger(0)

    @GetMapping("hola")
    fun requestHola() : String {
        var numRequestRecibidasLocal = numRequestRecibidas.getAndIncrement()
        println("Me han dicho hola $numRequestRecibidasLocal veces")
        Thread.sleep(4000)
        println("Voy a decir Buenas por vez $numRequestRecibidasLocal")
        return "Buenas $numRequestRecibidasLocal"
    }

    @GetMapping("pokemon")
    fun requestPokemon() : ListaPokemon {
       return listaPokemon

    }
    @GetMapping("pokemonPorNombre/{nombre}")
    fun requestPokemonPorNombre(@PathVariable nombre: String) : ListaPokemon {
        return listaPokemon.buscarPokemonPorNombre(nombre)

    }
    @GetMapping("pokemonPorTipo/{tipo}")
    fun requestPokemonPorTipo(@PathVariable tipo: String) : ListaPokemon {
        return listaPokemon.buscarPokemonPorTipo(tipo)
    }
    @GetMapping("pokemonMasBajo")
    fun requestPokemonMasBajo() : Pokemon {
        return listaPokemon.buscarPokemonMasBajo()
    }
    @GetMapping("pokemonMasAlto")
    fun requestPokemonMasAlto() : Pokemon {
        return listaPokemon.buscarPokemonMasAlto()
    }
}