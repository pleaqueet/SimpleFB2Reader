package com.example.simplereader.presentation.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplereader.R
import com.example.simplereader.data.room.Book
import com.example.simplereader.databinding.FragmentAddBinding
import com.example.simplereader.presentation.BooksViewModel
import com.example.simplereader.utils.AndroidUtils.toast
import com.example.simplereader.utils.FB2ParserUtils
import com.example.simplereader.utils.FilesUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: AddBooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        val addSectionButtons = resources.getStringArray(R.array.add_section_buttons).toList()
        val adapter = AddButtonsAdapter(onButtonClickListener = object : OnButtonClickListener {
            override fun onClick(button: String) {
                when (button) {
                    getString(R.string.device) -> pickFile()
                    getString(R.string.litres) -> pickFile()
                    else -> {}
                }
            }
        }, buttons = addSectionButtons)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun pickFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            viewModel.insertBookByUri(uri = data?.data, context = requireContext())
        }
    }
}