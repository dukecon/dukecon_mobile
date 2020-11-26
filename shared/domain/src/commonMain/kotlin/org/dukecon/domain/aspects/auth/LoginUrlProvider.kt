package org.dukecon.domain.aspects.auth

interface LoginUrlProvider {
    fun getLoginUrl():String
}