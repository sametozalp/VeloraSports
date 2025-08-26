package com.ozalp.velorasports.presentation.destination.concretes

import com.ozalp.velorasports.presentation.destination.abstracts.Destination

object RegisterDestination : Destination {
    override val route: String
        get() = "register"
    override val routeWithArgs: String
        get() = route

}