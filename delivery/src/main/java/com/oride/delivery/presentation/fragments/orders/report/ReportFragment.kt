package com.oride.delivery.presentation.fragments.orders.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentReportBinding
import com.oride.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReportFragment : BaseFragment<ReportViewModel, FragmentReportBinding>(R.layout.fragment_report) {

    @Inject
    lateinit var reportQuestionAdapter: ReportQuestionAdapter

    override val viewModel by viewModels<ReportViewModel>()

    override fun initializeViewBinding(view: View) = FragmentReportBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setupRecyclerView()
        subscribeObserver()
    }

    private fun setListeners() {
        val onClickListener = View.OnClickListener {
            when (it) {
                binding.sendBtn -> {}
            }
        }

        binding.sendBtn.setOnClickListener(onClickListener)
    }

    private fun setupRecyclerView() {
        binding.rvQues.adapter = reportQuestionAdapter
        reportQuestionAdapter.setOnItemClickListener { view, item ->
            if (item.isOther) {
                binding.etReason.visibility = View.VISIBLE
            } else {
                binding.etReason.visibility = View.GONE
            }
        }
    }

    private fun subscribeObserver() {
        viewModel.fetchReportQuestion().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    reportQuestionAdapter.differ.submitList(resource.data)
                }
            }
        }
    }
}