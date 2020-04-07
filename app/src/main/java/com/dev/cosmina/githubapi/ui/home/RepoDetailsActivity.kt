package com.dev.cosmina.githubapi.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.cosmina.githubapi.R
import kotlinx.android.synthetic.main.activity_repo_details.*

class RepoDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        setupUILayout()
    }

    private fun setupUILayout() {
        repository_name.text = intent.getStringExtra("title")
        repository_description.text = intent.getStringExtra("description")
        repository_stars.text = intent.getStringExtra("stars")
        repository_language.text = intent.getStringExtra("language")
        repository_forks.text = intent.getStringExtra("forks")
        repository_watchers.text = intent.getStringExtra("watchers")
        repository_owner.text = intent.getStringExtra("owner")
    }
}