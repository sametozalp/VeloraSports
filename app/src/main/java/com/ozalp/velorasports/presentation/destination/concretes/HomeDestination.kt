package com.ozalp.velorasports.presentation.destination.concretes

import com.ozalp.velorasports.presentation.destination.abstracts.Destination

object HomeDestination: Destination {
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}