package com.paulo.seleadclass

import kotlinx.coroutines.delay
import java.lang.Exception
import kotlin.random.Random

class MainRepository {

    suspend fun getData(): Resource<List<Person>> {
        return try {
            delay(2000L)
            if(Random.nextInt(2) == 0){
                throw Exception()
            }
            Resource.Success(
                listOf(
                    Person(
                        "Paulo Oliveira",
                        "Developer"
                    ),
                    Person(
                        "Renata Oliveira",
                        "Policia"
                    ),
                    Person(
                        "Malu Oliveira",
                        "Estudante"
                    ),
                    Person(
                        "Bruna Oliveira",
                        "Estudante"
                    ),
                )
            )
        } catch (e: Exception) {
            Resource.Error("Network Error")
        }

    }
}