package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeListFragment : Fragment() {
    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private var _binding: FragmentShoeListBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoeViewModel.shoeListLiveData.observe(viewLifecycleOwner) {
            removeAllViews()
            addNewViews(it)
        }
        binding.addShoeFloatingButton.setOnClickListener {
            it.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
    }

    private fun addNewViews(shoeList: MutableList<Shoe>?) {
        shoeList?.let { list ->
            list.forEach {
                val view = layoutInflater.inflate(R.layout.shoe_item, null)
                val textView = view.findViewById<TextView>(R.id.shoeTitleText)
                textView.text = it.name
                binding.shoeListLayout.addView(view)
            }
        }
    }

    private fun removeAllViews() {
        binding.shoeListLayout.removeAllViews()
    }
}