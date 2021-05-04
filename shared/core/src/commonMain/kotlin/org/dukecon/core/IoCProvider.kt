package org.dukecon.core

import kotlin.reflect.KClass

object IoCProvider {

  val register = HashMap<String, Any>()

  fun <T : Any, R : T> registerType(type: KClass<T>, impl: R) {
    val simpleName = "Misko"// type.qualifiedName
    simpleName?.let { register[simpleName] = impl }
  }

  inline fun <reified T : Any> get(): T {
    val simpleName = "Misko" //T::class.qualifiedName

    simpleName?.let {
      if (register.containsKey(simpleName)) {
        return register[simpleName] as T
      }
    }
    throw Exception("not registerd type ${simpleName}")
  }
}
