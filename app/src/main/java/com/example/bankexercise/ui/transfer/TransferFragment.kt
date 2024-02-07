package com.example.bankexercise.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bankexercise.data.Account
import com.example.bankexercise.databinding.FragmentTransferBinding
import com.tps.challenge.other.Status
import org.koin.android.ext.android.inject

class TransferFragment : Fragment() {

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransferViewModel by inject<TransferViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)

        lateinit var fromAccount: Account
        lateinit var toAccount: Account

        @Suppress("DEPRECATION") // The new method is not backward compatible.
        arguments?.getSerializable("from_account")?.let {
            if (it is Account) {
                binding.fromAccountTitle.text = it.accountType.toString()
                binding.fromAccountBalance.text = "$" + it.accountBalance.toString()
                fromAccount = it
            }
        }

        @Suppress("DEPRECATION") // The new method is not backward compatible.
        arguments?.getSerializable("to_account")?.let {
            if (it is Account) {
                binding.toAccountTitle.text = it.accountType.toString()
                binding.toAccountBalance.text = "$" + it.accountBalance.toString()
                toAccount = it
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            binding.loading.visibility =
                if (viewModel.status.value?.status == Status.LOADING) View.VISIBLE else View.GONE

            if (it.status == Status.ERROR) {
                binding.errorMessage.text = it.message
            }
            if (it.status == Status.SUCCESS) {
                it.data?.let { success ->
                    if (success) {
                        Toast.makeText(context, "Transfer Complete !!!", Toast.LENGTH_LONG).show()
                        findNavController().popBackStack()
                    }
                }
            }
        }

        viewModel.amount.observe(viewLifecycleOwner) {
            binding.inputAmount.setText("$" + it.toString())
        }

        binding.sendButton.setOnClickListener {
            viewModel.sendTransfer(
                fromAccount,
                toAccount,
                binding.inputAmount.text.toString().drop(1).toDoubleOrNull() ?: 0.00
            )
        }

        binding.twentyDollarButton.setOnClickListener {
            viewModel.addAmount(20.00)
        }
        binding.fiftyDollarButton.setOnClickListener {
            viewModel.addAmount(50.00)
        }
        binding.hundredDollarButton.setOnClickListener {
            viewModel.addAmount(100.00)
        }
        binding.fiveHundredDollarButton.setOnClickListener {
            viewModel.addAmount(500.00)
        }

        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}