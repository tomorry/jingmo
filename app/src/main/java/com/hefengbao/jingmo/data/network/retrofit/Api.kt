package com.hefengbao.jingmo.data.network.retrofit

import com.hefengbao.jingmo.data.model.ChineseKnowledge
import com.hefengbao.jingmo.data.model.ChineseWisecrack
import com.hefengbao.jingmo.data.model.ClassicPoem
import com.hefengbao.jingmo.data.model.Dataset
import com.hefengbao.jingmo.data.model.Idiom
import com.hefengbao.jingmo.data.model.PeopleWrapper
import com.hefengbao.jingmo.data.model.PoemSentence
import com.hefengbao.jingmo.data.model.Riddle
import com.hefengbao.jingmo.data.model.TongueTwister
import com.hefengbao.jingmo.data.model.WritingWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("dataset.json")
    suspend fun dataset(): List<Dataset>

    @GET("chinese_knowledge.json")
    suspend fun chineseKnowledge(): List<ChineseKnowledge>

    @GET("chinese_wisecracks.json")
    suspend fun chineseWisecracks(): List<ChineseWisecrack>

    @GET("idioms.json")
    suspend fun idioms(): List<Idiom>

    @GET("people_{page}.json")
    suspend fun people(
        @Path("page") page: Int
    ): PeopleWrapper

    @GET("classic_poems.json")
    suspend fun classicPoems(): List<ClassicPoem>

    @GET("poem_sentences.json")
    suspend fun poemSentences(): List<PoemSentence>

    @GET("riddles.json")
    suspend fun riddles(): List<Riddle>

    @GET("tongue_twisters.json")
    suspend fun tongueTwisters(): List<TongueTwister>

    @GET("writings_{page}.json")
    suspend fun writings(
        @Path("page") page: Int
    ): WritingWrapper

}