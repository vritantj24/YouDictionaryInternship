package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.widget.TextView
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MySingleton constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MySingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    private val baseurl = "https://download-indian-apps.com/bolo/feed.json"

    private val requestQueue = Volley.newRequestQueue(context.applicationContext)


    fun fetchData(videoAdapter: VideoAdapter, context: Context, synonymAdapter: SynonymAdapter, meaningtv:TextView, wordtv:TextView) {

        val url:String = baseurl


        val baseuri: Uri = Uri.parse(url)
        val builder : Uri.Builder? = baseuri.buildUpon()


        val ur = builder.toString()

        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET,
            ur,
            null,
            {
                val wordJSONObject = it.getJSONObject("wordDay")
                val synonymJSONArray = wordJSONObject.getJSONArray("synonym")
                val synonymArray = ArrayList<String>()
                for (i in 0 until synonymJSONArray.length())
                {
                    synonymArray.add(synonymJSONArray.getString(i))
                }
                val meaning  = wordJSONObject.getString("generic")
                val word = wordJSONObject.getString("word")


                val videoJsonObject = it.getJSONObject("videos")
                val videoJsonArray = videoJsonObject.getJSONArray("vids")
                val videoArray = ArrayList<Video>()
                for(i in 0 until videoJsonArray.length()) {
                    val vidJsonObject = videoJsonArray.getJSONObject(i)
                    val video = Video(
                        vidJsonObject.getString("thumbnail"),
                        vidJsonObject.getString("text")
                    )
                    videoArray.add(video)
                }
                videoAdapter.updateVideo(videoArray)
                synonymAdapter.updateSynonym(synonymArray)
                meaningtv.text = meaning
                wordtv.text = word
            },
            {
                Toast.makeText(context,"Error Fetching the Data! Please Check Your Internet", Toast.LENGTH_LONG).show()
            }

        ) {}
        requestQueue.add(jsonObjectRequest)
    }

}