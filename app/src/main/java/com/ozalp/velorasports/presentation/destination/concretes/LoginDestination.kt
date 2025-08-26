package com.ozalp.velorasports.presentation.destination.concretes

import com.ozalp.velorasports.presentation.destination.abstracts.Destination

object LoginDestination : Destination {
    override val route: String
        get() = "login"
    override val routeWithArgs: String
        get() = route

}