package com.dev.cosmina.githubapi.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.cosmina.githubapi.GitHubApiApp
import com.dev.cosmina.githubapi.R
import com.dev.cosmina.githubapi.ui.model.RepositoryItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), OnItemClickListener {

    companion object {
        private const val BASE_QUERY_STATE_KEY = "android"
        private const val TAG = "HOME TAG"
    }

    @Inject
    lateinit var searchViewModel: HomeViewModel
    lateinit var repositoryAdapter: RepositoryAdapter
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GitHubApiApp.getActivityComponent().inject(this)
        setContentView(R.layout.activity_home)

        repositoryAdapter = RepositoryAdapter(this)
        repository_items.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = repositoryAdapter
            setOnClickListener {

            }
        }

        setUpRefreshLayout()

        searchViewModel.searchQuery =
            savedInstanceState?.getString(BASE_QUERY_STATE_KEY) ?: searchViewModel.searchQuery

        searchViewModel.gelAllRepositories()
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    override fun onPause() {
        unbindViewModel()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(BASE_QUERY_STATE_KEY, searchViewModel.searchQuery)
        super.onSaveInstanceState(outState)
    }

    private fun setUpRefreshLayout() {
        refresh_layout.apply {
            setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent)
            setOnRefreshListener {
                searchViewModel.gelAllRepositories()
            }
        }
    }

    private fun bindViewModel() {
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(searchViewModel.getReposSubject()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { if (it.isEmpty()) showMessage("No results") else showItems(it) },
                { Log.d(TAG, "Error showing items", it) }
            ))

        compositeDisposable.add(searchViewModel.getLoadingIndicatorSubject()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { setLoadingVisibility(it) },
                { Log.d(TAG, "Error showing loading indicator", it) }
            ))
    }

    private fun unbindViewModel() {
        compositeDisposable.dispose()
        searchViewModel.clear()
    }

    private fun showItems(items: List<RepositoryItem>) {
        repository_items.visibility = View.VISIBLE
        search_message.visibility = View.INVISIBLE

        repositoryAdapter.replaceItems(items)
    }

    private fun showMessage(message: String?) {
        repository_items.visibility = View.INVISIBLE
        search_message.visibility = View.VISIBLE
        search_message.text = message
    }

    private fun setLoadingVisibility(visibility: Boolean) {
        refresh_layout.isRefreshing = visibility
    }

    override fun onItemClicked(repoItem: RepositoryItem) {
        val intent = Intent(this, RepoDetailsActivity::class.java)
        intent.putExtra("title", repoItem.name)
        intent.putExtra("description", repoItem.description)
        intent.putExtra("stars", repoItem.stargazers_count.toString())
        intent.putExtra("language", repoItem.language)
        intent.putExtra("forks", repoItem.forks_count.toString())
        intent.putExtra("watchers", repoItem.watchers.toString())
        intent.putExtra("owner", repoItem.owner.login)

        startActivity(intent)
    }

}
