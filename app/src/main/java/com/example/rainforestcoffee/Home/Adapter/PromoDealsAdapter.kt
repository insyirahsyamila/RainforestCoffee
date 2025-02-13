package com.example.rainforestcoffee.Home.Adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.rainforestcoffee.Data.Products
import com.example.rainforestcoffee.databinding.PromoDealsRvItemBinding

class PromoDealsAdapter: RecyclerView.Adapter<PromoDealsAdapter.PromoDealsVH>(){

    inner class PromoDealsVH(private val binding: PromoDealsRvItemBinding): ViewHolder(binding.root){
        fun bind(products: Products){
            binding.apply {
                Glide.with(itemView).load(products.images[0]).into(promoDealImg)
                products.offerPercentage?.let {
                    val remainingPrice = 1f - it
                    val priceAfterOffer = remainingPrice * products.price
                    promoDealNewPrice.text = "RM ${String.format("%.1f", priceAfterOffer)}"
                    promoDealOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                promoDealOldPrice.text = "RM ${products.price}"
                promoDealName.text = products.name
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoDealsVH {
        return PromoDealsVH(
            PromoDealsRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PromoDealsVH, position: Int) {
        val products = differ.currentList[position]
        holder.bind(products)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}