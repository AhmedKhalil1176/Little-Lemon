package com.ahmed.littlelemoncapstone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MenuViewModel (application: Application) : AndroidViewModel(application){
    private val database : AppDatabase

    init {
        database = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    fun getAllDatabaseMenuItems() : LiveData<List<MenuItemRoom>>{
        return database.menuItemDao().getAll()
    }

    fun fetchMenuDataIfNeeded(){
        viewModelScope.launch (IO){
            if(database.menuItemDao().isEmpty()){
                saveMenuToDatabase(
                    database,
                    fetchMenu("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                )
            }
        }
    }

     fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map{it.toMenuItemRoom()}
         database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }

    private suspend fun fetchMenu(url :String): List<MenuItemNetwork>{
        val response = client.get(url)
        val menuData : MenuNetworkData = response.body()
        val menuItems = menuData.menu
        return menuItems
    }

}