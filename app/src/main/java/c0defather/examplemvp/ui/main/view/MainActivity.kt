package c0defather.examplemvp.ui.main.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import c0defather.examplemvp.R
import c0defather.examplemvp.data.model.Photo
import c0defather.examplemvp.data.model.Size
import c0defather.examplemvp.ui.base.view.BaseActivity
import c0defather.examplemvp.ui.main.presenter.MainPresenter
import c0defather.examplemvp.ui.photo.view.PhotoActivity
import c0defather.examplemvp.utils.extensions.hide
import c0defather.examplemvp.utils.extensions.show
import c0defather.examplemvp.utils.views.SuggestionProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


/**
 * Created by kuanysh on 4/28/18.
 */
open class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var adapter: MainAdapter

    lateinit var searchView: SearchView

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)

        setupRecycler()

        presenter.bind(this)
    }

    private fun setupRecycler() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView!!.canScrollVertically(1) && !adapter.isEmpty()) {
                    presenter.fetchPhotosNext(searchView.query.toString())
                }
            }
        })
    }

    private fun setupSearchView(searchItem : MenuItem, searchManager : SearchManager) {
        searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                searchView.requestFocus()
                return true
            }
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                adapter.clearItems()
                searchView.setQuery("",false)
                statusText.text = getText(R.string.start_search)
                statusText.show()
                return true
            }
        })
    }

    override fun onFetchPhotosIdsSuccess(photos: List<Photo>) {
        for (p: Photo in photos) {
            presenter.fetchPhotoSize(p.id)
        }
        adapter.addItems(photos)
        progressBar.hide()
        if (adapter.isEmpty()){
            statusText.text = getText(R.string.no_results)
            statusText.show()
        }
    }

    override fun onFetchPhotosIdsError(error: Throwable) {
        Toast.makeText(this, "Error. ${error.message.toString()}", Toast.LENGTH_LONG).show()
        progressBar.hide()
        statusText.text = getText(R.string.error)
        statusText.show()
    }

    override fun onFetchPhotoSizesSuccess(photo_id: String, sizes: List<Size>) {
        adapter.setPhotoUrl(photo_id, sizes)
        progressBar.hide()
    }

    override fun onFetchPhotoSizesError(photo_id: String, error: Throwable) {
        Toast.makeText(this, "Error. ${error.message.toString()}", Toast.LENGTH_LONG).show()
        progressBar.hide()
    }

    override fun onResume() {
        super.onResume()
        root.requestFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun setCurrentPhoto(photo: Photo) {
        presenter.setCurrentPhoto(photo)
        startActivity(Intent(this, PhotoActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        setupSearchView(searchItem, searchManager)
        return true
    }
    override fun onNewIntent(intent: Intent) {
        setIntent(intent)
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            searchView.setQuery(query, false)
            progressBar.show()
            statusText.hide()
            adapter.clearItems()
            presenter.fetchPhotosStart(query)
            val suggestions = SearchRecentSuggestions(this@MainActivity,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE)
            suggestions.saveRecentQuery(query, null)
        }
    }
}