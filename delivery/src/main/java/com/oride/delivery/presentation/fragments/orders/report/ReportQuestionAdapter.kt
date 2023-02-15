package com.oride.delivery.presentation.fragments.orders.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.oride.base.BaseAdapter
import com.oride.delivery.data.ReportQuestion
import com.oride.delivery.databinding.LayoutReportItemBinding
import javax.inject.Inject

class ReportQuestionAdapter @Inject constructor(
) : BaseAdapter<ReportQuestion, LayoutReportItemBinding>() {

    override fun initializeViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ) = LayoutReportItemBinding.inflate(layoutInflater, parent, false)

    override fun initializeDiffItemCallback() = object : DiffUtil.ItemCallback<ReportQuestion>() {
        override fun areItemsTheSame(oldItem: ReportQuestion, newItem: ReportQuestion) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReportQuestion, newItem: ReportQuestion) = oldItem == newItem
    }

    override fun onBindViewHolder(holder: BaseViewHolder<LayoutReportItemBinding>, position: Int) {
        val question = differ.currentList[position]
        holder.binding.apply {
            tvName.text = question.question
        }
        holder.itemView.setOnClickListener {
            listener?.invoke(it, question)
        }
    }
}