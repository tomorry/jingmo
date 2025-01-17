package com.hefengbao.jingmo.data.repository

import com.hefengbao.jingmo.data.database.entity.ChineseKnowledgeEntity
import com.hefengbao.jingmo.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.jingmo.data.database.entity.ClassicPoemEntity
import com.hefengbao.jingmo.data.database.entity.IdiomEntity
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.database.entity.PoemSentenceEntity
import com.hefengbao.jingmo.data.database.entity.TongueTwisterEntity
import com.hefengbao.jingmo.data.database.entity.WritingEntity
import kotlinx.coroutines.flow.Flow

interface ImportRepository {
    suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity)
    suspend fun insertChineseKnowledge(entity: ChineseKnowledgeEntity)
    suspend fun insertClassicPoem(entity: ClassicPoemEntity)
    suspend fun insertIdiom(entity: IdiomEntity)
    suspend fun insertPeople(entity: PeopleEntity)
    suspend fun insertPoemSentence(entity: PoemSentenceEntity)
    suspend fun insertTongueTwister(entity: TongueTwisterEntity)
    suspend fun insertWriting(entity: WritingEntity)
    fun chineseWisecrackTotal(): Flow<Int>
    fun chineseKnowledgeTotal(): Flow<Int>
    fun classicPoemTotal(): Flow<Int>
    fun idiomsTotal(): Flow<Int>
    fun peopleTotal(): Flow<Int>
    fun poemSentencesTotal(): Flow<Int>
    fun tongueTwistersTotal(): Flow<Int>
    fun writingsTotal(): Flow<Int>
}