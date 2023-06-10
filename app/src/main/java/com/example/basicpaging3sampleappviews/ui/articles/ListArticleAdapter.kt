package com.example.basicpaging3sampleappviews.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicpaging3sampleappviews.data.Article
import com.example.basicpaging3sampleappviews.databinding.HolderArticleBinding

class ListArticleAdapter(
    private val owner: LifecycleOwner,
    private val op: (Article) -> Unit
) : PagingDataAdapter<Article, ListArticleAdapter.ArticleViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            HolderArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ArticleViewHolder(
        private val binding: HolderArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val article = getItem(absoluteAdapterPosition)
                article?.let(op)
            }
        }

        fun bind(article: Article) = with(binding) {
            lifecycleOwner = owner
            vm = ArticleViewModel(article)
            executePendingBindings()
        }
    }

    companion object {

        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}