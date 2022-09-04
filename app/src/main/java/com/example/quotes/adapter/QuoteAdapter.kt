package com.example.quotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.model.Quotes
import com.example.quotes.databinding.QuotesListBinding

class QuoteAdapter : RecyclerView.Adapter<QuoteAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : QuotesListBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick : ((Quotes) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<Quotes>(){
        override fun areItemsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(QuotesListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quotes = differ.currentList[position]
        holder.binding.txtQuote.text = quotes.text
        holder.binding.txtAuthor.text = "-  ${quotes.author}"

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(quotes)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}