package com.ozalp.velorasports.presentation.destination.concretes

import com.ozalp.velorasports.presentation.destination.abstracts.Destination

object ChooseOrganizationDestination : Destination {
    override val route: String
        get() = "choose_organization"
    override val routeWithArgs: String
        get() = route

}