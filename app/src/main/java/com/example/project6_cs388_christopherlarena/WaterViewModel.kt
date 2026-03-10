package com.example.project6_cs388_christopherlarena

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WaterViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = WaterDatabase.getDatabase(application).waterDao()
    val allEntries: LiveData<List<WaterEntry>> = dao.getAllEntries()

    fun insert(entry: WaterEntry) = viewModelScope.launch {
        dao.insert(entry)
    }
}
