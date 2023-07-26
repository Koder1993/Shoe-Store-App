package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.AddShoeState
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private var _binding: FragmentShoeDetailBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        binding.save.setOnClickListener {
            shoeViewModel.addNewShoe(
                binding.shoeNameEditText.text.toString(),
                binding.shoeCompanyEditText.text.toString(),
                binding.shoeSizeEditText.text.toString(),
                binding.shoeDescriptionEditText.text.toString(),
            )
        }

        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }

        shoeViewModel.addShoeStateLiveData.observe(viewLifecycleOwner) {
            if (it == AddShoeState.INVALID) {
                Toast.makeText(
                    requireContext(),
                    "Please enter valid values for all fields!",
                    Toast.LENGTH_SHORT
                ).show()
                shoeViewModel.updateState(AddShoeState.NOT_DEFINED)
            } else if (it == AddShoeState.VALID) {
                shoeViewModel.updateState(AddShoeState.NOT_DEFINED)
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

}