package com.example.rainforestcoffee.Home.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rainforestcoffee.Data.Products
import com.example.rainforestcoffee.databinding.SpecialRvItemBinding

class SpecialProductAdapter : RecyclerView.Adapter<SpecialProductAdapter.SpecialProductVH>(){

    inner class SpecialProductVH(private val binding: SpecialRvItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(products: Products){
                binding.apply {
                    Glide.with(itemView).load(products.images[0]).into(specialProductImg)
                    specialProductName.text = products.name
                    specialProductPrice.text = "RM ${products.price}"
                    Log.d("ProductBind", "Price: RM ${products.price}")
                }
            }
        }

    private val diffCallback = object: DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductVH {
        return SpecialProductVH(
            SpecialRvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SpecialProductVH, position: Int) {
        val products = differ.currentList[position]
        holder.bind(products)    }
}