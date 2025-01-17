package com.hefengbao.jingmo.data.model

data class DatasetVersion(
    val chineseKnowledgeVersion: Int = 0,
    val chineseWisecracksVersion: Int = 0,
    val classicPoemsVersion: Int = 0,
    val idiomsVersion: Int = 0,
    val peopleVersion: Int = 0,
    val poemSentencesVersion: Int = 0,
    val riddlesVersion: Int = 0,
    val tongueTwistersVersion: Int = 0,
    val writingsVersion: Int = 0,
    val writingsCurrentPage: Int = 1,
    val writingsCurrentCount: Int = 0,
)