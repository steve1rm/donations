package me.androidbox.tamboon.presentation.routers

import me.androidbox.tamboon.data.entities.Charity

interface CharitiesFragmentRouter {
     fun gotoCharitiesFragment(charityList: List<Charity>)
}
