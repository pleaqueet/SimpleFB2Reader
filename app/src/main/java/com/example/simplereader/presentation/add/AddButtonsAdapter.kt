package com.example.simplereader.presentation.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplereader.databinding.ItemAddSectionButtonBinding

interface OnButtonClickListener {
    fun onClick(button: String)
}

class AddButtonsAdapter(
    private val onButtonClickListener: OnButtonClickListener,
    private val buttons: List<String>
) : RecyclerView.Adapter<AddButtonsAdapter.AddButtonsViewHolder>(), View.OnClickListener {

    class AddButtonsViewHolder(
        val binding: ItemAddSectionButtonBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddButtonsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddSectionButtonBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return AddButtonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddButtonsViewHolder, position: Int) {
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