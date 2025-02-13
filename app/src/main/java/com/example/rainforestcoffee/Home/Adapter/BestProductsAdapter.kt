package com.example.rainforestcoffee.Home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Paint
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rainforestcoffee.Data.Products
import com.example.rainforestcoffee.databinding.BestProductRvItemBinding

class BestProductsAdapter: RecyclerView.Adapter<BestProductsAdapter.BestProductsVH>() {

    inner class BestProductsVH(private val binding: BestProductRvItemBinding): ViewHolder(binding.root){
        fun bind(products: Products){
            binding.apply {
                Glide.with(itemView).load(products.images[0]).into(bestProductImg)
                products.offerPercentage?.let {
                    val remainingPrice = 1f - it
                    val priceAfterOffer = remainingPrice * products.price
                    bestProductNewPrice.text = "RM ${String.format("%.1f", priceAfterOffer)}"
                    bestProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if (products.offerPercentage == null)
                    bestProductNewPrice.visibility = View.INVISIBLE
                bestProductPrice.text = "RM ${products.price}"
                bestProductName.text = products.name
            }


        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductsVH {
        return BestProductsVH(
            BestProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: BestProductsVH, position: Int) {
        val products = differ.currentList[position]
        holder.bind(products)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}