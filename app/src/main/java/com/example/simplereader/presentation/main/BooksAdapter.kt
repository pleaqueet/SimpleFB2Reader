package com.example.simplereader.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplereader.data.room.Book
import com.example.simplereader.databinding.ItemAddSectionButtonBinding
import com.example.simplereader.presentation.add.OnButtonClickListener

interface OnBookClickListener {
    fun onClick(book: Book)
}

class BooksAdapter (private val onButtonClickListener: OnButtonClickListener,
private val buttons: List<String>
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(), View.OnClickListener {

    class BooksViewHolder(
        val binding: ItemAddSectionButtonBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddSectionButtonBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val button = buttons[position]
        holder.itemView.tag = button
        with(holder.binding) {
            addButton.text = button
        }
    }

    override fun getItemCount() = buttons.size

    override fun onClick(view: View) {
        val button = view.tag as String
        onButtonClickListener.onClick(button)
    }
}