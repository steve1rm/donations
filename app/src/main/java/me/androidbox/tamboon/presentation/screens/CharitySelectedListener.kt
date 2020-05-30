package me.androidbox.tamboon.presentation.screens

import me.androidbox.tamboon.data.entities.Charity

interface CharitySelectedListener {
    fun onCharitySelected(charity: Charity)
}