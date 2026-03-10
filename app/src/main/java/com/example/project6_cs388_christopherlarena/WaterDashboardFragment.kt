package com.example.project6_cs388_christopherlarena

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.project6_cs388_christopherlarena.databinding.FragmentWaterDashboardBinding

class WaterDashboardFragment : Fragment() {

    private var _binding: FragmentWaterDashboardBinding? = null
    private val binding get() = _binding!!

    private val waterViewModel: WaterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWaterDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waterViewModel.allEntries.observe(viewLifecycleOwner) { entries ->
            if (entries != null) {
                val totalIntake = entries.sumOf { it.amount }
                val averageIntake = if (entries.isNotEmpty()) {
                    totalIntake.toDouble() / entries.size
                } else {
                    0.0
                }

                binding.textViewTotalAmount.text = "$totalIntake oz"
                binding.textViewAverageAmount.text = String.format("%.1f oz", averageIntake)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
