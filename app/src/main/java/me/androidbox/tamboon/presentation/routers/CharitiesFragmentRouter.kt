package me.androidbox.tamboon.presentation.routers

import me.androidbox.tamboon.data.entities.Charity

interface CharitiesFragmentRouter {
     fun goToCharitiesFragment(charityList: List<Charity>)
}
