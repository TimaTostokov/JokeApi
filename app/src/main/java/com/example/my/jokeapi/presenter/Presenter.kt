package com.example.my.jokeapi.presenter

import com.example.my.jokeapi.model.JokeModel
import com.example.my.jokeapi.model.RetrofitService
import com.example.my.jokeapi.view.JokeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter(private val jokeView: JokeView) {

    private val api = RetrofitService().api

    fun getJokeResult() {
        api.getJoke().enqueue(object : Callback<JokeModel> {
            override fun onResponse(call: Call<JokeModel>, response: Response<JokeModel>) {
                response.body()?.let {
                    jokeView.showJoke(it.body.first().setup, it.body.first().punchline)
                }
            }

            override fun onFailure(call: Call<JokeModel>, t: Throwable) {
                jokeView.showError(t.message.toString())
            }

        })
    }

}