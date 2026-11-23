package com.kotlinconf.workshop.articles.tasks

import com.kotlinconf.workshop.articles.model.Article
import com.kotlinconf.workshop.articles.network.BlogService
import com.kotlinconf.workshop.articles.network.BlogServiceBlocking
import com.kotlinconf.workshop.articles.network.createBlogService
import com.kotlinconf.workshop.blog.ArticleInfo

// Task: Implement blocking and non-blocking versions for articles loading

// This function is invoked when you select the "BLOCKING" option in the UI.
fun loadArticles(serviceBlocking: BlogServiceBlocking): List<Article> {
    val articleInfo = serviceBlocking.getArticleInfoList()
    return articleInfo.map {
        val comments = serviceBlocking.getComments(it)
        Article(info = it, comments = comments)
    }
}

// This function is invoked when you select the "SUSPENDING" option in the UI.
suspend fun loadArticles(service: BlogService): List<Article> {
    val articleInfo = service.getArticleInfoList()
    return articleInfo.map {
        val comments = service.getComments(it)
        Article(info = it, comments = comments)
    }
}

fun main0() {
    val serviceBlocking = BlogServiceBlocking()
    val articles = loadArticles(serviceBlocking)
    println(articles)
}

suspend fun main() {
    val service = createBlogService()
    println(loadArticles(service))
}