package com.example.various.scope

import com.google.ads.interactivemedia.v3.internal.age
import org.junit.Test

class ApplyTest {
    @Test
    fun applyTest() {
        // applyブロックの中で設定した変数はすぐに使える
        val person = Person().apply {
            name = "John"
            age = 30
            displayName = name
        }
        println(person)
    }
}

class Person {
    var name: String = ""
    var age: Int = 0
    var displayName = ""

    override fun toString(): String {
        return "Person(name='$name', age=$age, displayName='$displayName')"
    }
}
