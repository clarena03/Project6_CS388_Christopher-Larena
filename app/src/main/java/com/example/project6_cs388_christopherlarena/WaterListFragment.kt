package com.example.project6_cs388_christopherlarena

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project6_cs388_christopherlarena.databinding.FragmentWaterListBinding

class WaterListFragment : Fragment() {

    private var _binding: FragmentWaterListBinding? = null
    private val binding get() = _binding!!

    private val waterViewModel: WaterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWaterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WaterAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        waterViewModel.allEntries.observe(viewLifecycleOwner) { entries ->
            entries?.let { adapter.submitList(it) }
        }

        binding.buttonAdd.setOnClickListener {
            val amountText = binding.editTextAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val amount = amountText.toInt()
                val entry = WaterEntry(amount = amount)
                waterViewModel.insert(entry)
                binding.editTextAmount.text.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
