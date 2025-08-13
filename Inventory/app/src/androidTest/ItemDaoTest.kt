package com.example.inventory

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemDaoTest {
    private lateinit var itemDao: ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
}

@Before
fun createDb() {
    val context: Context = ApplicationProvider.getApplicationContext()
    // using and in-memory database because the information stored here disappears when the process is killed
    inventoryDatabase = Room.inMemoryDatabaseBuilder(context. InventoryDatabase::class.java)
        .allowMainThreadQueries()
        .build()
    itemDao = inventoryDatabase.itemDao()
}