package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mVideoAdapter : VideoAdapter
    private lateinit var mSynonymAdapter: SynonymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        video_rv.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        mVideoAdapter = VideoAdapter()

        synonyms_rv.layoutManager = LinearLayoutManager(this)
        mSynonymAdapter = SynonymAdapter()

        MySingleton.getInstance(this).fetchData(mVideoAdapter,this,mSynonymAdapter,generic_tv,word_tv)

        video_rv.adapter=mVideoAdapter
        synonyms_rv.adapter=mSynonymAdapter
    }
}