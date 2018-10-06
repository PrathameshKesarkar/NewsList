package co.pratham.newslist.data.local

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import co.pratham.newslist.data.repository.NewsLocalSource
import co.pratham.newslist.helper.NewsFactory
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsLocalSourceImplTest {
    lateinit var localSource: NewsLocalSource
    lateinit var database: NewsDatabase
    lateinit var preferenceHelper: PreferenceHelper

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), NewsDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        preferenceHelper = PreferenceHelper(InstrumentationRegistry.getContext())
        localSource = NewsLocalSourceImpl(preferenceHelper, database.newsDAO())
    }

    @After
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun saveNewsCollection() {
        //Create fake data
        val newsCollection = NewsFactory.makeFakeNews()

        //Save data to Database
        localSource.saveNewsCollecction(newsCollection).test()
        //Fetch data from database
        val fetchedCollection = database.newsDAO().getNewsCollection("home")
        //Assert if two slugs are same
        Assert.assertEquals(newsCollection.slug, fetchedCollection.slug)
    }

    @Test
    fun nullIsReturnedWhenThereIsNoData() {

        val fetchedCollection = database.newsDAO().getNewsCollection("home")

        Assert.assertNull(fetchedCollection)
    }

    @Test
    fun returnFalseWhenCollectionIsNotPresent() {
        val fetchedCollection = localSource.isCached("name").blockingGet()

        Assert.assertFalse(fetchedCollection)
    }

}